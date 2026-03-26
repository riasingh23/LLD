package com.shoppingplatform.entities;

import java.util.List;

public class Order {
    private String orderId;
    private UserAccount userAccount;
    private List<Product> productList;

    public Order() {
    }

    public static class OrderBuilder {
        private final Order order = new Order();

        public OrderBuilder setOrderId(String orderId) {
            order.orderId = orderId;
            return this;
        }

        public OrderBuilder setUserAccount(UserAccount userAccount) {
            order.userAccount = userAccount;
            return this;
        }

        public OrderBuilder setProductList(List<Product> productList) {
            order.productList = productList;
            return this;
        }

        public Order build() {
            return order;
        }
    }

    public void orderDetails() {
        System.out.println("Orderid: " + orderId + ", User: " + userAccount);
    }

}
