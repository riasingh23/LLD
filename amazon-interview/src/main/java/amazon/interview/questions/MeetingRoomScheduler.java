import java.time.LocalDateTime;
import java.util.*;

public class MeetingRoomScheduler {

    // ==================== ENUMS ====================

    enum MeetingStatus {
        SCHEDULED,
        CANCELLED,
        COMPLETED
    }

    // ==================== SLOT ====================

    static class Slot {
        private final LocalDateTime startTime;
        private final LocalDateTime endTime;

        public Slot(LocalDateTime startTime, LocalDateTime endTime) {
            if (startTime == null || endTime == null
                    || !startTime.isBefore(endTime)) {
                throw new IllegalArgumentException("Invalid time slot");
            }

            this.startTime = startTime;
            this.endTime = endTime;
        }

        public boolean overlaps(Slot other) {
            return startTime.isBefore(other.endTime)
                    && other.startTime.isBefore(endTime);
        }

        public LocalDateTime getStartTime() {
            return startTime;
        }

        public LocalDateTime getEndTime() {
            return endTime;
        }
    }

    // ==================== USER ====================

    static class User {
        private final String id;
        private final String name;

        private final List<Meeting> meetings = new ArrayList<>();

        public User(String id, String name) {
            this.id = id;
            this.name = name;
        }

        /*
         * Check + add must be atomic.
         */
        public synchronized boolean reserve(Meeting meeting) {

            for (Meeting existing : meetings) {

                if (existing.getStatus() == MeetingStatus.CANCELLED) {
                    continue;
                }

                if (existing.getSlot().overlaps(meeting.getSlot())) {
                    return false;
                }
            }

            meetings.add(meeting);
            return true;
        }

