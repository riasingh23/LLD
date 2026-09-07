package amazon.locker.entities;

import amazon.locker.AccessToken;
import amazon.locker.enums.CompartmentSize;
import amazon.locker.enums.Location;
import amazon.locker.exception.ExpiryCodeException;
import amazon.locker.exception.InvalidCodeException;
import amazon.locker.exception.NoAvailableCompartmentException;
import amazon.locker.strategy.ExpirationStrategy;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class AmazonLocker {
    private List<Compartment> compartments;
    private ConcurrentHashMap<String, AccessToken> codeAccessTokenMapping;
    private Set<String> usedCodes;
    private Location location;
    private static final Logger logger = Logger.getLogger(AmazonLocker.class.getName());
    private final ExpirationStrategy expirationStrategy;

    public AmazonLocker(List<Compartment> compartments, ExpirationStrategy expirationStrategy) {
        this.compartments = compartments;
        this.codeAccessTokenMapping = new ConcurrentHashMap<>();
        this.usedCodes = ConcurrentHashMap.newKeySet();
        this.expirationStrategy = expirationStrategy;
    }

    public String generateCodeAndPutPackage(CompartmentSize size) {
        for(Compartment compartment : compartments) {
            if(compartment.getSize().equals(size)){
                if(compartment.putPackage()) {
                    String code = UUID.randomUUID().toString();
                    // Check both previously used codes and currently active codes to guarantee uniqueness
                    while(usedCodes.contains(code) || codeAccessTokenMapping.containsKey(code)) {
                        code = UUID.randomUUID().toString();
                    }
                    AccessToken accessToken = new AccessToken(compartment, code);
                    codeAccessTokenMapping.put(accessToken.getCode(), accessToken);
                    return accessToken.getCode();
                }
            }
        }
        throw new NoAvailableCompartmentException("No empty compartment found for size: " + size);
    }

    public String validateCodeAndRemovePackage(String code) {
        // Check reuse first to give a more specific error before falling through to null check
        if (usedCodes.contains(code)) throw new InvalidCodeException("Reusing a code after pickup");
        AccessToken accessToken = codeAccessTokenMapping.get(code);
        if (accessToken == null) throw new InvalidCodeException("InCorrect Code: " + code);
        if (accessToken.isExpired(expirationStrategy, location)) throw new ExpiryCodeException("Code already expired");
        if(accessToken.getCompartment() != null){
            if(accessToken.getCompartment().removePackage()) {
                codeAccessTokenMapping.remove(code);
                usedCodes.add(code);
                return "Correct Code";
            }
        }
        throw new InvalidCodeException("InCorrect Code: " + code);
    }

    void openExpiredCompartment() {
        List<AccessToken> accessTokens = new ArrayList<>(codeAccessTokenMapping.values());
        for(AccessToken accessToken: accessTokens) {
            if(accessToken.isExpired(expirationStrategy, location)) {
                if(accessToken.getCompartment()!=null) {
                    if (!accessToken.getCompartment().removePackage()) {
                        logger.warning("removePackage() returned false for compartment "
                                + accessToken.getCompartment().getId()
                                + " during expired token cleanup. Removing stale token: "
                                + accessToken.getCode());
                    }
                    usedCodes.add(accessToken.getCode());
                    codeAccessTokenMapping.remove(accessToken.getCode());
                }
            }
        }
    }
}
