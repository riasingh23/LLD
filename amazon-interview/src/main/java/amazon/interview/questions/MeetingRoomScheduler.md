Meeting Room Scheduler
Design a meeting room booking system that allows users to create meeting rooms with defined capacities, 
book available rooms for specific time intervals, and prevent overlapping reservations.

Requirement
- create rooms
- meeting room booking system that allows users to create meeting rooms with defined capacities
- book available rooms for specific time intervals
  - prevent overlapping reservations
  - User availability
- Cancellation

Entities
- MeetingRoom
- Users
- MeetingRoomScheduler
- Slot

Class Diagram
Users
- id
- name
- List<Meeting>
+ synchronized reserve(Meeting)
+ removeMeeting(meetingId)

Slot
- int startTime
- int endTime
- date

Meeting
- id
- MeetingRoom
- Slot
- List<User> users
- status - Cancel, Done, Todo

MeetingRoom
- int MeetingroomID
- Map<meetingId, Meeting> scheduledMeet
- int capacity
+ synchronized reserve(Meeting)
  1. check availability
  2. if true add to scheduledMeet
+ synchronized cancelMeeting(meetingId)
  1. check unavailability
  2. if true remove from scheduledMeet
  3. Meeting.status = cancel

MeetingRoomScheduler
- Map<MeetingroomID, MeetingRoom> meetingRooms;
+ bookMeetingRoom(int MeetingroomID, int startTime, int endTime, List<User> users) -> meeting
  1. Scheduler checks capacity
  2. Scheduler checks user availability
  3. Scheduler calls room.reserve()
  4. add to user meetings
+ cancelMeeting(Meeting) calls meeting.getMeeingRoom().cancelMeeting(meetingId);
  1. Scheduler calls room.cancelMeeting()
  2. remove from  user meetings