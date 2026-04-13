package com.carparkinglot.entities.parkingstrategy;

import com.carparkinglot.entities.parkingspot.ParkingSpot;

import java.util.List;

public class DefaultParkingStrategy implements ParkingStrategy{

    @Override
    public ParkingSpot findingParkingSpot(List<ParkingSpot> availableParkingSpotList) {
        return availableParkingSpotList.iterator().next();
    }
}
