package com.carparkinglot.entities.pricingstrategy;

import com.carparkinglot.entities.Ticket;

public class HourlyPricingStrategy implements PricingStrategy{
    @Override
    public int price(Ticket ticket) {
        int rate = PriceConfig.getRate(ticket.getVehicle());
        int hours = (int)(System.currentTimeMillis() - ticket.getTimeStamp())/(1000*60*60);
        if(hours==0) hours = 1;
        return rate * hours;
    }
}
