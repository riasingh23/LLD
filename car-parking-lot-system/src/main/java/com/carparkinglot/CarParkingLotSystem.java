package com.carparkinglot;

import com.carparkinglot.entities.Ticket;
import com.carparkinglot.entities.costcalculator.BikeCostCalculator;
import com.carparkinglot.entities.costcalculator.CarCostCalculator;
import com.carparkinglot.entities.costcalculator.CostCalculator;
import com.carparkinglot.entities.costcalculator.CostCalculatorFactory;
import com.carparkinglot.entities.gate.EntryGate;
import com.carparkinglot.entities.gate.ExitGate;
import com.carparkinglot.entities.parkingspot.BikeParkingSpot;
import com.carparkinglot.entities.parkingspot.CarParkingSpot;
import com.carparkinglot.entities.parkingspot.ParkingSpot;
import com.carparkinglot.entities.parkingspotmanager.ParkingSpotManagerFactory;
import com.carparkinglot.entities.payment.*;
import com.carparkinglot.entities.vehicle.Vehicle;
import com.carparkinglot.enums.VehicleType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<VehicleType, CostCalculator> costCalculatorMap = new HashMap<>();
        costCalculatorMap.put(VehicleType.CAR, new CarCostCalculator());
        costCalculatorMap.put(VehicleType.BIKE, new BikeCostCalculator());
        CostCalculatorFactory costCalculatorFactory = new CostCalculatorFactory(costCalculatorMap);

        Map<PaymentMethod, PaymentStrategy> paymentMethodPaymentStrategyMap = new HashMap<>();
        paymentMethodPaymentStrategyMap.put(PaymentMethod.CARD, new CardPaymentStrategy());
        paymentMethodPaymentStrategyMap.put(PaymentMethod.CASH, new CashPaymentStrategy());
        PaymentFactory paymentFactory = new PaymentFactory(paymentMethodPaymentStrategyMap);

        EntryGate entryGate = new EntryGate("Entry Gate", parkingSpotManagerFactory);
        ExitGate exitGate = new ExitGate("Exit Gate", parkingSpotManagerFactory, costCalculatorFactory, paymentFactory);

        Vehicle car1 = new Vehicle("KA877J356", VehicleType.CAR);
        Vehicle car2= new Vehicle("KA877J357", VehicleType.CAR);
        Vehicle car3 = new Vehicle("KA877J358", VehicleType.CAR);
        Vehicle car4 = new Vehicle("KA877J359", VehicleType.CAR);

        Vehicle bike1 = new Vehicle("KA877J351", VehicleType.BIKE);
        Vehicle bike2 = new Vehicle("KA877J352", VehicleType.BIKE);
        Vehicle bike3 = new Vehicle("KA877J353", VehicleType.BIKE);
        Vehicle bike4 = new Vehicle("KA877J354", VehicleType.BIKE);

        Ticket ticketCar1 = entryGate.parkVehicle(car1);
        Ticket ticketBike1 = entryGate.parkVehicle(bike1);

        Ticket ticketCar2 = entryGate.parkVehicle(car2);
        Ticket ticketBike2 = entryGate.parkVehicle(bike2);
        
        exitGate.exitProcess(ticketBike1, PaymentMethod.CARD);
        exitGate.exitProcess(ticketCar2, PaymentMethod.CASH);

    }
}