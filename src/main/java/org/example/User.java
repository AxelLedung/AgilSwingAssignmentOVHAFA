package org.example;

public class User {
    private String userName;
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    public String getUsername() {
        return userName;
    }
    public void setUsername(String userName) {
        this.userName = userName;
    }
    public String getDescription() {
        return "Username: " + userName + ", Password: " + password;
    }
    public String getCSV () {
        return userName + "," + password;
    }
    public String getPassword() {
        return password;
    }
}
