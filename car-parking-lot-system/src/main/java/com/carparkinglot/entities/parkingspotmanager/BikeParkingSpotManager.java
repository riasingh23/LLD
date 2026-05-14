package com.carparkinglot.entities.parkingspotmanager;

import com.carparkinglot.entities.parkingspot.ParkingSpot;
import com.carparkinglot.entities.parkingstrategy.DefaultParkingStrategy;

import java.util.List;

public class BikeParkingSpotManager extends ParkingSpotManager {
    public BikeParkingSpotManager(List<ParkingSpot> availableParkingSpots) {
        super(new DefaultParkingStrategy(), availableParkingSpots);
    }
}
