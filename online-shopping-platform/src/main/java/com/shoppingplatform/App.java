package com.shoppingplatform;

import com.shoppingplatform.dal.UserDatabase;
import com.shoppingplatform.entities.Order;
import com.shoppingplatform.entities.Product;
import com.shoppingplatform.entities.ProductFactory;
import com.shoppingplatform.entities.UserAccount;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("hi ria!");
        UserDatabase userDatabase = new UserDatabase();

        ProductFactory productFactory = new ProductFactory();

        Product clothing = productFactory.createProduct("clothing");
        clothing.displayInfo();
        Product electronic = productFactory.createProduct("electronic");
        electronic.displayInfo();

        UserAccount.UserAccountBuilder userAccountBuilder = new UserAccount.UserAccountBuilder("ria", "helloWorld");
        UserAccount userAccount = userAccountBuilder.build();
        System.out.println(userAccount);

        List<Product> productList = new ArrayList<>();
        productList.add(clothing);
        productList.add(electronic);

        Order order = new Order.OrderBuilder().setOrderId("OD123").setProductList(productList).setUserAccount(userAccount).build();
        order.orderDetails();

    }
}
