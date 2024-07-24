package com.example.codingwebapp.model;

public class Client {
    private String id;
    private String role;
    public Client (){}
    public Client(String id, String role){
        this.id = id;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
