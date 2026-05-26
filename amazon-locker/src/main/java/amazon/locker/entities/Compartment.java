package amazon.locker.entities;

import amazon.locker.enums.CompartmentSize;

import java.util.concurrent.locks.ReentrantLock;

public class Compartment {
    private String id;
    private CompartmentSize size;
    private boolean isOccupied;
    private final ReentrantLock lock = new ReentrantLock();

    public Compartment(String id, CompartmentSize size) {
        this.id = id;
        this.size = size;
        this.isOccupied = false;
    }

    public boolean putPackage() {
        lock.lock();
        try {
            if(isOccupied) return false;
            isOccupied = true;
            return true;
        } finally {
            lock.unlock();
        }
    }

    public boolean removePackage() {
        lock.lock();
        try {
            if(!isOccupied) return false;
            isOccupied = false;
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
