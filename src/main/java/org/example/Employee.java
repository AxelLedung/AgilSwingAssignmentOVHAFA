package org.example;

public class Employee extends User{
    private  static int nextEmployeeId = 1;

    public Employee(int id, String username, String password) {
        super(nextEmployeeId, username, password);
        nextEmployeeId++;
    }
}
