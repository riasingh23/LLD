package com.carparkinglot;

import com.carparkinglot.entities.Ticket;
import com.carparkinglot.entities.costcalculator.CostCalculatorFactory;
import com.carparkinglot.entities.gate.EntryGate;
import com.carparkinglot.entities.gate.ExitGate;
import com.carparkinglot.entities.parkingspot.BikeParkingSpot;
import com.carparkinglot.entities.parkingspot.CarParkingSpot;
import com.carparkinglot.entities.parkingspot.ParkingSpot;

import com.carparkinglot.entities.parkingspotmanager.ParkingSpotManagerFactory;
import com.carparkinglot.entities.payment.PaymentMethod;
import com.carparkinglot.entities.vehicle.Bike;
import com.carparkinglot.entities.vehicle.Car;
import com.carparkinglot.entities.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class CarParkingLotSystem {
    public static void main(String[] args) throws IllegalAccessException {
        List<ParkingSpot> carParkingSpotList = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            ParkingSpot parkingSpot = new CarParkingSpot("ParkingSpot"+i);
            carParkingSpotList.add(parkingSpot);
        }
        List<ParkingSpot> bikeParkingSpotList = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            ParkingSpot parkingSpot = new BikeParkingSpot("ParkingSpot"+i);
            bikeParkingSpotList.add(parkingSpot);
        }

        ParkingSpotManagerFactory parkingSpotManagerFactory = new ParkingSpotManagerFactory(carParkingSpotList, bikeParkingSpotList);
        CostCalculatorFactory costCalculatorFactory = new CostCalculatorFactory();
        EntryGate entryGate = new EntryGate("Entry Gate", parkingSpotManagerFactory);
        ExitGate exitGate = new ExitGate("Exit Gate", parkingSpotManagerFactory, costCalculatorFactory);

        Vehicle car1 = new Car("KA877J356");
        Vehicle car2= new Car("KA877J357");
        Vehicle car3 = new Car("KA877J358");
        Vehicle car4 = new Car("KA877J359");

        Vehicle bike1 = new Bike("KA877J351");
        Vehicle bike2 = new Bike("KA877J352");
        Vehicle bike3 = new Bike("KA877J353");
        Vehicle bike4 = new Bike("KA877J354");

        Ticket ticketCar1 = entryGate.parkVehicle(car1);
        Ticket ticketBike1 = entryGate.parkVehicle(bike1);

        Ticket ticketCar2 = entryGate.parkVehicle(car2);
        Ticket ticketBike2 = entryGate.parkVehicle(bike2);
        
        exitGate.exitProcess(ticketBike1, PaymentMethod.CARD);
        exitGate.exitProcess(ticketCar2, PaymentMethod.CASH);

    }
}