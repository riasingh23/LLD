package com.carparkinglot.entities.pricingstrategy;

import com.carparkinglot.entities.Ticket;

public interface PricingStrategy {
    int price(Ticket ticket);
}
