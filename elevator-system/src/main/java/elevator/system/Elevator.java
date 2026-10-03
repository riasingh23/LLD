package elevator.system;

import elevator.system.enums.Direction;
import elevator.system.enums.RequestType;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Elevator {
    private int currentFloor;
    private Direction direction;
    private List<ElevatorRequest> requests = new ArrayList<>();
    private int maxFloor;
    private final int elevatorId;

    public boolean validFloor(int floor) {
        return floor>=0 && floor<=maxFloor;
    }

    public void step() {
        if(requests.isEmpty()) {
            direction = Direction.IDLE;
            System.out.println(elevatorId + " " + direction.toString() + " " + currentFloor);
            return;
        }
        if(direction == Direction.IDLE) {
            pickNearestFloor();
            System.out.println(elevatorId + " " + direction.toString() + " " + currentFloor);
            return;
        }

        if(stopCurrFloor()) {
            removeCurrFloorRequest();
            if(requests.isEmpty()) {
                direction = Direction.IDLE;
            }
            System.out.println(elevatorId + " " + direction.toString() + " " + currentFloor);
            return;
        }

        if(sameDirRequest()){
            if(direction == Direction.DOWN)  currentFloor--;
            else  currentFloor++;
            System.out.println(elevatorId + " " + direction.toString() + " " + currentFloor);
        } else {
            direction = direction==Direction.UP?Direction.DOWN:Direction.UP;
            System.out.println(elevatorId + " " + direction.toString() + " " + currentFloor);
        }
    }

    private boolean sameDirRequest() {
        for(ElevatorRequest request : requests) {
            if ((direction == Direction.UP && currentFloor < request.getFloor()) || (direction == Direction.DOWN && currentFloor > request.getFloor())) {
                return true;
            }
        }
        return false;
    }

    private boolean stopCurrFloor() {
        for (ElevatorRequest request : requests) {
            if(currentFloor == request.getFloor()) {
                return true;
            }
        }
        return false;
    }

    private void removeCurrFloorRequest() {
        requests.removeIf(request -> currentFloor == request.getFloor());
    }

    private void pickNearestFloor() {
        int minFloorDiff = Integer.MAX_VALUE;
        ElevatorRequest elevatorRequest = null;
        for (ElevatorRequest request : requests) {
            int currentFloorDiff = abs(request.getFloor()-currentFloor);
            if(currentFloorDiff < minFloorDiff) {
                minFloorDiff = currentFloorDiff;
                elevatorRequest = request;
            }
        }
        direction = elevatorRequest.getFloor()>currentFloor?Direction.UP:Direction.DOWN;
    }

    public Elevator(int maxFloor, int elevatorId) {
        this.maxFloor = maxFloor;
        this.currentFloor = 0;
        this.elevatorId = elevatorId;
        this.direction = Direction.IDLE;
    }

    public int getElevatorId() {
        return elevatorId;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void addRequest(ElevatorRequest request) {
        this.requests.add(request);
    }

    public void removeRequest(ElevatorRequest request) {
        this.requests.remove(request);
    }

    public List<ElevatorRequest> getRequests() {
        return requests;
    }
}
