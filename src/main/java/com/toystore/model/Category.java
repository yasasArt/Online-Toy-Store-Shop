package com.toystore.model;

public class Category {
    private String categoryId;
    private String categoryName;
    private String description;

    public Category() {
    }

    public Category(String categoryId, String categoryName, String description) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
    }

    public String toFileString() {
        return categoryId + "," + categoryName + "," + description;
    }

    public static Category fromFileString(String line) {
        String[] data = line.split(",", -1);

        if (data.length < 3) {
            return null;
        }

        return new Category(data[0], data[1], data[2]);
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}