<%@ page import="com.toystore.model.User" %>
<%@ page import="com.toystore.service.CartService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User loggedUser = (User) session.getAttribute("loggedUser");
    if (loggedUser == null || !"customer".equalsIgnoreCase(loggedUser.getRole())) {
        response.sendRedirect("../login.jsp");
        return;
    }

    CartService cartService = new CartService();
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
        <a href="../logout" class="logout">Logout</a>
    </aside>

    <main class="content">
        <div class="form-panel">
            <h1>Checkout</h1>
            <h2>Total Amount: Rs. <%= cartService.getCartTotal(loggedUser.getUsername()) %></h2>

            <form action="../placeOrder" method="post">
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