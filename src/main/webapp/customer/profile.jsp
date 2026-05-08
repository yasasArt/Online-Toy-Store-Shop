<%@ page import="com.toystore.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User loggedUser = (User) session.getAttribute("loggedUser");
    if (loggedUser == null || !"customer".equalsIgnoreCase(loggedUser.getRole())) {
        response.sendRedirect("../login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>My Profile</title>
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
        <div class="profile-card">
            <h1>My Profile</h1>
            <p><b>User ID:</b> <%= loggedUser.getUserId() %></p>
            <p><b>Name:</b> <%= loggedUser.getFullName() %></p>
            <p><b>Email:</b> <%= loggedUser.getEmail() %></p>
            <p><b>Username:</b> <%= loggedUser.getUsername() %></p>
            <p><b>Phone:</b> <%= loggedUser.getPhone() %></p>
            <p><b>Address:</b> <%= loggedUser.getAddress() %></p>
        </div>
    </main>
</div>

</body>
</html>