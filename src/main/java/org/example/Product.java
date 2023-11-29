package org.example;

public class Product {
    String name;
    int cost;
    String category;
    int quantity;
    public Product(String name, int cost, String category, int quantity) {
        this.name = name;
        this.cost = cost;
        this.category = category;
        this.quantity = quantity;
    }
    private boolean checkQuantity(int quantity){
        if(this.quantity > quantity) {
            this.quantity -= quantity;
            return true;
        }
        else{
            return false;
        }
    }
    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }
    public String GetDescription() {
        return name + ", " + cost + ", " + category + ", " + quantity;
    }
}