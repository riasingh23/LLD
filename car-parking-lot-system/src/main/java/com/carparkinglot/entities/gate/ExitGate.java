package com.carparkinglot.entities.gate;

import com.carparkinglot.entities.Ticket;
import com.carparkinglot.entities.costcalculator.CostCalculator;
import com.carparkinglot.entities.costcalculator.CostCalculatorFactory;
import com.carparkinglot.entities.parkingspotmanager.ParkingSpotManager;
import com.carparkinglot.entities.parkingspotmanager.ParkingSpotManagerFactory;
import com.carparkinglot.entities.payment.PaymentStrategy;
import com.carparkinglot.entities.payment.PaymentFactory;
import com.carparkinglot.entities.payment.PaymentMethod;

public class ExitGate implements Gate{
    private final String gateId;
    ParkingSpotManagerFactory parkingSpotManagerFactory;
    CostCalculatorFactory costCalculatorFactory;

    public ExitGate(String gateId, ParkingSpotManagerFactory parkingSpotManagerFactory, CostCalculatorFactory costCalculatorFactory) {
        this.gateId = gateId;
        this.parkingSpotManagerFactory = parkingSpotManagerFactory;
        this.costCalculatorFactory = costCalculatorFactory;
    }

    public void exitProcess(Ticket ticket, PaymentMethod paymentMethod) {
        int amount = calculatePrice(ticket);
        System.out.print(ticket.getVehicle().getVehicleType() + " ");
        makePayment(amount, paymentMethod);
        unparkVehicle(ticket);
        System.out.println();
    }

    private void makePayment (int amount, PaymentMethod paymentMethod) {
        PaymentStrategy paymentStrategy = PaymentFactory.getPayment(paymentMethod);
        paymentStrategy.pay(amount);
    }

    private void unparkVehicle (Ticket ticket) {
        ParkingSpotManager parkingSpotManager = parkingSpotManagerFactory.getParkingAreaManager(ticket.getVehicle().getVehicleType());
        parkingSpotManager.removeVehicle(ticket.getVehicle());
    }

    private int calculatePrice(Ticket ticket) {
        CostCalculator costCalculator = costCalculatorFactory.getCostCalculator(ticket.getVehicle().getVehicleType());
        return costCalculator.calculatingCost(ticket);
    }

}
