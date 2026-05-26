package amazon.locker.strategy;

import amazon.locker.entities.Compartment;
import amazon.locker.enums.Location;

import java.time.Duration;

public class SizeBasedExpirationStrategy implements ExpirationStrategy{
    @Override
    public Duration getexpirationDuration(Compartment compartment, Location location) {
        switch(compartment.getSize()) {
            case SMALL:
                return Duration.ofDays(7);
            case MEDIUM:
                return Duration.ofDays(14);
            case LARGE:
                return Duration.ofDays(21);
            default:
                throw new RuntimeException("Invalid compartmentSize");
        }

    }
}
