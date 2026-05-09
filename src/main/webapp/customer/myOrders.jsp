<%@ page import="com.toystore.model.*" %>
<%@ page import="com.toystore.service.OrderService" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User loggedUser = (User) session.getAttribute("loggedUser");

    if (loggedUser == null || !"customer".equalsIgnoreCase(loggedUser.getRole())) {
        response.sendRedirect("../login.jsp");
        return;
    }

    OrderService orderService = new OrderService();
    List<Order> orders = orderService.getOrdersByCustomer(loggedUser.getUsername());
%>

<!DOCTYPE html>
<html>
<head>
    <title>My Orders</title>
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
        <h1>My Orders</h1>
        <p class="muted">View your placed orders and order status.</p>

        <% if (orders != null && !orders.isEmpty()) { %>

        <div class="table-panel">
            <table>
                <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Toy</th>
                    <th>Qty</th>
                    <th>Total</th>
                    <th>Date</th>
                    <th>Address</th>
                    <th>Status</th>
                </tr>
                </thead>

                <tbody>
                <% for (Order order : orders) { %>
                <tr>
                    <td><%= order.getOrderId() %></td>
                    <td><%= order.getToyName() %></td>
                    <td><%= order.getQuantity() %></td>
                    <td>Rs. <%= order.getTotalAmount() %></td>
                    <td><%= order.getOrderDate() %></td>
                    <td><%= order.getDeliveryAddress() %></td>
                    <td><span class="badge"><%= order.getStatus() %></span></td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>

        <% } else { %>

        <div class="panel">
            <h2>No orders found 📦</h2>
            <p>You have not placed any orders yet.</p>
            <a href="../viewToys" class="main-btn">Shop Now</a>
        </div>

        <% } %>

    </main>

</div>

</body>
</html>