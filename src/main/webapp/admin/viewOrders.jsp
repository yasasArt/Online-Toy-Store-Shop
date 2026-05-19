<%@ page import="com.toystore.model.*" %>
<%@ page import="com.toystore.service.OrderService" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User loggedUser = (User) session.getAttribute("loggedUser");
    if (loggedUser == null || !"admin".equalsIgnoreCase(loggedUser.getRole())) {
        response.sendRedirect("../login.jsp");
        return;
    }

    OrderService orderService = new OrderService();
    List<Order> orders = orderService.getAllOrders();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Orders</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>

<div class="dashboard-layout">
    <aside class="sidebar">
        <h2>ToyLand Admin</h2>

        <a href="${pageContext.request.contextPath}/admin/adminDashboard.jsp">Dashboard</a>
        <a href="${pageContext.request.contextPath}/admin/addToy.jsp">Add Toy</a>
        <a href="${pageContext.request.contextPath}/viewToys">Manage Toys</a>
        <a href="${pageContext.request.contextPath}/admin/addCategory.jsp">Add Category</a>
        <a href="${pageContext.request.contextPath}/viewCategories">Manage Categories</a>
        <a href="${pageContext.request.contextPath}/admin/viewCustomers.jsp">Customers</a>
        <a href="${pageContext.request.contextPath}/admin/viewOrders.jsp">Orders</a>
        <a href="${pageContext.request.contextPath}/admin/viewPayments.jsp">Payments</a>
        <a href="${pageContext.request.contextPath}/admin/viewReviews.jsp">Reviews</a>

        <a href="${pageContext.request.contextPath}/logout" class="logout">Logout</a>
    </aside>

    <main class="content">
        <h1>All Orders</h1>

        <div class="table-panel">
            <table>
                <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Customer</th>
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
                        <td><%= order.getCustomerUsername() %></td>
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
    </main>
</div>

</body>
</html>