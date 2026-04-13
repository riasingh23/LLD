package com.carparkinglot.entities.payment;

public class CardPaymentStrategy implements PaymentStrategy {

    @Override
    public void pay(int amount) {
        System.out.print("CardPaymentStrategy done: " + amount);
    }
}
