package org.example;

import java.util.ArrayList;

public class Product {
    private String name;
    private int cost;
    private String category;
    private int quantity;
    private ArrayList<Review> reviewArrayList = new ArrayList<Review>();

    public Product(String name, int cost, String category, int quantity) {
        this.name = name;
        this.cost = cost;
        this.category = category;
        this.quantity = quantity;
    }
    public boolean checkQuantity(int quantity){
        if(this.quantity > 0) {
            this.quantity--;
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
        return name + ", " + category + ", Cost: " + cost;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}