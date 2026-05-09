<%@ page import="com.toystore.model.User" %>
<%@ page import="com.toystore.service.CartService" %>
<%@ page import="com.toystore.service.OrderService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User loggedUser = (User) session.getAttribute("loggedUser");

    if (loggedUser == null || !"customer".equalsIgnoreCase(loggedUser.getRole())) {
        response.sendRedirect("../login.jsp");
        return;
    }

    CartService cartService = new CartService();
    OrderService orderService = new OrderService();

    double cartTotal = cartService.getCartTotal(loggedUser.getUsername());
    int orderCount = orderService.getOrdersByCustomer(loggedUser.getUsername()).size();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Customer Dashboard</title>
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
        <a href="profile.jsp">Profile</a>
        <a href="../logout" class="logout">Logout</a>
    </aside>

    <main class="content">
        <h1>Welcome, <%= loggedUser.getFullName() %> 🎈</h1>
        <p class="muted">Browse toys, add them to your cart, and place orders easily.</p>

        <div class="stats-grid">
            <div class="stat-card">
                <h3>Cart Total</h3>
                <h2>Rs. <%= cartTotal %></h2>
            </div>

            <div class="stat-card">
                <h3>My Orders</h3>
                <h2><%= orderCount %></h2>
            </div>

            <div class="stat-card">
                <h3>Account Type</h3>
                <h2>Customer</h2>
            </div>

            <div class="stat-card">
                <h3>Status</h3>
                <h2>Active</h2>
            </div>
        </div>

        <div class="panel">
            <h2>Start Shopping</h2>
            <p>Visit our toy catalog and choose your favourite toys.</p>
            <a href="../viewToys" class="main-btn">View Toys</a>
            <a href="cart.jsp" class="secondary-btn">Go to Cart</a>
        </div>
    </main>

</div>

</body>
</html>