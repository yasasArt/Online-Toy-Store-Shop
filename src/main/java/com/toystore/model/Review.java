package com.toystore.model;

public class Review {
    private String reviewId;
    private String customerUsername;
    private String toyId;
    private String toyName;
    private int rating;
    private String comment;
    private String reviewDate;

    public Review() {
    }

    public Review(String reviewId, String customerUsername, String toyId,
                  String toyName, int rating, String comment, String reviewDate) {
        this.reviewId = reviewId;
        this.customerUsername = customerUsername;
        this.toyId = toyId;
        this.toyName = toyName;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }

    public String toFileString() {
        return reviewId + "," + customerUsername + "," + toyId + "," +
                toyName + "," + rating + "," + comment + "," + reviewDate;
    }

    public static Review fromFileString(String line) {
        String[] data = line.split(",", -1);
        if (data.length < 7) {
            return null;
        }

        return new Review(
                data[0],
                data[1],
                data[2],
                data[3],
                Integer.parseInt(data[4]),
                data[5],
                data[6]
        );
    }

    public String getStarDisplay() {
        StringBuilder stars = new StringBuilder();
        for (int i = 0; i < rating; i++) {
            stars.append("★");
        }
        return stars.toString();
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public String getToyId() {
        return toyId;
    }

    public void setToyId(String toyId) {
        this.toyId = toyId;
    }

    public String getToyName() {
        return toyName;
    }

    public void setToyName(String toyName) {
        this.toyName = toyName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }
}