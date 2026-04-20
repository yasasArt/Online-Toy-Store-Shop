package com.toystore.servlet;

import com.toystore.model.User;
import com.toystore.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/register")   // ✅ FIXED
public class RegisterServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = "customer";

        User user = new User(fullName, email, username, password, role);

        boolean success = userService.registerUser(user);

        if (success) {
            request.setAttribute("message", "Registration successful. Please login.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "Username already exists.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}