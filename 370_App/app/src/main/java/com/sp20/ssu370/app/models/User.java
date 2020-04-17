package com.sp20.ssu370.app.models;

public class User {
    private int id;
    private String email, name;

    public User(int id, String email, String name){
        this.id = id;
        this.email = email;
        this.name = name;

    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
