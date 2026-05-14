package com.carparkinglot.entities.parkingspotmanager;

import com.carparkinglot.entities.parkingspot.CarParkingSpot;
import com.carparkinglot.entities.parkingspot.ParkingSpot;
import com.carparkinglot.entities.parkingstrategy.DefaultParkingStrategy;

import java.util.List;

public class CarParkingSpotManager extends ParkingSpotManager {
    public CarParkingSpotManager(List<ParkingSpot> availableParkingSpots) {
        super(new DefaultParkingStrategy(), availableParkingSpots);
    }
}
