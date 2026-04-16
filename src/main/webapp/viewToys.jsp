<%@ page import="java.util.List" %>
<%@ page import="com.toystore.model.Toy" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Toys</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2>All Toys</h2>

    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th>Toy ID</th>
            <th>Name</th>
            <th>Category</th>
            <th>Age Group</th>
            <th>Price</th>
            <th>Quantity</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Toy> toyList = (List<Toy>) request.getAttribute("toyList");
            if (toyList != null && !toyList.isEmpty()) {
                for (Toy toy : toyList) {
        %>
        <tr>
            <td><%= toy.getToyId() %></td>
            <td><%= toy.getToyName() %></td>
            <td><%= toy.getCategory() %></td>
            <td><%= toy.getAgeGroup() %></td>
            <td><%= toy.getPrice() %></td>
            <td><%= toy.getQuantity() %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="6" class="text-center">No toys found.</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>

    <a href="dashboard" class="btn btn-secondary">Back</a>
</div>
</body>
</html>