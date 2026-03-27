package com.carparkinglot.entities.payment;

public class PaymentFactory {
    public static Payment getPayment(PaymentMethod paymentMethod) {
        return switch (paymentMethod) {
            case CASH -> new CashPayment();
            case CARD -> new CardPayment();
            default -> throw new IllegalArgumentException("Unsupported vehicle type: " + paymentMethod);
        };
    }
}