        public synchronized void removeMeeting(String meetingId) {
            meetings.removeIf(
                    meeting -> meeting.getId().equals(meetingId)
            );
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    // ==================== MEETING ====================

    static class Meeting {
        private final String id;
        private final MeetingRoom meetingRoom;
        private final Slot slot;
        private final List<User> users;

        private MeetingStatus status;

        public Meeting(
                String id,
                MeetingRoom meetingRoom,
                Slot slot,
                List<User> users) {

            this.id = id;
            this.meetingRoom = meetingRoom;
            this.slot = slot;
            this.users = new ArrayList<>(users);
            this.status = MeetingStatus.SCHEDULED;
        }

        public String getId() {
            return id;
        }

        public MeetingRoom getMeetingRoom() {
            return meetingRoom;
        }

        public Slot getSlot() {
            return slot;
        }

        public List<User> getUsers() {
            return users;
        }

        public MeetingStatus getStatus() {
            return status;
        }

        public void setStatus(MeetingStatus status) {
            this.status = status;
        }
    }

    // ==================== MEETING ROOM ====================

    static class MeetingRoom {
        private final String id;
        private final int capacity;

        private final Map<String, Meeting> scheduledMeetings =
                new HashMap<>();

        public MeetingRoom(String id, int capacity) {
            if (capacity <= 0) {
                throw new IllegalArgumentException(
                        "Capacity must be greater than 0"
                );
            }

            this.id = id;
            this.capacity = capacity;
        }

        /*
         * Check availability + reservation must be atomic.
         */
        public synchronized boolean reserve(Meeting meeting) {

            if (meeting.getUsers().size() > capacity) {
                return false;
            }

            for (Meeting existing : scheduledMeetings.values()) {

                if (existing.getStatus() == MeetingStatus.CANCELLED) {
                    continue;
                }

                if (existing.getSlot().overlaps(meeting.getSlot())) {
                    return false;
                }
            }

            scheduledMeetings.put(meeting.getId(), meeting);

            return true;
        }

        public synchronized boolean cancelMeeting(String meetingId) {

            Meeting meeting = scheduledMeetings.get(meetingId);

            if (meeting == null) {
                return false;
            }

            scheduledMeetings.remove(meetingId);

            meeting.setStatus(MeetingStatus.CANCELLED);

            return true;
        }

        public String getId() {
            return id;
        }

        public int getCapacity() {
            return capacity;
        }
    }

    // ==================== ROOM SERVICE ====================

    static class MeetingRoomService {

        private final Map<String, MeetingRoom> meetingRooms =
                new HashMap<>();

        public synchronized MeetingRoom createMeetingRoom(
                String roomId,
                int capacity) {

            if (meetingRooms.containsKey(roomId)) {
                throw new IllegalArgumentException(
                        "Meeting room already exists"
                );
            }

            MeetingRoom room =
                    new MeetingRoom(roomId, capacity);

            meetingRooms.put(roomId, room);

            return room;
        }

        public MeetingRoom getMeetingRoom(String roomId) {

            MeetingRoom room = meetingRooms.get(roomId);

            if (room == null) {
                throw new IllegalArgumentException(
                        "Meeting room not found"
                );
            }

            return room;
        }
    }

    // ==================== EXCEPTIONS ====================

    static class RoomNotAvailableException
            extends RuntimeException {

        public RoomNotAvailableException(String message) {
            super(message);
        }
    }

    static class UserNotAvailableException
            extends RuntimeException {

        public UserNotAvailableException(String message) {
            super(message);
        }
    }

    static class CapacityExceededException
            extends RuntimeException {

        public CapacityExceededException(String message) {
            super(message);
        }
    }

    // ==================== SCHEDULER ====================

    private final MeetingRoomService roomService;

    public MeetingRoomScheduler(
            MeetingRoomService roomService) {

        this.roomService = roomService;
    }

    /*
     * Booking flow:
     *
     * 1. Get room
     * 2. Check capacity
     * 3. Create Meeting
     * 4. Reserve users
     * 5. Reserve room
     * 6. If room fails -> rollback users
     */
    public Meeting bookMeetingRoom(
            String roomId,
            LocalDateTime startTime,
            LocalDateTime endTime,
            List<User> users) {

        MeetingRoom room =
                roomService.getMeetingRoom(roomId);

        // 1. Validate users
        if (users == null || users.isEmpty()) {
            throw new IllegalArgumentException(
                    "At least one user is required"
            );
        }

        // 2. Check capacity
        if (users.size() > room.getCapacity()) {
            throw new CapacityExceededException(
                    "Room capacity exceeded"
            );
        }

        // 3. Create Slot
        Slot slot = new Slot(startTime, endTime);

        // 4. Create Meeting
        Meeting meeting = new Meeting(
                UUID.randomUUID().toString(),
                room,
                slot,
                users
        );

        List<User> reservedUsers = new ArrayList<>();

        try {

            // 5. Reserve users
            for (User user : users) {

                if (!user.reserve(meeting)) {

                    throw new UserNotAvailableException(
                            "User " + user.getId()
                                    + " is not available"
                    );
                }

                reservedUsers.add(user);
            }

            // 6. Reserve room
            if (!room.reserve(meeting)) {

                throw new RoomNotAvailableException(
                        "Room is not available"
                );
            }

            // 7. Booking successful
            return meeting;

        } catch (RuntimeException e) {

            // 8. Rollback users
            for (User user : reservedUsers) {
                user.removeMeeting(meeting.getId());
            }

            throw e;
        }
    }

    // ==================== CANCEL ====================

    public void cancelMeeting(Meeting meeting) {

        if (meeting == null) {
            return;
        }

        if (meeting.getStatus() == MeetingStatus.CANCELLED) {
            return;
        }

        MeetingRoom room = meeting.getMeetingRoom();

        // Remove from room
        boolean cancelled =
                room.cancelMeeting(meeting.getId());

        if (!cancelled) {
            return;
        }

        // Remove from all users
        for (User user : meeting.getUsers()) {
            user.removeMeeting(meeting.getId());
        }

        meeting.setStatus(MeetingStatus.CANCELLED);
    }

    // ==================== MAIN ====================

    public static void main(String[] args) {

        MeetingRoomService roomService =
                new MeetingRoomService();

        // Create rooms
        roomService.createMeetingRoom("ROOM-1", 5);
        roomService.createMeetingRoom("ROOM-2", 10);

        // Create users
        User user1 =
                new User("U1", "Alice");

        User user2 =
                new User("U2", "Bob");

        // Create scheduler
        MeetingRoomScheduler scheduler =
                new MeetingRoomScheduler(roomService);

        // Book meeting
        Meeting meeting =
                scheduler.bookMeetingRoom(
                        "ROOM-1",
                        LocalDateTime.of(2026, 9, 7, 10, 0),
                        LocalDateTime.of(2026, 9, 7, 11, 0),
                        List.of(user1, user2)
                );

        System.out.println(
                "Meeting created: " + meeting.getId()
        );

        // Cancel meeting
        scheduler.cancelMeeting(meeting);

        System.out.println(
                "Meeting status: " + meeting.getStatus()
        );
    }
}