package com.carparkinglot.entities.vehicle;

import com.carparkinglot.enums.VehicleType;

public class Bike implements Vehicle {
    private final String registrationNumber;

    public Bike(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    @Override
    public VehicleType getVehicleType() {
        return VehicleType.BIKE;
    }
}
