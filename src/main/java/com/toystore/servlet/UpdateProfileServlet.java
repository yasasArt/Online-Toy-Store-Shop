package com.toystore.servlet;

import com.toystore.model.User;
import com.toystore.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/updateProfile")
public class UpdateProfileServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loggedUser") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        User oldUser = (User) session.getAttribute("loggedUser");

        User updatedUser = new User(
                request.getParameter("userId"),
                request.getParameter("fullName"),
                request.getParameter("email"),
                oldUser.getUsername(),
                request.getParameter("password"),
                "customer",
                request.getParameter("phone"),
                request.getParameter("address")
        );

        boolean success = userService.updateUser(updatedUser);

        if (success) {
            session.setAttribute("loggedUser", updatedUser);
            session.setAttribute("username", updatedUser.getUsername());
            session.setAttribute("role", updatedUser.getRole());

            response.sendRedirect("customer/profile.jsp?msg=updated");
        } else {
            response.sendRedirect("customer/profile.jsp?error=failed");
        }
    }
}