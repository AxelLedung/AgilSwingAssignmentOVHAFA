package org.example;

public class Product {
    int quantity;
    String name;
    int cost;
    String category;

    public Product(String name, int cost, String category) {
        this.name = name;
        this.cost = cost;
        this.category = category;
    }

    private boolean checkQuantity(int quantity){
        if(this.quantity < quantity) {
            this.quantity -= quantity;
            return true;
        }
        else{
            return false;
        }
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String GetDescription() {
        return name + ", " + cost + ", " + category;
    }
}
