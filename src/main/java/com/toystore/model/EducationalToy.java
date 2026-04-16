package com.toystore.model;

public class EducationalToy extends Toy {

    public EducationalToy() {
        super();
    }

    public EducationalToy(String toyId, String toyName, String category, String ageGroup, double price, int quantity) {
        super(toyId, toyName, category, ageGroup, price, quantity);
    }

    @Override
    public String displayDetails() {
        return "Educational Toy -> " + getToyName();
    }
}