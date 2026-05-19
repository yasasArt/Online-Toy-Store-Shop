<%@ page import="com.toystore.model.User" %>
<%@ page import="com.toystore.model.CartItem" %>
<%@ page import="com.toystore.service.CartService" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User loggedUser = (User) session.getAttribute("loggedUser");

    if (loggedUser == null || !"customer".equalsIgnoreCase(loggedUser.getRole())) {
        response.sendRedirect("../login.jsp");
        return;
    }

    CartService cartService = new CartService();
    List<CartItem> cartItems = cartService.getCartByCustomer(loggedUser.getUsername());
    double total = cartService.getCartTotal(loggedUser.getUsername());

    if (cartItems == null || cartItems.isEmpty()) {
        response.sendRedirect("cart.jsp?error=emptyCart");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Checkout</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>

<div class="dashboard-layout">

    <aside class="sidebar customer-side">
        <h2>ToyLand</h2>

        <a href="customerDashboard.jsp">Dashboard</a>
        <a href="../viewToys">Toy Catalog</a>
        <a href="cart.jsp">My Cart</a>
        <a href="myOrders.jsp">My Orders</a>
        <a href="addReview.jsp">Add Review</a>
        <a href="profile.jsp">Profile</a>
        <a href="../logout" class="logout">Logout</a>
    </aside>

    <main class="content">
        <h1>Checkout</h1>
        <p class="muted">Confirm your delivery details and payment method.</p>

        <div class="form-panel">
            <h2>Order Summary</h2>
            <p><b>Total Items:</b> <%= cartItems.size() %></p>
            <h2>Total Amount: Rs. <%= total %></h2>

            <form action="${pageContext.request.contextPath}/placeOrder" method="post">
                <label>Delivery Address</label>
                <textarea name="deliveryAddress" required><%= loggedUser.getAddress() %></textarea>

                <label>Payment Method</label>
                <select name="paymentMethod" required>
                    <option value="Cash On Delivery">Cash On Delivery</option>
                    <option value="Card Payment">Card Payment</option>
                    <option value="Bank Transfer">Bank Transfer</option>
                </select>

                <button type="submit">Place Order</button>
            </form>
        </div>
    </main>

</div>

</body>
</html>