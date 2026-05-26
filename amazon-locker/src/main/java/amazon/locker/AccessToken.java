package amazon.locker;

import amazon.locker.entities.Compartment;
import amazon.locker.enums.Location;
import amazon.locker.strategy.ExpirationStrategy;

import java.time.Instant;

public class AccessToken {
    private final String code;
    private final Instant creationAt;
    private final Compartment compartment;

    public AccessToken(Compartment compartment, String code) {
        this.code = code;
        this.creationAt = Instant.now();
        this.compartment = compartment;
    }

    public String getCode() {
        return code;
    }

    public Compartment getCompartment() {
        return compartment;
    }

    public boolean isExpired(ExpirationStrategy expirationStrategy, Location location) {
        return Instant.now().isAfter(creationAt.plus(expirationStrategy.getexpirationDuration(compartment, location))) ;
    }
}
