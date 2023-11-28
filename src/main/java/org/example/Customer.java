package org.example;

import java.util.ArrayList;

public class Customer extends User {
    public ArrayList<Product> productArrayList = new ArrayList<Product>();

    public Customer(int id, String username, String password) {
        super(id, username, password);
    }
}
