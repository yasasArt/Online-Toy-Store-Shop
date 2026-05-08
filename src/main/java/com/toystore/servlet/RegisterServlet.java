package com.toystore.servlet;

import com.toystore.model.User;
import com.toystore.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        String role = "customer";
        String userId = userService.generateUserId(role);

        User user = new User(userId, fullName, email, username, password, role, phone, address);

        boolean success = userService.addUser(user);

        if (success) {
            response.sendRedirect("login.jsp?success=registered");
        } else {
            request.setAttribute("error", "Username already exists!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}