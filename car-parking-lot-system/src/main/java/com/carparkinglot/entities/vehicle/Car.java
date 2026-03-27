package com.carparkinglot.entities.vehicle;

import com.carparkinglot.enums.VehicleType;

public class Car implements Vehicle {
    private final String registrationNumber;

    public Car(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    @Override
    public VehicleType getVehicleType() {
        return VehicleType.CAR;
    }
}
