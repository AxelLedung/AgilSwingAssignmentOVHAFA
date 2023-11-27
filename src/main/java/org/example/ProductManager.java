package org.example;

import java.util.ArrayList;

public class ProductManager {
     ArrayList<Product> productList = new ArrayList<>();

     public ProductManager(){
         productList.add(new Product(1,"Thing 1", 20));
         productList.add(new Product(2,"Thing 2", 200));
         productList.add(new Product(3,"Thing 3", 12));
         productList.add(new Product(4,"Thing 4", 1));
         productList.add(new Product(5,"Thing 5", 53));
         productList.add(new Product(6,"Thing 6", 67));
         productList.add(new Product(7,"Thing 7", 80));
         productList.add(new Product(8,"Thing 8", 90));
         productList.add(new Product(9,"Thing 9", 34));
         productList.add(new Product(10,"Thing 10", 32));
         productList.add(new Product(11,"Thing 11", 21));
         productList.add(new Product(12,"Thing 12", 9999));
     }

    public ArrayList<Product> getProductList() {
        return productList;
    }
}
