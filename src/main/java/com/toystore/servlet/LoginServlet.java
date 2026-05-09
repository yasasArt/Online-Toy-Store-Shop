package com.toystore.servlet;

import com.toystore.model.User;
import com.toystore.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.login(username, password);

        if (user == null) {
            request.setAttribute("error", "Invalid username or password!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("loggedUser", user);
        session.setAttribute("username", user.getUsername());
        session.setAttribute("role", user.getRole());

        if ("admin".equalsIgnoreCase(user.getRole())) {
            response.sendRedirect("admin/adminDashboard.jsp");
        } else if ("customer".equalsIgnoreCase(user.getRole())) {
            response.sendRedirect("customer/customerDashboard.jsp");
        } else {
            session.invalidate();
            request.setAttribute("error", "Unauthorized user role!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}