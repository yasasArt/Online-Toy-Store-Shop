<%@ page import="com.toystore.model.User" %>
<%@ page import="com.toystore.service.UserService" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User loggedUser = (User) session.getAttribute("loggedUser");
    if (loggedUser == null || !"admin".equalsIgnoreCase(loggedUser.getRole())) {
        response.sendRedirect("../login.jsp");
        return;
    }

    UserService userService = new UserService();
    List<User> customers = userService.getAllCustomers();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Customers</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>

<div class="dashboard-layout">
    <aside class="sidebar">
        <h2>ToyLand Admin</h2>
        <a href="adminDashboard.jsp">Dashboard</a>
        <a href="viewCustomers.jsp">Customers</a>
        <a href="../logout" class="logout">Logout</a>
    </aside>

    <main class="content">
        <h1>Customer List</h1>

        <div class="table-panel">
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Full Name</th>
                    <th>Email</th>
                    <th>Username</th>
                    <th>Phone</th>
                    <th>Address</th>
                </tr>
                </thead>

                <tbody>
                <% for (User customer : customers) { %>
                    <tr>
                        <td><%= customer.getUserId() %></td>
                        <td><%= customer.getFullName() %></td>
                        <td><%= customer.getEmail() %></td>
                        <td><%= customer.getUsername() %></td>
                        <td><%= customer.getPhone() %></td>
                        <td><%= customer.getAddress() %></td>
                    </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </main>
</div>

</body>
</html>