package amazon.locker.strategy;

import amazon.locker.entities.Compartment;
import amazon.locker.enums.CompartmentSize;
import amazon.locker.enums.Location;

import java.time.Duration;

public interface ExpirationStrategy {
    public Duration getexpirationDuration(Compartment compartment, Location location);
}
