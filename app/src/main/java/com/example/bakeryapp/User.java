package com.example.bakeryapp;

public class User {
    private static User instance;
    private String username;
    private String email;
    private String password;

    private User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }
    public static synchronized User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

