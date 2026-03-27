package com.carparkinglot.entities.pricingstrategy;

import com.carparkinglot.entities.vehicle.Vehicle;

public class PriceConfig {
    public static int getRate(Vehicle vehicle){
        if(vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }

        return switch (vehicle.getVehicleType()) {
            case BIKE -> 10;
            case CAR -> 20;
            default -> throw new IllegalArgumentException("Unsupported vehicle type: " + vehicle.getVehicleType());
        };
    }
}
