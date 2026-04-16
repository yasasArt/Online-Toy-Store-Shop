package com.toystore.model;

public class ElectronicToy extends Toy {

    public ElectronicToy() {
        super();
    }

    public ElectronicToy(String toyId, String toyName, String category, String ageGroup, double price, int quantity) {
        super(toyId, toyName, category, ageGroup, price, quantity);
    }

    @Override
    public String displayDetails() {
        return "Electronic Toy -> " + getToyName();
    }
}