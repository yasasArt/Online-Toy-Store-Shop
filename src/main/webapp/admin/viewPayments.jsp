<%@ page import="com.toystore.model.*" %>
<%@ page import="com.toystore.service.PaymentService" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User loggedUser = (User) session.getAttribute("loggedUser");
    if (loggedUser == null || !"admin".equalsIgnoreCase(loggedUser.getRole())) {
        response.sendRedirect("../login.jsp");
        return;
    }

    PaymentService paymentService = new PaymentService();
    List<Payment> payments = paymentService.getAllPayments();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Payments</title>
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
        <h1>Payments</h1>

        <div class="table-panel">
            <table>
                <thead>
                <tr>
                    <th>Payment ID</th>
                    <th>Order ID</th>
                    <th>Customer</th>
                    <th>Method</th>
                    <th>Amount</th>
                    <th>Status</th>
                    <th>Date</th>
                </tr>
                </thead>

                <tbody>
                <% for (Payment payment : payments) { %>
                    <tr>
                        <td><%= payment.getPaymentId() %></td>
                        <td><%= payment.getOrderId() %></td>
                        <td><%= payment.getCustomerUsername() %></td>
                        <td><%= payment.getPaymentMethod() %></td>
                        <td>Rs. <%= payment.getAmount() %></td>
                        <td><span class="badge green"><%= payment.getPaymentStatus() %></span></td>
                        <td><%= payment.getPaymentDate() %></td>
                    </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </main>
</div>

</body>
</html>