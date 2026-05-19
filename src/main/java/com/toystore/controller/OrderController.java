package com.toystore.controller;

import com.toystore.model.*;
import com.toystore.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

/**
 * Handles: /placeOrder
 * Replaces: PlaceOrderServlet
 */
@Controller
public class OrderController {

    private final CartService    cartService    = new CartService();
    private final OrderService   orderService   = new OrderService();
    private final PaymentService paymentService = new PaymentService();
    private final ToyService     toyService     = new ToyService();

    @PostMapping("/placeOrder")
    public String placeOrder(@RequestParam String deliveryAddress,
                             @RequestParam String paymentMethod,
                             HttpSession session) {

        User loggedUser = (User) session.getAttribute("loggedUser");
        if (loggedUser == null) return "redirect:/login.jsp";

        String username = loggedUser.getUsername();
        List<CartItem> cartItems = cartService.getCartByCustomer(username);

        if (cartItems == null || cartItems.isEmpty()) {
            return "redirect:/customer/cart.jsp?error=emptyCart";
        }

        for (CartItem item : cartItems) {
            boolean stockUpdated = toyService.reduceStock(item.getToyId(), item.getQuantity());
            if (!stockUpdated) {
                return "redirect:/customer/cart.jsp?error=stockNotAvailable";
            }

            String orderId = orderService.generateOrderId();

            Order order = new Order(
                    orderId, username, item.getToyId(), item.getToyName(),
                    item.getQuantity(), item.getTotal(),
                    LocalDate.now().toString(), deliveryAddress, "Pending"
            );
            orderService.addOrder(order);

            Payment payment = new Payment(
                    paymentService.generatePaymentId(), orderId, username,
                    paymentMethod, item.getTotal(), "Paid",
                    LocalDate.now().toString()
            );
            paymentService.addPayment(payment);
        }

        cartService.clearCustomerCart(username);
        return "redirect:/customer/myOrders.jsp?success=orderPlaced";
    }
}
