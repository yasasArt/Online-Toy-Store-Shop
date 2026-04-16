<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Delete Toy</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2>Delete Toy</h2>

    <%
        String message = (String) request.getAttribute("message");
        if (message != null) {
    %>
    <div class="alert alert-danger"><%= message %></div>
    <% } %>

    <form action="deleteToy" method="post">
        <div class="mb-3">
            <label>Toy ID</label>
            <input type="text" name="toyId" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-danger">Delete Toy</button>
        <a href="dashboard" class="btn btn-secondary">Back</a>
    </form>
</div>
</body>
</html>