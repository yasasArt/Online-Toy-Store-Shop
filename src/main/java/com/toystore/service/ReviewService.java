package com.toystore.service;

import com.toystore.model.Review;
import com.toystore.util.FileUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewService {

    private String getFilePath() {
        return FileUtil.getFilePath("reviews.txt");
    }

    public boolean addReview(Review review) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFilePath(), true))) {
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
        try (BufferedReader reader = new BufferedReader(new FileReader(getFilePath()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Review review = Review.fromFileString(line);
                    if (review != null) reviews.add(review);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    public Review getReviewById(String reviewId) {
        for (Review review : getAllReviews()) {
            if (review.getReviewId().equalsIgnoreCase(reviewId)) return review;
        }
        return null;
    }

    public List<Review> getReviewsByToy(String toyId) {
        List<Review> result = new ArrayList<>();
        for (Review review : getAllReviews()) {
            if (review.getToyId().equalsIgnoreCase(toyId)) result.add(review);
        }
        return result;
    }

    public List<Review> getReviewsByCustomer(String username) {
        List<Review> result = new ArrayList<>();
        for (Review review : getAllReviews()) {
            if (review.getCustomerUsername().equalsIgnoreCase(username)) result.add(review);
        }
        return result;
    }

    public boolean updateReview(Review updatedReview) {
        List<Review> reviews = getAllReviews();
        boolean updated = false;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFilePath()))) {
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFilePath()))) {
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
        if (reviews.isEmpty()) return 0;
        int total = 0;
        for (Review r : reviews) total += r.getRating();
        return (double) total / reviews.size();
    }

    public String generateReviewId() {
        return "REV" + String.format("%03d", getAllReviews().size() + 1);
    }
}
