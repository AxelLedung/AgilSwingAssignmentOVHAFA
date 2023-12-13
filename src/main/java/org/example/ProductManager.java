package org.example;

import java.util.ArrayList;

public class ProductManager {
    ArrayList<Product> productArrayList = new ArrayList<Product>();
    ArrayList<String> categoryArrayList = new ArrayList<String>();
    ArrayList<Order> orderArrayList = new ArrayList<Order>();
    ArrayList<Order> refundList = new ArrayList<Order>();
     public ProductManager(){
         categoryArrayList.add("Tools");
         categoryArrayList.add("Hardware");
         productArrayList.add(new Product("Handdrill", 20,"Tool", 5));
         productArrayList.add(new Product("Steel", 200,"Hardware", 25));
         productArrayList.add(new Product("Nuts M12", 12,"Hardware", 3));
         productArrayList.add(new Product("Drill", 30,"Tool", 5));
         productArrayList.add(new Product("Screwdriver", 53,"Tool", 2));
         productArrayList.add(new Product("Socketwrench M8", 67,"Tool", 3));
         productArrayList.add(new Product("Socketwrench M9", 80,"Tool", 8));
         productArrayList.add(new Product("Socketwrench M10", 90,"Tool", 9));
         productArrayList.add(new Product("Socketwrench M11", 34,"Tool", 12));
         productArrayList.add(new Product("Socketwrench M12", 32,"Tool", 13));
         productArrayList.add(new Product("Socketwrench M13", 21,"Tool", 13));
         productArrayList.add(new Product("Sheet Metal", 9999,"Hardware", 13));
         productArrayList.add(new Product("Nails", 25, "Hardware", 30));
         productArrayList.add(new Product("Hammer", 35, "Tool", 12));
         productArrayList.add(new Product("Saw", 45, "Tool", 5));
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