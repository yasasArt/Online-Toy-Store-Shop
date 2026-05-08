package com.toystore.servlet;

import com.toystore.model.CartItem;
import com.toystore.model.Toy;
import com.toystore.service.CartService;
import com.toystore.service.ToyService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet {
    private final CartService cartService = new CartService();
    private final ToyService toyService = new ToyService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String username = (String) session.getAttribute("username");
        String toyId = request.getParameter("toyId");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        Toy toy = toyService.getToyById(toyId);

        if (toy == null) {
            response.sendRedirect("viewToys?error=toyNotFound");
            return;
        }

        if (quantity <= 0 || quantity > toy.getQuantity()) {
            response.sendRedirect("viewToys?error=invalidQuantity");
            return;
        }

        CartItem item = new CartItem(
                cartService.generateCartId(),
                username,
                toy.getToyId(),
                toy.getToyName(),
                toy.getPrice(),
                quantity
        );

        cartService.addToCart(item);

        response.sendRedirect("customer/cart.jsp?msg=added");
    }
}