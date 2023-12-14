package org.example;

import java.util.ArrayList;

public class ProductManager {
    ArrayList<Product> productArrayList = new ArrayList<Product>();
    ArrayList<String> categoryArrayList = new ArrayList<String>();
    ArrayList<Order> orderArrayList = new ArrayList<Order>();
     public ProductManager(){
         categoryArrayList.add("Tools");
         categoryArrayList.add("Hardware");
     }
    public ArrayList<Product> GetInventory() {
        return productArrayList;
    }
    public ArrayList<String> GetCategories() { return categoryArrayList; }
    public ArrayList<Order> getCategoryArrayList() { return orderArrayList; }
}