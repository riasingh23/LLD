package com.carparkinglot.entities.payment;

import java.util.Map;

public class PaymentFactory {
    private final Map<PaymentMethod, PaymentStrategy> paymentMethodPaymentStrategyMap;

    public PaymentFactory(Map<PaymentMethod, PaymentStrategy> paymentMethodPaymentStrategyMap) {
        this.paymentMethodPaymentStrategyMap = paymentMethodPaymentStrategyMap;
    }

    public PaymentStrategy getPayment(PaymentMethod paymentMethod) {
        PaymentStrategy paymentStrategy = paymentMethodPaymentStrategyMap.get(paymentMethod);
        if (paymentStrategy == null) {
            throw new IllegalArgumentException("Unsupported payment type");
        }
        return paymentStrategy;
    }
}
