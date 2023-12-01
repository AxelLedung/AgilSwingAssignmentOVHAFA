package org.example;

import java.util.ArrayList;

public class Admin {
    ArrayList<Employee> EmployeeList = new ArrayList<>();
    ArrayList<Customer> CustomerList = new ArrayList<>();
    public Admin(){
        EmployeeList.add(new Employee("Kent", "1234"));
        EmployeeList.add(new Employee("Freddie", "1234"));
        EmployeeList.add(new Employee("Gunvald", "1234"));
        CustomerList.add(new Customer("Ingvar","1234",50000));
        CustomerList.add(new Customer("Ulla","1234",35000));
        CustomerList.add(new Customer("Sven","1234",123));
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
