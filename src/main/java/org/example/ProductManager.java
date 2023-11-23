package org.example;

import java.util.ArrayList;

public class ProductManager {
    ArrayList<Product> productArrayList = new ArrayList<Product>();
    public ProductManager() {
        productArrayList.add(new Product("Nails", 25, "Hardware"));
        productArrayList.add(new Product("Hammer", 35, "Tool"));
        productArrayList.add(new Product("Saw", 45, "Tool"));
    }
}
