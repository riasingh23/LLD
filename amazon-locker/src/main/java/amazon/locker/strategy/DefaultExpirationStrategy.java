package amazon.locker.strategy;

import amazon.locker.entities.Compartment;
import amazon.locker.enums.Location;

import java.time.Duration;

public class DefaultExpirationStrategy implements ExpirationStrategy{
    @Override
    public Duration getexpirationDuration(Compartment compartment, Location location) {
        return Duration.ofDays(7);
    }
}
