package amazon.locker;

import amazon.locker.entities.Compartment;
import java.util.UUID;

public class AccessToken {
    private String code;
    private long creationTime;
    private Compartment compartment;

    public AccessToken(Compartment compartment, String code) {
        this.code = code;
        this.creationTime = System.currentTimeMillis();
        this.compartment = compartment;
    }

    public String getCode() {
        return code;
    }

    public Compartment getCompartment() {
        return compartment;
    }

    public boolean isExpired() {
        return System.currentTimeMillis()-creationTime>(7*24*60*60*1000);
    }
}
