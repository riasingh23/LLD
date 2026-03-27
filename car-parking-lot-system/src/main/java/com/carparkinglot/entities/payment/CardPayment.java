package com.carparkinglot.entities.payment;

public class CardPayment implements Payment{

    @Override
    public void pay(int amount) {
        System.out.print("CardPayment done: " + amount);
    }
}
