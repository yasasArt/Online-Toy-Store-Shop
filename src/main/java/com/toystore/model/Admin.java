package com.toystore.model;

public class Admin extends User {
    private String permissionLevel;

    public Admin() {
        super();
        setRole("admin");
    }

    public Admin(String userId, String fullName, String email, String username,
                 String password, String phone, String address, String permissionLevel) {
        super(userId, fullName, email, username, password, "admin", phone, address);
        this.permissionLevel = permissionLevel;
    }

    public String getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(String permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    public boolean canManageToys() {
        return true;
    }

    public boolean canManageOrders() {
        return true;
    }

    public boolean canManageUsers() {
        return true;
    }
}