package org.example;

public class User {
    private int id;
    private String userName;
    private String password;

    public User(int id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }
    public String GetCSV() {
        return userName + "," + password;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return userName;
    }
    public void setUsername(String userName) {
        this.userName = userName;
    }
    public String getDescription() {
        return "Id: " + id + ", Username: " + userName + ", Password: " + password;
    }
    public String getCSV () {
        return id + "," + userName + "," + password;
    }
    public String getPassword() {
        return password;
    }
}
