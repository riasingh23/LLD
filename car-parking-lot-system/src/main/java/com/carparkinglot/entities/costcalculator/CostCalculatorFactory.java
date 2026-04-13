package com.carparkinglot.entities.costcalculator;

import com.carparkinglot.enums.VehicleType;
import java.util.Map;

public class CostCalculatorFactory {

    private final Map<VehicleType, CostCalculator> costCalculatorMap;

    public CostCalculatorFactory(Map<VehicleType, CostCalculator> costCalculatorMap) {
        this.costCalculatorMap = costCalculatorMap;
    }

    public CostCalculator getCostCalculator(VehicleType type) {

        CostCalculator costCalculator = costCalculatorMap.get(type);
        if (costCalculator == null) {
            throw new IllegalArgumentException("Unsupported vehicle type");
        }
        return costCalculator;
    }
}