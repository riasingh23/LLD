package com.carparkinglot.entities.costcalculator;

import com.carparkinglot.entities.vehicle.Vehicle;
import com.carparkinglot.enums.VehicleType;

public class CostCalculatorFactory {
    public CostCalculator getCostCalculator(VehicleType vehicleType){
        return switch (vehicleType) {
            case CAR -> new CarCostCalculator();
            case BIKE -> new BikeCostCalculator();
            default -> throw new IllegalArgumentException("Unsupported vehicle type: " + vehicleType.name());
        };
    }
}
