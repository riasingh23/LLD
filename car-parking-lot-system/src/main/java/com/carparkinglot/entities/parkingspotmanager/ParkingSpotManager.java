package com.carparkinglot.entities.parkingspotmanager;

import com.carparkinglot.entities.parkingspot.ParkingSpot;
import com.carparkinglot.entities.parkingstrategy.ParkingStrategy;
import com.carparkinglot.entities.vehicle.Vehicle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ParkingSpotManager {
    private final List<ParkingSpot> parkingSpotList;
    private Map<String,ParkingSpot> allocatedparkingSpotList;
    private final ParkingStrategy parkingStrategy;

    public ParkingSpotManager(ParkingStrategy parkingStrategy, List<ParkingSpot> parkingSpotList){
        this.parkingSpotList = parkingSpotList;
        this.parkingStrategy = parkingStrategy;
        this.allocatedparkingSpotList = new HashMap<>();
    }

    public ParkingSpot findParkingSpot () {
        return parkingStrategy.findingParkingSpot(parkingSpotList);
    }

    public ParkingSpot parkVehicle(Vehicle vehicle) {
        ParkingSpot parkingSpot = findParkingSpot();
        if(parkingSpot!=null){
            parkingSpot.parkVehicle(vehicle);
            allocatedparkingSpotList.put(vehicle.getRegistrationNumber(), parkingSpot);
        }
        return parkingSpot;
    }

    public void removeVehicle(Vehicle vehicle) {
        ParkingSpot parkingSpot = allocatedparkingSpotList.get(vehicle.getRegistrationNumber());
        allocatedparkingSpotList.remove(vehicle.getRegistrationNumber());
        parkingSpot.removeVehicle();
    }
}
