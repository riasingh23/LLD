package com.carparkinglot.entities.pricingstrategy;

import com.carparkinglot.entities.Ticket;

public class FixedPricingStrategy implements PricingStrategy{
    @Override
    public int price(Ticket ticket) {
        return PriceConfig.getRate(ticket.getVehicle());
    }
}
