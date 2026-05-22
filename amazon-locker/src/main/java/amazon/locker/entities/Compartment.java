package amazon.locker.entities;

import amazon.locker.enums.CompartmentSize;

public class Compartment {
    private String id;
    private CompartmentSize size;
    private boolean isOccupied;

    public Compartment(String id, CompartmentSize size) {
        this.id = id;
        this.size = size;
        this.isOccupied = false;
    }

    public boolean putPackage() {
        if(isOccupied) return false;
        isOccupied = true;
        return true;
    }

    public boolean removePackage() {
        if(!isOccupied) return false;
        isOccupied = false;
        return true;
    }


    public String getId() {
        return id;
    }

    public CompartmentSize getSize() {
        return size;
    }

    public boolean isOccupied() {
        return isOccupied;
    }
}
