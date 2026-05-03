package com.toystore.servlet;

import com.toystore.model.Order;
import com.toystore.service.OrderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/updateOrder")
public class UpdateOrderServlet extends HttpServlet {
    private final OrderService orderService = new OrderService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");

        if (orderId != null && !orderId.trim().isEmpty()) {
            Order order = orderService.searchOrderById(orderId);
            request.setAttribute("order", order);

            if (order == null) {
                request.setAttribute("message", "Order not found.");
            }
        }

        request.getRequestDispatcher("updateOrder.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        String customerName = request.getParameter("customerName");
        String toyId = request.getParameter("toyId");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String status = request.getParameter("status");

        Order order = new Order(orderId, customerName, toyId, quantity, status);

        boolean success = orderService.updateOrder(order);

        if (success) {
            request.setAttribute("message", "Order updated successfully.");
            request.setAttribute("order", order);
        } else {
            request.setAttribute("message", "Order not found.");
        }

        request.getRequestDispatcher("updateOrder.jsp").forward(request, response);
    }
}