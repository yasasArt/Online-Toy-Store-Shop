package com.toystore.servlet;

import com.toystore.service.OrderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/viewOrders")
public class ViewOrderServlet extends HttpServlet {
    private final OrderService orderService = new OrderService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("orderList", orderService.getAllOrders());
        request.getRequestDispatcher("viewOrders.jsp").forward(request, response);
    }
}