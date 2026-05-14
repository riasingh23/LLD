package com.carparkinglot.entities.costcalculator;

import com.carparkinglot.entities.Ticket;
import com.carparkinglot.entities.pricingstrategy.PricingStrategy;

public abstract class CostCalculator {

    private final PricingStrategy pricingStrategy;

    public CostCalculator(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public int calculatingCost(Ticket ticket) {
        return pricingStrategy.price(ticket);
    }
}
