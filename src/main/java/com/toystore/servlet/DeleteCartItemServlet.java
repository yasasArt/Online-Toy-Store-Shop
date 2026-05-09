package com.toystore.servlet;

import com.toystore.service.CartService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/deleteCartItem")
public class DeleteCartItemServlet extends HttpServlet {

    private final CartService cartService = new CartService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String cartId = request.getParameter("cartId");

        if (cartId != null && !cartId.trim().isEmpty()) {
            cartService.removeCartItem(cartId);
        }

        response.sendRedirect(request.getContextPath() + "/customer/cart.jsp?msg=deleted");
    }
}