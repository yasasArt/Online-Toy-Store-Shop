package com.toystore.service;

import com.toystore.model.Review;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewService {
    private static final String FILE_PATH = "data/reviews.txt";

    public ReviewService() {
        createFileIfNotExists();
    }

    private void createFileIfNotExists() {
        try {
            File file = new File(FILE_PATH);
            File parent = file.getParentFile();

            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean addReview(Review review) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(review.toFileString());
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Review> getAllReviews() {
        List<Review> reviews = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Review review = Review.fromFileString(line);
                    if (review != null) {
                        reviews.add(review);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return reviews;
    }

    public Review getReviewById(String reviewId) {
        for (Review review : getAllReviews()) {
            if (review.getReviewId().equalsIgnoreCase(reviewId)) {
                return review;
            }
        }

        return null;
    }

    public List<Review> getReviewsByToy(String toyId) {
        List<Review> toyReviews = new ArrayList<>();

        for (Review review : getAllReviews()) {
            if (review.getToyId().equalsIgnoreCase(toyId)) {
                toyReviews.add(review);
            }
        }

        return toyReviews;
    }

    public List<Review> getReviewsByCustomer(String username) {
        List<Review> customerReviews = new ArrayList<>();

        for (Review review : getAllReviews()) {
            if (review.getCustomerUsername().equalsIgnoreCase(username)) {
                customerReviews.add(review);
            }
        }

        return customerReviews;
    }

    public boolean updateReview(Review updatedReview) {
        List<Review> reviews = getAllReviews();
        boolean updated = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Review review : reviews) {
                if (review.getReviewId().equalsIgnoreCase(updatedReview.getReviewId())) {
                    writer.write(updatedReview.toFileString());
                    updated = true;
                } else {
                    writer.write(review.toFileString());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return updated;
    }

    public boolean deleteReview(String reviewId) {
        List<Review> reviews = getAllReviews();
        boolean deleted = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Review review : reviews) {
                if (!review.getReviewId().equalsIgnoreCase(reviewId)) {
                    writer.write(review.toFileString());
                    writer.newLine();
                } else {
                    deleted = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return deleted;
    }

    public double getAverageRatingByToy(String toyId) {
        List<Review> reviews = getReviewsByToy(toyId);

        if (reviews.isEmpty()) {
            return 0;
        }

        int total = 0;

        for (Review review : reviews) {
            total += review.getRating();
        }

        return (double) total / reviews.size();
    }

    public String generateReviewId() {
        int count = getAllReviews().size() + 1;
        return "REV" + String.format("%03d", count);
    }
}