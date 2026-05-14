package com.carparkinglot.entities;

import com.carparkinglot.entities.parkingspot.ParkingSpot;
import com.carparkinglot.entities.vehicle.Vehicle;

public class Ticket {
    private long timeStamp;
    private Vehicle vehicle;
    private ParkingSpot parkingSpot;

    public Ticket(Vehicle vehicle, ParkingSpot parkingSpot) {
        this.timeStamp = System.currentTimeMillis();
        this.parkingSpot = parkingSpot;
        this.vehicle = vehicle;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }
}
