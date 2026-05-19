package com.toystore.controller;

import com.toystore.model.*;
import com.toystore.service.ReviewService;
import com.toystore.service.ToyService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * Handles: /addReview
 * Replaces: AddReviewServlet
 */
@Controller
public class ReviewController {

    private final ReviewService reviewService = new ReviewService();
    private final ToyService    toyService    = new ToyService();

    @PostMapping("/addReview")
    public String addReview(@RequestParam String toyId,
                            @RequestParam int rating,
                            @RequestParam String comment,
                            HttpSession session) {

        User loggedUser = (User) session.getAttribute("loggedUser");
        if (loggedUser == null) return "redirect:/login.jsp";

        Toy toy = toyService.getToyById(toyId);
        if (toy == null) {
            return "redirect:/customer/addReview.jsp?error=toyNotFound";
        }

        Review review = new Review(
                reviewService.generateReviewId(),
                loggedUser.getUsername(),
                toy.getToyId(),
                toy.getToyName(),
                rating,
                comment,
                LocalDate.now().toString()
        );

        boolean success = reviewService.addReview(review);

        if (success) {
            return "redirect:/customer/addReview.jsp?msg=success";
        } else {
            return "redirect:/customer/addReview.jsp?error=failed";
        }
    }
}
