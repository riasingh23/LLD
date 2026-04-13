package com.carparkinglot.entities.pricingstrategy;

import com.carparkinglot.entities.Ticket;

public class MinuteBasedPricingStrategy implements PricingStrategy{
    private final int rate;

    public MinuteBasedPricingStrategy(int rate) {
        this.rate = rate;
    }

    @Override
    public int price(Ticket ticket) {
        int minutes = (int)(System.currentTimeMillis() - ticket.getTimeStamp())/(1000*60);
        if(minutes==0) minutes = 1;
        return rate * minutes;
    }
}
