package com.carparkinglot.entities.pricingstrategy;

import com.carparkinglot.entities.Ticket;

public class FixedPricingStrategy implements PricingStrategy{
    private final int rate;

    public FixedPricingStrategy(int rate) {
        this.rate = rate;
    }
    @Override
    public int price(Ticket ticket) {
        return rate;
    }
}
