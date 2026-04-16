<%@ page import="com.toystore.model.Toy" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Search Toy</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2>Search Toy by ID</h2>

    <form action="searchToy" method="post" class="mb-4">
        <div class="mb-3">
            <label>Enter Toy ID</label>
            <input type="text" name="toyId" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-warning">Search</button>
        <a href="dashboard" class="btn btn-secondary">Back</a>
    </form>

    <%
        Object searched = request.getAttribute("searched");
        Toy toy = (Toy) request.getAttribute("toy");

        if (searched != null) {
            if (toy != null) {
    %>
    <div class="card">
        <div class="card-body">
            <h5>Toy Found</h5>
            <p><strong>ID:</strong> <%= toy.getToyId() %></p>
            <p><strong>Name:</strong> <%= toy.getToyName() %></p>
            <p><strong>Category:</strong> <%= toy.getCategory() %></p>
            <p><strong>Age Group:</strong> <%= toy.getAgeGroup() %></p>
            <p><strong>Price:</strong> <%= toy.getPrice() %></p>
            <p><strong>Quantity:</strong> <%= toy.getQuantity() %></p>
        </div>
    </div>
    <%
            } else {
    %>
    <div class="alert alert-danger">Toy not found.</div>
    <%
            }
        }
    %>
</div>
</body>
</html>