<%@ page import="com.toystore.service.*" %>
<%@ page import="com.toystore.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User loggedUser = (User) session.getAttribute("loggedUser");
    if (loggedUser == null || !"admin".equalsIgnoreCase(loggedUser.getRole())) {
        response.sendRedirect("../login.jsp");
        return;
    }

    ToyService toyService = new ToyService();
    UserService userService = new UserService();
    OrderService orderService = new OrderService();
    PaymentService paymentService = new PaymentService();
    ReviewService reviewService = new ReviewService();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>

<div class="dashboard-layout">
    <aside class="sidebar">
        <h2>ToyLand Admin</h2>
        <a href="adminDashboard.jsp">Dashboard</a>
        <a href="addToy.jsp">Add Toy</a>
        <a href="editToy.jsp">Manage Toys</a>
        <a href="viewCustomers.jsp">Customers</a>
        <a href="viewOrders.jsp">Orders</a>
        <a href="viewPayments.jsp">Payments</a>
        <a href="viewReviews.jsp">Reviews</a>
        <a href="../logout" class="logout">Logout</a>
    </aside>

    <main class="content">
        <h1>Hello, <%= loggedUser.getFullName() %> 👋</h1>
        <p class="muted">Manage your online toy store from here.</p>

        <div class="stats-grid">
            <div class="stat-card">
                <h3>Total Toys</h3>
                <h2><%= toyService.getAllToys().size() %></h2>
            </div>
            <div class="stat-card">
                <h3>Customers</h3>
                <h2><%= userService.getAllCustomers().size() %></h2>
            </div>
            <div class="stat-card">
                <h3>Orders</h3>
                <h2><%= orderService.getTotalOrders() %></h2>
            </div>
            <div class="stat-card">
                <h3>Total Sales</h3>
                <h2>Rs. <%= paymentService.getTotalPaidAmount() %></h2>
            </div>
        </div>

        <div class="panel">
            <h2>Quick Actions</h2>
            <a href="addToy.jsp" class="main-btn">Add New Toy</a>
            <a href="../viewToys" class="secondary-btn">View Toys</a>
            <a href="viewOrders.jsp" class="secondary-btn">View Orders</a>
        </div>
    </main>
</div>

</body>
</html>