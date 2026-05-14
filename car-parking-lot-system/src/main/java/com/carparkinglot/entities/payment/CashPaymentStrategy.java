package com.carparkinglot.entities.payment;

public class CashPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.print("CashPaymentStrategy done: " + amount);
    }
}
