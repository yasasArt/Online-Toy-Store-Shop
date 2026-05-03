package com.toystore.servlet;

import com.toystore.service.OrderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/deleteOrder")
public class DeleteOrderServlet extends HttpServlet {
    private final OrderService orderService = new OrderService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");

        boolean success = orderService.deleteOrder(orderId);

        if (success) {
            request.setAttribute("message", "Order deleted successfully.");
        } else {
            request.setAttribute("message", "Order not found.");
        }

        request.getRequestDispatcher("deleteOrder.jsp").forward(request, response);
    }
}