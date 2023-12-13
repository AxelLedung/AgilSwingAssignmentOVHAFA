package org.example;

import java.util.ArrayList;

public class ProductManager {
    ArrayList<Product> productArrayList = new ArrayList<Product>();
    ArrayList<Category> categoryArrayList = new ArrayList<Category>();
    ArrayList<Order> orderArrayList = new ArrayList<Order>();
    ArrayList<Order> refundArrayList = new ArrayList<>();
     public ProductManager(){
         Category tools = new Category("Tools");
         Category hardware = new Category("Hardware");
     }
    public ArrayList<Product> GetInventory() {
        return productArrayList;
    }
    public ArrayList<Category> GetCategories() { return categoryArrayList; }
    public ArrayList<Order> getCategoryArrayList() { return orderArrayList; }
}