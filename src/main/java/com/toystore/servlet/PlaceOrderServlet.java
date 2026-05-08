package com.toystore.servlet;

import com.toystore.model.CartItem;
import com.toystore.model.Order;
import com.toystore.model.Payment;
import com.toystore.service.CartService;
import com.toystore.service.OrderService;
import com.toystore.service.PaymentService;
import com.toystore.service.ToyService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/placeOrder")
public class PlaceOrderServlet extends HttpServlet {
    private final CartService cartService = new CartService();
    private final OrderService orderService = new OrderService();
    private final PaymentService paymentService = new PaymentService();
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
        String deliveryAddress = request.getParameter("deliveryAddress");
        String paymentMethod = request.getParameter("paymentMethod");

        List<CartItem> cartItems = cartService.getCartByCustomer(username);

        if (cartItems.isEmpty()) {
            response.sendRedirect("customer/cart.jsp?error=emptyCart");
            return;
        }

        boolean allOrdersPlaced = true;

        for (CartItem item : cartItems) {
            boolean stockReduced = toyService.reduceStock(item.getToyId(), item.getQuantity());

            if (!stockReduced) {
                allOrdersPlaced = false;
                continue;
            }

            String orderId = orderService.generateOrderId();

            Order order = new Order(
                    orderId,
                    username,
                    item.getToyId(),
                    item.getToyName(),
                    item.getQuantity(),
                    item.getTotal(),
                    LocalDate.now().toString(),
                    deliveryAddress,
                    "Pending"
            );

            orderService.addOrder(order);

            Payment payment = new Payment(
                    paymentService.generatePaymentId(),
                    orderId,
                    username,
                    paymentMethod,
                    item.getTotal(),
                    "Paid",
                    LocalDate.now().toString()
            );

            paymentService.addPayment(payment);
        }

        if (allOrdersPlaced) {
            cartService.clearCustomerCart(username);
            response.sendRedirect("customer/myOrders.jsp?msg=orderPlaced");
        } else {
            response.sendRedirect("customer/cart.jsp?error=stockIssue");
        }
    }
}