package org.example;

public class Shop {
    public Shop() {
        ProductManager productManager = new ProductManager();
        Admin admin = new Admin();
        //LoginPanel loginPanel = new LoginPanel(admin, productManager);
        EmployeePanel employeePanel = new EmployeePanel(productManager, admin);
//        CustomerPanel customerPanel = new CustomerPanel(productManager, admin);
    }
}
