package org.example;

import java.util.ArrayList;

public class Admin {
    ArrayList<Employee> EmployeeList = new ArrayList<>();
    ArrayList<Customer> CustomerList = new ArrayList<>();
    ArrayList<Card> CardList = new ArrayList<>();
    public Admin(){
    }
    public void AddCustomer(String username, String password, int balance){
        CustomerList.add(new Customer(username, password, balance));
    }
    public void AddEmloyee(String username, String password){
        EmployeeList.add(new Employee(username, password));

    }

    public void RemoveEmployee(int index){
        EmployeeList.remove(index);
    }

    public void RemoveCustomer(int index){
        CustomerList.remove(index);
    }
}
