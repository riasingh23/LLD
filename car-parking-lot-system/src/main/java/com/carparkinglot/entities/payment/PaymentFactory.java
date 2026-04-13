package com.carparkinglot.entities.payment;

public class PaymentFactory {
    public static PaymentStrategy getPayment(PaymentMethod paymentMethod) {
        return switch (paymentMethod) {
            case CASH -> new CashPaymentStrategy();
            case CARD -> new CardPaymentStrategy();
            default -> throw new IllegalArgumentException("Unsupported vehicle type: " + paymentMethod);
        };
    }
}
