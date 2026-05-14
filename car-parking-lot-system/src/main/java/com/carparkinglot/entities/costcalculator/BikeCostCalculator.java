package com.carparkinglot.entities.costcalculator;

import com.carparkinglot.entities.pricingstrategy.HourlyPricingStrategy;

public class BikeCostCalculator extends CostCalculator {
    public BikeCostCalculator() {
        super(new HourlyPricingStrategy(10));
    }
}
