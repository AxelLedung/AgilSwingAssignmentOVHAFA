package org.example;

import java.util.ArrayList;

public class ProductManager {
    ArrayList<Product> productArrayList = new ArrayList<Product>();

     public ProductManager(){}
    public ArrayList<Product> GetInventory() {
        return productArrayList;
    }
}