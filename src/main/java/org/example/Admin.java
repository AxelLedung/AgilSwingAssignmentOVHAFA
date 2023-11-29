package org.example;

import java.util.ArrayList;

public class Admin {
    ArrayList<User> UserList = new ArrayList<>();

    public void AddCustomer(String username, String password, int balance){
        UserList.add(new Customer(username, password, balance));
    }

    public void AddEmloyee(String username, String password){
        UserList.add(new Employee(username, password));
    }

    public void RemoveEmployee(int index){
        UserList.remove(index);
    }

    public void RemoveCustomer(int index){
        UserList.remove(index);
    }


}
