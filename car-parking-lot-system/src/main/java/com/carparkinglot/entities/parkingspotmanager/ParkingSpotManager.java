package com.carparkinglot.entities.parkingspotmanager;

import com.carparkinglot.entities.parkingspot.ParkingSpot;
import com.carparkinglot.entities.parkingstrategy.ParkingStrategy;
import com.carparkinglot.entities.vehicle.Vehicle;

import java.util.List;

public abstract class ParkingSpotManager {
    private final List<ParkingSpot> parkingSpotList;
    private final ParkingStrategy parkingStrategy;

    public ParkingSpotManager(ParkingStrategy parkingStrategy, List<ParkingSpot> parkingSpotList){
        this.parkingSpotList = parkingSpotList;
        this.parkingStrategy = parkingStrategy;
    }

    public ParkingSpot findParkingSpot () {
        return parkingStrategy.findingParkingSpot(parkingSpotList);
    }

    public ParkingSpot parkVehicle(Vehicle vehicle) {
        ParkingSpot parkingSpot = findParkingSpot();
        if(parkingSpot!=null){
            parkingSpot.parkVehicle(vehicle);
        }
        return parkingSpot;
    }

    public void removeVehicle(Vehicle vehicle) {
        for(ParkingSpot parkingSpot: parkingSpotList){
            if (parkingSpot.isBooked() == true && parkingSpot.getVehicle() == vehicle) {
                parkingSpot.removeVehicle();
            }
        }
    }
}
