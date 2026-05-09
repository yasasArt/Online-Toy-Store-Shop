package com.toystore.servlet;

import com.toystore.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/deleteCustomer")
public class DeleteCustomerServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");

        if (username != null && !username.trim().isEmpty()) {
            userService.deleteUser(username);
        }

        response.sendRedirect(request.getContextPath() + "/admin/viewCustomers.jsp?msg=deleted");
    }
}