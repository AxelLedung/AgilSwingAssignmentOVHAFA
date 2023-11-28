package org.example;

import java.util.ArrayList;

public class ProductManager {
    ArrayList<Product> productArrayList = new ArrayList<Product>();

     public ProductManager(){
         productArrayList.add(new Product("Thing 1", 20,"Hardware"));
         productArrayList.add(new Product("Thing 2", 200,"Hardware"));
         productArrayList.add(new Product("Thing 3", 12,"Hardware"));
         productArrayList.add(new Product("Thing 4", 1,"Tool"));
         productArrayList.add(new Product("Thing 5", 53,"Tool"));
         productArrayList.add(new Product("Thing 6", 67,"Tool"));
         productArrayList.add(new Product("Thing 7", 80,"Tool"));
         productArrayList.add(new Product("Thing 8", 90,"Tool"));
         productArrayList.add(new Product("Thing 9", 34,"Tool"));
         productArrayList.add(new Product("Thing 10", 32,"Tool"));
         productArrayList.add(new Product("Thing 11", 21,"Tool"));
         productArrayList.add(new Product("Thing 12", 9999,"Hardware"));
         productArrayList.add(new Product("Nails", 25, "Hardware"));
         productArrayList.add(new Product("Hammer", 35, "Tool"));
         productArrayList.add(new Product("Saw", 45, "Tool"));
     }

    public ArrayList<Product> getProductList() {
        return productArrayList;
    }
}
