package elevator.system;
import elevator.system.enums.Direction;
import elevator.system.enums.RequestType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElevatorTest {

    Elevator e = new Elevator(6, 1);

    @Test
    void validFloor() {
        assertEquals(true, e.validFloor(5));
        assertEquals(false, e.validFloor(7));
    }

    @Test
    void step() {
        e.addRequest(new ElevatorRequest(RequestType.PICKUP_DOWN,4));
        e.step();
        assertEquals(Direction.UP, e.getDirection());
        e.step();
        assertEquals(1, e.getCurrentFloor());
        e.step();
        e.step();
        e.step();
        e.step();
        e.step();
        assertEquals(Direction.IDLE, e.getDirection());
        assertEquals(4, e.getCurrentFloor());

    }
}