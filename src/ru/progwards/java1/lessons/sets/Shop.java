package ru.progwards.java1.lessons.sets;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private List<Product> products;

    public Shop(List<Product> products) {
        this.products = new ArrayList<>(products);
    }
    public List<Product> getProducts() {
        return products;
    }
}
