package org.example;

public class Product {
    String name;
    int cost;
    String category;
    public Product(String name, int cost, String category) {
        this.name = name;
        this.cost = cost;
        this.category = category;
    }
    public String GetDescription() {
        return name + ", " + cost + ", " + category;
    }
}
