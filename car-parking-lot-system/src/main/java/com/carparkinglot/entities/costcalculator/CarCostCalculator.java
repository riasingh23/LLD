package com.carparkinglot.entities.costcalculator;

import com.carparkinglot.entities.pricingstrategy.MinuteBasedPricingStrategy;

public class CarCostCalculator extends CostCalculator{

    public CarCostCalculator() {
        super(new MinuteBasedPricingStrategy());
    }
}
