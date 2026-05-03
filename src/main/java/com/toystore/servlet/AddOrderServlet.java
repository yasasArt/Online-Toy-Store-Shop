package com.toystore.servlet;

import com.toystore.model.Order;
import com.toystore.service.OrderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/addOrder")
public class AddOrderServlet extends HttpServlet {
    private final OrderService orderService = new OrderService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        String customerName = request.getParameter("customerName");
        String toyId = request.getParameter("toyId");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String status = request.getParameter("status");

        Order order = new Order(orderId, customerName, toyId, quantity, status);

        boolean success = orderService.addOrder(order);

        if (success) {
            request.setAttribute("message", "Order added successfully.");
        } else {
            request.setAttribute("message", "Order ID already exists.");
        }

        request.getRequestDispatcher("addOrder.jsp").forward(request, response);
    }
}