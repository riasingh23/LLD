package amazon.locker.entities;

import amazon.locker.enums.CompartmentSize;
import amazon.locker.enums.CompartmentState;

import java.util.concurrent.locks.ReentrantLock;

public class Compartment {
    private String id;
    private CompartmentSize size;
    private CompartmentState compartmentState;
    private final ReentrantLock lock = new ReentrantLock();

    public Compartment(String id, CompartmentSize size) {
        this.id = id;
        this.size = size;
        this.compartmentState = CompartmentState.AVAILABLE;
    }

    public boolean putPackage() {
        lock.lock();
        try {
            if(CompartmentState.OUT_OF_SERVICE.equals(compartmentState) || CompartmentState.OCCUPIED.equals(compartmentState)) return false;
            compartmentState = CompartmentState.OCCUPIED;
            return true;
        } finally {
            lock.unlock();
        }
    }

    public boolean removePackage() {
        lock.lock();
        try {
            if(CompartmentState.AVAILABLE.equals(compartmentState) || CompartmentState.OUT_OF_SERVICE.equals(compartmentState)) return false;
            compartmentState = CompartmentState.AVAILABLE;
            return true;
        } finally {
            lock.unlock();
        }
    }

    public String getId() {
        return id;
    }

    public CompartmentSize getSize() {
        return size;
    }
}
