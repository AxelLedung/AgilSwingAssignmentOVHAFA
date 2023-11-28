package org.example;

public class User {
    private int id;
    private String userName;
    private String password;

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return "Id: " + id + ", Username: " + username + ", Password: " + password;
    }
    public String getCSV () {
        return id + "," + username + "," + password;
    }

    public String getPassword() {
        return password;
    }
}
