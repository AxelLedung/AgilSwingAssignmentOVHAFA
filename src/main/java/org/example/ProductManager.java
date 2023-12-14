package org.example;

import java.util.ArrayList;

public class ProductManager {
    ArrayList<Product> productArrayList = new ArrayList<Product>();
    ArrayList<String> categoryArrayList = new ArrayList<String>();
    ArrayList<Order> orderArrayList = new ArrayList<Order>();
    ArrayList<Order> refundArrayList = new ArrayList<>();
     public ProductManager(){
     }
    public ArrayList<Product> GetInventory() {
        return productArrayList;
    }
    public ArrayList<String> GetCategories() { return categoryArrayList; }
    public ArrayList<Order> getCategoryArrayList() { return orderArrayList; }
    public ArrayList<Order> getOrders(){
        return orderArrayList;
    }
}