<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Toy</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2>Add New Toy</h2>

    <%
        String message = (String) request.getAttribute("message");
        if (message != null) {
    %>
    <div class="alert alert-danger"><%= message %></div>
    <% } %>

    <form action="addToy" method="post">
        <div class="mb-3"><label>Toy ID</label><input type="text" name="toyId" class="form-control" required></div>
        <div class="mb-3"><label>Toy Name</label><input type="text" name="toyName" class="form-control" required></div>
        <div class="mb-3"><label>Category</label><input type="text" name="category" class="form-control" required></div>
        <div class="mb-3"><label>Age Group</label><input type="text" name="ageGroup" class="form-control" required></div>
        <div class="mb-3"><label>Price</label><input type="number" step="0.01" name="price" class="form-control" required></div>
        <div class="mb-3"><label>Quantity</label><input type="number" name="quantity" class="form-control" required></div>
        <button type="submit" class="btn btn-primary">Add Toy</button>
        <a href="dashboard" class="btn btn-secondary">Back</a>
    </form>
</div>
</body>
</html>