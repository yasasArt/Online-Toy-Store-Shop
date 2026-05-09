package com.toystore.servlet;

import com.toystore.model.User;
import com.toystore.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/deleteProfile")
public class DeleteProfileServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loggedUser") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        User loggedUser = (User) session.getAttribute("loggedUser");

        boolean deleted = userService.deleteUser(loggedUser.getUsername());

        if (deleted) {
            session.invalidate();
            response.sendRedirect("register.jsp?msg=accountDeleted");
        } else {
            response.sendRedirect("customer/profile.jsp?error=failed");
        }
    }
}