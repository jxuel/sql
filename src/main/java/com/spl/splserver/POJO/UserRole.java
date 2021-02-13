package com.spl.splserver.POJO;

public enum UserRole {
    user ("USER"),
    admin ("ADMIN"),
    general ("GENERAL");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }

    public boolean equalsRole(String otherRole) {
        return this.role.equals(otherRole);
    }

    public String toString() {
        return this.role;
    }
}
