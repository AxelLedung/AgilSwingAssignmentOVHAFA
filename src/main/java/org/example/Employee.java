package org.example;

public class Employee extends User{
    private  static int nextEmployeeId = 1;

    public Employee(int id, String userName, String password) {
        super(nextEmployeeId, userName, password);
        nextEmployeeId++;
    }
}
