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
        <h1>My Profile</h1>
        <p class="muted">Update or delete your customer account.</p>

        <% if ("updated".equals(request.getParameter("msg"))) { %>
            <div class="alert success">Profile updated successfully.</div>
        <% } %>

        <% if ("failed".equals(request.getParameter("error"))) { %>
            <div class="alert error">Something went wrong. Please try again.</div>
        <% } %>

        <div class="form-panel">
            <form action="../updateProfile" method="post">

                <label>User ID</label>
                <input type="text" name="userId" value="<%= loggedUser.getUserId() %>" readonly>

                <label>Full Name</label>
                <input type="text" name="fullName" value="<%= loggedUser.getFullName() %>" required>

                <label>Email</label>
                <input type="email" name="email" value="<%= loggedUser.getEmail() %>" required>

                <label>Username</label>
                <input type="text" name="username" value="<%= loggedUser.getUsername() %>" readonly>

                <label>Password</label>
                <input type="password" name="password" value="<%= loggedUser.getPassword() %>" required>

                <label>Phone</label>
                <input type="text" name="phone" value="<%= loggedUser.getPhone() %>" required>

                <label>Address</label>
                <textarea name="address" required><%= loggedUser.getAddress() %></textarea>

                <button type="submit">Update Profile</button>
            </form>
        </div>

        <div class="panel">
            <h2>Delete Account</h2>
            <p>This will permanently delete your customer account.</p>

            <form action="../deleteProfile" method="post"
                  onsubmit="return confirm('Are you sure you want to delete your account?');">
                <button type="submit" class="delete-account-btn">Delete My Account</button>
            </form>
        </div>
    </main>

</div>

</body>
</html>