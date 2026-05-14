package com.carparkinglot.entities.parkingstrategy;

import com.carparkinglot.entities.parkingspot.ParkingSpot;

import java.util.List;

public interface ParkingStrategy {

    ParkingSpot findingParkingSpot(List<ParkingSpot> parkingSpotList);
}
