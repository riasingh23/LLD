package com.carparkinglot.entities.gate;

import com.carparkinglot.entities.Ticket;
import com.carparkinglot.entities.parkingspot.ParkingSpot;
import com.carparkinglot.entities.parkingspotmanager.ParkingSpotManager;
import com.carparkinglot.entities.parkingspotmanager.ParkingSpotManagerFactory;
import com.carparkinglot.entities.vehicle.Vehicle;

public class EntryGate implements Gate {
    private String gateId;
    private ParkingSpotManagerFactory parkingSpotManagerFactory;

    public EntryGate(String gateId, ParkingSpotManagerFactory parkingSpotManagerFactory) {
        this.gateId = gateId;
        this.parkingSpotManagerFactory = parkingSpotManagerFactory;
    }
    
    public Ticket parkVehicle(Vehicle vehicle) {
        ParkingSpotManager parkingSpotManager = parkingSpotManagerFactory.getParkingAreaManager(vehicle.getVehicleType());
        ParkingSpot parkingSpot = parkingSpotManager.parkVehicle(vehicle);
        if(parkingSpot != null) {
            return new Ticket(vehicle, parkingSpot);
        }
        return null;
    }
}
