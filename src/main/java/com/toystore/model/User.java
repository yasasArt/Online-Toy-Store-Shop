package com.toystore.model;

public class User {
    private String userId;
    private String fullName;
    private String email;
    private String username;
    private String password;
    private String role;
    private String phone;
    private String address;

    public User() {
    }

    public User(String userId, String fullName, String email, String username,
                String password, String role, String phone, String address) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.phone = phone;
        this.address = address;
    }

    public String toFileString() {
        return userId + "," + fullName + "," + email + "," + username + "," +
                password + "," + role + "," + phone + "," + address;
    }

    public static User fromFileString(String line) {
        String[] data = line.split(",", -1);
        if (data.length < 8) {
            return null;
        }

        return new User(
                data[0], data[1], data[2], data[3],
                data[4], data[5], data[6], data[7]
        );
    }

    public String getDashboardPage() {
        if ("admin".equalsIgnoreCase(role)) {
            return "admin/adminDashboard.jsp";
        }
        return "customer/customerDashboard.jsp";
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public boolean checkPassword(String password) {
        return this.password != null && this.password.equals(password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}