package com.carparkinglot.entities.parkingspotmanager;

import com.carparkinglot.entities.parkingspot.ParkingSpot;
import com.carparkinglot.enums.VehicleType;
import java.util.List;

public class ParkingSpotManagerFactory {
    private final CarParkingSpotManager carParkingSpotManager;
    private final BikeParkingSpotManager bikeParkingSpotManager;

    public ParkingSpotManagerFactory(List<ParkingSpot> carParkingSpotList, List<ParkingSpot> bikeParkingSpotList) {
        carParkingSpotManager = new CarParkingSpotManager(carParkingSpotList);
        bikeParkingSpotManager = new BikeParkingSpotManager(bikeParkingSpotList);
    }

    public ParkingSpotManager getParkingAreaManager(VehicleType vehicleType) {
        switch (vehicleType) {
            case CAR:
                return carParkingSpotManager;
            case BIKE:
                return bikeParkingSpotManager;
            default:
                throw new IllegalArgumentException("Unsupported vehicle type: " + vehicleType.name());
        }
    }
}
