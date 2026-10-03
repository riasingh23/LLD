package elevator.system;

import elevator.system.enums.RequestType;

public class ElevatorRequest {
    private final RequestType requestType;
    private final int floor;

    public ElevatorRequest(RequestType requestType, int floor) {
        this.requestType = requestType;
        this.floor = floor;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public int getFloor() {
        return floor;
    }
}
