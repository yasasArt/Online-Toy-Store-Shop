<%@ page import="java.util.List" %>
<%@ page import="com.toystore.model.Toy" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View Toys</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Bootstrap + Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">

    <style>
        body {
            background: linear-gradient(135deg, #ffe4ec, #e0f7ff, #fff7d6);
            font-family: 'Segoe UI', sans-serif;
        }

        .navbar {
            background: linear-gradient(90deg, #ff6b6b, #ff9f43);
        }

        .navbar-brand {
            font-weight: bold;
            color: white !important;
        }

        .page-title {
            color: #ff6b6b;
            font-weight: bold;
            margin-bottom: 25px;
        }

        .toy-card {
            border: none;
            border-radius: 20px;
            padding: 20px;
            box-shadow: 0 10px 25px rgba(0,0,0,0.08);
            transition: 0.3s;
            height: 100%;
            background: white;
        }

        .toy-card:hover {
            transform: translateY(-8px);
            box-shadow: 0 15px 35px rgba(0,0,0,0.15);
        }

        .toy-icon {
            font-size: 50px;
            text-align: center;
            margin-bottom: 10px;
            color: #ff6b6b;
        }

        .toy-name {
            font-weight: bold;
            font-size: 18px;
        }

        .badge-custom {
            background: #f1f3f5;
            border-radius: 20px;
            padding: 5px 10px;
            font-size: 12px;
            margin-right: 5px;
        }

        .price {
            color: #00b894;
            font-weight: bold;
            font-size: 18px;
        }

        .qty {
            font-size: 14px;
            color: #666;
        }
    </style>
</head>

<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg">
    <div class="container">
        <a class="navbar-brand"><i class="bi bi-controller"></i> Toy Store</a>
        <div class="ms-auto">
            <a href="dashboard" class="btn btn-light me-2">Dashboard</a>
            <a href="logout" class="btn btn-dark">Logout</a>
        </div>
    </div>
</nav>

<div class="container mt-4">

    <h2 class="page-title text-center">
        <i class="bi bi-bag-fill"></i> All Toys
    </h2>

    <div class="row g-4">

        <%
            List<Toy> toyList = (List<Toy>) request.getAttribute("toyList");
            if (toyList != null && !toyList.isEmpty()) {
                for (Toy toy : toyList) {
        %>

        <div class="col-md-4">
            <div class="toy-card text-center">

                <div class="toy-icon">
                    <i class="bi bi-box-seam"></i>
                </div>

                <div class="toy-name"><%= toy.getToyName() %></div>

                <div class="mt-2">
                    <span class="badge-custom"><%= toy.getCategory() %></span>
                    <span class="badge-custom"><%= toy.getAgeGroup() %></span>
                </div>

                <div class="price mt-2">
                    Rs. <%= toy.getPrice() %>
                </div>

                <div class="qty">
                    Stock: <%= toy.getQuantity() %>
                </div>

                <hr>

                <small class="text-muted">ID: <%= toy.getToyId() %></small>

            </div>
        </div>

        <%
                }
            } else {
        %>

        <div class="col-12 text-center">
            <div class="alert alert-warning">
                <i class="bi bi-exclamation-circle"></i> No toys found.
            </div>
        </div>

        <%
            }
        %>

    </div>

    <div class="text-center mt-4">
        <a href="dashboard" class="btn btn-secondary">
            <i class="bi bi-arrow-left"></i> Back
        </a>
    </div>

</div>

</body>
</html>