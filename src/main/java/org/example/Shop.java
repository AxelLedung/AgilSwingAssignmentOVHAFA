package org.example;

public class Shop {
    User currentUser = new Customer("Axel");
    public Shop() {
        ProductManager productManager = new ProductManager();
        CustomerPanel customerPanel = new CustomerPanel(productManager);

    }
}
