package com.carparkinglot.entities.parkingspot;

import com.carparkinglot.entities.vehicle.Vehicle;

public abstract class ParkingSpot {
    private final String id;
    private Boolean booked;
    private Vehicle vehicle;

    public ParkingSpot(String id) {
        this.id = id;
        this.booked = false;
    }

    public void parkVehicle(Vehicle vehicle){
        this.booked = true;
        this.vehicle = vehicle;
    }

    public void removeVehicle(){
        this.booked = false;
        this.vehicle = null;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Boolean isBooked() {
        return booked;
    }
}
