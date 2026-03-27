package com.carparkinglot.entities.parkingstrategy;

import com.carparkinglot.entities.parkingspot.ParkingSpot;

import java.util.List;

public class DefaultParkingStrategy implements ParkingStrategy{

    @Override
    public ParkingSpot findingParkingSpot(List<ParkingSpot> parkingSpotList) {
        for(ParkingSpot parkingSpot:parkingSpotList) {
            if(!parkingSpot.isBooked()) {
                return parkingSpot;
            }
        }
        return null;
    }
}
