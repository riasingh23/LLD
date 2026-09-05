package amazon.locker.exception;

public class NoAvailableCompartmentException extends RuntimeException {
    public NoAvailableCompartmentException(String message) {
        super(message);
    }
}
