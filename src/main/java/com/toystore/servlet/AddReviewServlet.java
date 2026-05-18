package com.toystore.servlet;

import com.toystore.model.Review;
import com.toystore.model.Toy;
import com.toystore.model.User;
import com.toystore.service.ReviewService;
import com.toystore.service.ToyService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/addReview")
public class AddReviewServlet extends HttpServlet {

    private final ReviewService reviewService = new ReviewService();
    private final ToyService toyService = new ToyService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loggedUser") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        User loggedUser = (User) session.getAttribute("loggedUser");

        String toyId = request.getParameter("toyId");
        String ratingText = request.getParameter("rating");
        String comment = request.getParameter("comment");

        System.out.println("AddReviewServlet called");
        System.out.println("toyId = " + toyId);
        System.out.println("rating = " + ratingText);
        System.out.println("comment = " + comment);

        Toy toy = toyService.getToyById(toyId);

        if (toy == null) {
            System.out.println("Toy not found for review.");
            response.sendRedirect(request.getContextPath() + "/customer/addReview.jsp?error=toyNotFound");
            return;
        }

        Review review = new Review(
                reviewService.generateReviewId(),
                loggedUser.getUsername(),
                toy.getToyId(),
                toy.getToyName(),
                Integer.parseInt(ratingText),
                comment,
                LocalDate.now().toString()
        );

        boolean success = reviewService.addReview(review);

        if (success) {
            response.sendRedirect(request.getContextPath() + "/customer/addReview.jsp?msg=success");
        } else {
            response.sendRedirect(request.getContextPath() + "/customer/addReview.jsp?error=failed");
        }
    }
}