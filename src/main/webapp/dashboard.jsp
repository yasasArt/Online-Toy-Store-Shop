<%@ page import="com.toystore.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User loggedUser = (User) session.getAttribute("loggedUser");
    if (loggedUser == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2>Welcome, <%= loggedUser.getFullName() %></h2>
    <p>Username: <%= loggedUser.getUsername() %></p>
    <p>Role: <%= loggedUser.getRole() %></p>

    <div class="row g-3 mt-3">
        <div class="col-md-4">
            <a href="viewToys" class="btn btn-primary w-100">View Toys</a>
        </div>
        <div class="col-md-4">
            <a href="addToy.jsp" class="btn btn-success w-100">Add Toy</a>
        </div>
        <div class="col-md-4">
            <a href="searchToy.jsp" class="btn btn-warning w-100">Search Toy</a>
        </div>
        <div class="col-md-6">
            <a href="updateToy.jsp" class="btn btn-info w-100">Update Toy</a>
        </div>
        <div class="col-md-6">
            <a href="deleteToy.jsp" class="btn btn-danger w-100">Delete Toy</a>
        </div>
    </div>

    <a href="logout" class="btn btn-dark mt-4">Logout</a>
</div>
</body>
</html>