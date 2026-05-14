package com.carparkinglot.entities.pricingstrategy;

import com.carparkinglot.entities.Ticket;

public class HourlyPricingStrategy implements PricingStrategy{
    private final int rate;

    public HourlyPricingStrategy(int rate) {
        this.rate = rate;
    }

    @Override
    public int price(Ticket ticket) {
        int hours = (int)(System.currentTimeMillis() - ticket.getTimeStamp())/(1000*60*60);
        if(hours==0) hours = 1;
        return rate * hours;
    }
}
