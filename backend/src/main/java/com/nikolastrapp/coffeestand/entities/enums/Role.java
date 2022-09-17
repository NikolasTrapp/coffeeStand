package com.nikolastrapp.coffeestand.entities.enums;

public enum Role {
    ADMIN("Admin"),
    USER("User");

    private String str;

    private Role(String str) {
        this.str = str;
    }

    public String getRole() {
        return str;
    }
}
