package org.example;

import java.util.ArrayList;

public class Customer extends User {
    private String username;

    public ArrayList<Product> productArrayList = new ArrayList<Product>();

    public Customer(String username) {
        this.username = username;
    }
}
