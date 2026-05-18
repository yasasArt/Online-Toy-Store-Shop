package com.toystore.servlet;

import com.toystore.model.Review;
import com.toystore.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
 
public class ReviewService {

    public boolean addReview(Review review) {
        String sql = "INSERT INTO reviews (review_id, customer_username, toy_id, toy_name, rating, comment, review_date) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, review.getReviewId());
            ps.setString(2, review.getCustomerUsername());
            ps.setString(3, review.getToyId());
            ps.setString(4, review.getToyName());
            ps.setInt(5, review.getRating());
            ps.setString(6, review.getComment());
            ps.setString(7, review.getReviewDate());

            int rows = ps.executeUpdate();
            System.out.println("Review inserted rows: " + rows);

            return rows > 0;

        } catch (Exception e) {
            System.out.println("Review insert failed.");
            e.printStackTrace();
        }

        return false;
    }

    public List<Review> getAllReviews() {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT * FROM reviews";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                reviews.add(new Review(
                        rs.getString("review_id"),
                        rs.getString("customer_username"),
                        rs.getString("toy_id"),
                        rs.getString("toy_name"),
                        rs.getInt("rating"),
                        rs.getString("comment"),
                        rs.getString("review_date")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return reviews;
    }

    public String generateReviewId() {
        String sql = "SELECT COUNT(*) FROM reviews";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return "REV" + String.format("%03d", rs.getInt(1) + 1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "REV001";
    }
}