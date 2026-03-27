package com.carparkinglot.entities.payment;

public class CashPayment implements Payment{
    @Override
    public void pay(int amount) {
        System.out.print("CashPayment done: " + amount);
    }
}
