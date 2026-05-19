package com.toystore.controller;

import com.toystore.model.CartItem;
import com.toystore.model.Toy;
import com.toystore.service.CartService;
import com.toystore.service.ToyService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles: /addToCart, /deleteCartItem
 * Replaces: AddToCartServlet, DeleteCartItemServlet
 */
@Controller
public class CartController {

    private final CartService cartService = new CartService();
    private final ToyService  toyService  = new ToyService();

    // ---------------------------------------------------------------
    // ADD ITEM TO CART
    // ---------------------------------------------------------------
    @PostMapping("/addToCart")
    public String addToCart(@RequestParam String toyId,
                            @RequestParam int quantity,
                            HttpSession session) {

        String username = (String) session.getAttribute("username");
        if (username == null) return "redirect:/login.jsp";

        Toy toy = toyService.getToyById(toyId);
        if (toy == null) return "redirect:/viewToys?error=toyNotFound";

        if (quantity <= 0 || quantity > toy.getQuantity()) {
            return "redirect:/viewToys?error=invalidQuantity";
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
        return "redirect:/customer/cart.jsp?msg=added";
    }

    // ---------------------------------------------------------------
    // REMOVE ITEM FROM CART
    // ---------------------------------------------------------------
    @GetMapping("/deleteCartItem")
    public String deleteCartItem(@RequestParam(required = false) String cartId) {
        if (cartId != null && !cartId.trim().isEmpty()) {
            cartService.removeCartItem(cartId);
        }
        return "redirect:/customer/cart.jsp?msg=deleted";
    }
}
