package com.toystore.model;

public class Customer extends User {
    private String membershipType;

    public Customer() {
        super();
        setRole("customer");
        this.membershipType = "Regular";
    }

    public Customer(String userId, String fullName, String email, String username,
                    String password, String phone, String address, String membershipType) {
        super(userId, fullName, email, username, password, "customer", phone, address);
        this.membershipType = membershipType;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public double getDiscountRate() {
        if ("Gold".equalsIgnoreCase(membershipType)) {
            return 0.10;
        } else if ("Silver".equalsIgnoreCase(membershipType)) {
            return 0.05;
        }
        return 0.00;
    }
}