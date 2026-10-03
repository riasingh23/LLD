package elevator.system;

import elevator.system.enums.Direction;
import elevator.system.enums.RequestType;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.abs;

public class ElevatorSystem {
    Map<Integer, Elevator> elevatorIdToElevators = new HashMap<>() ;

    public boolean requestDestination(int elevatorId, int floor) {
        if (!elevatorIdToElevators.containsKey(elevatorId) || !elevatorIdToElevators.get(elevatorId).validFloor(floor)) {
//            System.out.println("Elevator requested from " + elevatorId + " to " + floor);
            return false;
        }



        Elevator elevator = elevatorIdToElevators.get(elevatorId);
        elevator.addRequest(new ElevatorRequest(RequestType.DESTINATION, floor));
        return true;
    }

    public boolean requestElevator(int floor, Direction direction) {
        RequestType requestType = direction==Direction.DOWN?RequestType.PICKUP_DOWN:RequestType.PICKUP_UP;
        ElevatorRequest elevatorRequest = new ElevatorRequest(requestType, floor);
        Elevator best = selectBestElevator(elevatorRequest);
        if(best == null) {return false;}
        else {
            best.addRequest(elevatorRequest);
//            System.out.println(best.getElevatorId());
            return true;
        }

    }


    private Elevator selectBestElevator(ElevatorRequest elevatorRequest) {
        Elevator best = null;
        best = findMovingTowards(elevatorRequest);
        if(best != null) {
            System.out.println("findMovingTowards"+ " " +best.getElevatorId());
            return best;
        }
        best = nearestidealElevator(elevatorRequest);

        if(best != null) {
            System.out.println("nearestidealElevator"+ " " +best.getElevatorId());
            return best;
        }
        best = getNearest(elevatorRequest);
        System.out.println("getNearest"+ " " +best.getElevatorId());
        return best;
    }

    private Elevator findMovingTowards(ElevatorRequest elevatorRequest) {
        Elevator best = null;
        Direction reqDirection = elevatorRequest.getRequestType()==RequestType.PICKUP_DOWN? Direction.DOWN: Direction.UP;
        int minDistance = Integer.MAX_VALUE;
        for(Elevator elevator : elevatorIdToElevators.values()){
            if(elevator.getDirection() != reqDirection) continue;
            if((reqDirection == Direction.UP && elevatorRequest.getFloor()<elevator.getCurrentFloor()) || (reqDirection == Direction.DOWN && elevatorRequest.getFloor()>elevator.getCurrentFloor())) continue;
            int currMinDist = abs(elevatorRequest.getFloor() - elevator.getCurrentFloor());
            if(elevatorPassFloor(elevator, elevatorRequest.getFloor(), reqDirection) && minDistance > currMinDist) {
                best = elevator;
                minDistance = currMinDist;
            }
        }
        return best;
    }

    private boolean elevatorPassFloor(Elevator e, int floor, Direction dr) {
        for(ElevatorRequest elevatorRequest: e.getRequests()) {
            if((dr == Direction.UP && elevatorRequest.getFloor()>=floor || (dr == Direction.DOWN && elevatorRequest.getFloor()<=floor))){
                return true;
            }
        }
        return false;
    }

    private Elevator nearestidealElevator(ElevatorRequest elevatorRequest) {
        Elevator best = null;
        int minDistance = Integer.MAX_VALUE;
        for(Elevator elevator : elevatorIdToElevators.values()){
            if(!elevator.validFloor(elevatorRequest.getFloor())  ||   !Direction.IDLE.equals(elevator.getDirection())) continue;
            int currMinDist = abs(elevatorRequest.getFloor() - elevator.getCurrentFloor());
            if( minDistance > currMinDist) {
                best = elevator;
                minDistance = currMinDist;
            }
        }
        return best;
    }

    private Elevator getNearest(ElevatorRequest elevatorRequest) {
        Elevator best = null;
        int minDistance = Integer.MAX_VALUE;
        for(Elevator elevator : elevatorIdToElevators.values()){
            int currMinDist = abs(elevatorRequest.getFloor() - elevator.getCurrentFloor());
            if( minDistance > currMinDist) {
                best = elevator;
                minDistance = currMinDist;
            }
        }
        return best;
    }

    public void step() {
        System.out.println("ElevatorSystem step");
        for(Elevator elevator : elevatorIdToElevators.values()){
            elevator.step();
        }
    }

    public ElevatorSystem(Map<Integer, Elevator> elevatorIdToElevators) {
        this.elevatorIdToElevators = elevatorIdToElevators;
    }

    public void addElevator(Elevator elevator) {
        this.elevatorIdToElevators.put(elevator.getElevatorId(), elevator);
    }

    public void removeElevator(Elevator elevator) {
        this.elevatorIdToElevators.remove(elevator.getElevatorId());
    }
}
