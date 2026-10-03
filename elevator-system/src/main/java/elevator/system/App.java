package elevator.system;

import elevator.system.enums.Direction;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Elevator elevator1 = new Elevator(6,1);
        Elevator elevator2 = new Elevator(6,2);
        Elevator elevator3 = new Elevator(6,3);
        Map<Integer, Elevator> elevators = new HashMap<>();
        elevators.put(elevator1.getElevatorId(), elevator1);
        elevators.put(elevator2.getElevatorId(), elevator2);
        elevators.put(elevator3.getElevatorId(), elevator3);
        ElevatorSystem elevatorSystem = new ElevatorSystem(elevators);
        elevatorSystem.requestElevator(4, Direction.UP);
        elevatorSystem.step();
        elevatorSystem.requestElevator(2, Direction.DOWN);
        elevatorSystem.step();
        elevatorSystem.step();
        elevatorSystem.step();
        elevatorSystem.step();
        elevatorSystem.step();
        elevatorSystem.requestDestination(1,5);
        elevatorSystem.step();
        elevatorSystem.step();
        elevatorSystem.step();
        elevatorSystem.requestDestination(2,1);
        elevatorSystem.step();
        elevatorSystem.step();
        elevatorSystem.step();
        elevatorSystem.step();
        elevatorSystem.step();
        elevatorSystem.step();

    }
}
