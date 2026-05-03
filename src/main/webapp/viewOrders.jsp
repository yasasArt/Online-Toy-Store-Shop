<%@ page import="java.util.List,com.toystore.model.Order" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Orders</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark navbar-custom">
    <div class="container">
        <a class="navbar-brand" href="dashboard.jsp">
            <i class="bi bi-controller"></i> Online Toy Store
        </a>
        <div>
            <a href="dashboard.jsp" class="btn btn-outline-light btn-sm">Dashboard</a>
        </div>
    </div>
</nav>

<div class="container py-4">
    <div class="page-header">
        <h2>Order List</h2>
        <p class="page-subtitle">View all customer orders</p>
    </div>

    <div class="table-wrapper">
        <div class="table-responsive">
            <table class="table table-hover align-middle">
                <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Customer Name</th>
                    <th>Toy ID</th>
                    <th>Quantity</th>
                    <th>Status</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<Order> orderList = (List<Order>) request.getAttribute("orderList");
                    if (orderList != null && !orderList.isEmpty()) {
                        for (Order o : orderList) {
                %>
                <tr>
                    <td><%= o.getOrderId() %></td>
                    <td><%= o.getCustomerName() %></td>
                    <td><%= o.getToyId() %></td>
                    <td><%= o.getQuantity() %></td>
                    <td><span class="badge-soft"><%= o.getStatus() %></span></td>
                </tr>
                <%      }
                    } else { %>
                <tr>
                    <td colspan="5" class="text-center">No orders found.</td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>

        <a href="dashboard.jsp" class="btn btn-dark mt-3">Back</a>
    </div>
</div>

</body>
</html>