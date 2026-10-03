package elevator.system;

import elevator.system.enums.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ElevatorSystemTest {
    Elevator e1 = new Elevator(6,1);
    Elevator e2 = new Elevator(6,2);
    Elevator e3 = new Elevator(6,3);
    Map<Integer, Elevator> elevatorIdToElevators = new HashMap<>() ;
    ElevatorSystem elevatorSystem;


    @BeforeEach
    void setUp() {
        elevatorIdToElevators.put(e1.getElevatorId(), e1);
        elevatorIdToElevators.put(e2.getElevatorId(), e2);
        elevatorIdToElevators.put(e3.getElevatorId(), e3);
        elevatorSystem = new ElevatorSystem(elevatorIdToElevators);
    }

    @Test
    void requestDestination() {
        elevatorSystem.requestElevator(2, Direction.UP);
        elevatorSystem.step();
        assertEquals(Direction.UP, elevatorIdToElevators.get(1).getDirection());
        elevatorSystem.step();
//
        assertEquals(1, elevatorIdToElevators.get(1).getCurrentFloor());
        assertEquals(0, elevatorIdToElevators.get(2).getCurrentFloor());
        assertEquals(0, elevatorIdToElevators.get(3).getCurrentFloor());
    }

    @Test
    void requestElevator() {
        elevatorSystem.requestDestination(3,1);
        elevatorSystem.step();
        elevatorSystem.step();
        elevatorSystem.step();
        elevatorSystem.step();
        assertEquals(1, elevatorIdToElevators.get(3).getCurrentFloor());
    }

    @Test
    void step() {
        elevatorSystem.step();

    }
}