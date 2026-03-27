package com.shoppingplatform.entities;
import java.util.Objects;

public class ProductFactory {

    public Product createProduct(String category){
        switch (category.toLowerCase()) {
            case "electronic":
                return new ElectronicProduct();
            case "clothing":
                return new ClothingProduct();
            default:
                throw new IllegalArgumentException("Invalid Category name: " + category);
        }
    }

}
