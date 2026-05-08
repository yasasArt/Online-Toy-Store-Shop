<%@ page import="com.toystore.model.User" %>
<%@ page import="com.toystore.service.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User loggedUser = (User) session.getAttribute("loggedUser");
    if (loggedUser == null || !"customer".equalsIgnoreCase(loggedUser.getRole())) {
        response.sendRedirect("../login.jsp");
        return;
    }

    CartService cartService = new CartService();
    OrderService orderService = new OrderService();
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
        <p class="muted">Find beautiful toys and place your order easily.</p>

        <div class="stats-grid">
            <div class="stat-card">
                <h3>Cart Total</h3>
                <h2>Rs. <%= cartService.getCartTotal(loggedUser.getUsername()) %></h2>
            </div>
            <div class="stat-card">
                <h3>My Orders</h3>
                <h2><%= orderService.getOrdersByCustomer(loggedUser.getUsername()).size() %></h2>
            </div>
        </div>

        <div class="panel">
            <h2>Start Shopping</h2>
            <p>Browse our toy catalog and add your favorite toys to cart.</p>
            <a href="../viewToys" class="main-btn">View Toys</a>
        </div>
    </main>
</div>

</body>
</html>