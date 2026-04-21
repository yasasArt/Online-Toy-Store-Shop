<%@ page import="com.toystore.model.Toy" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Toy</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">

    <style>
        body {
            background: linear-gradient(135deg, #ffe4ec, #e0f7ff, #fff7d6);
            font-family: 'Segoe UI', sans-serif;
            min-height: 100vh;
        }

        .navbar {
            background: linear-gradient(90deg, #ff6b6b, #ff9f43);
        }

        .navbar-brand {
            font-weight: bold;
            color: white !important;
            font-size: 1.4rem;
        }

        .main-card {
            background: #fff;
            border-radius: 22px;
            padding: 30px;
            margin-top: 40px;
            box-shadow: 0 12px 35px rgba(0,0,0,0.10);
        }

        .page-title {
            font-weight: bold;
            color: #ff6b6b;
        }

        .subtitle {
            color: #666;
            margin-bottom: 25px;
        }

        .form-control {
            border-radius: 12px;
            height: 48px;
        }

        .input-group-text {
            border-radius: 12px 0 0 12px;
        }

        .btn-custom {
            border-radius: 12px;
            font-weight: 600;
            padding: 10px 18px;
        }

        .result-card {
            border: none;
            border-radius: 18px;
            box-shadow: 0 8px 25px rgba(0,0,0,0.08);
            margin-top: 25px;
            overflow: hidden;
        }

        .result-header {
            background: linear-gradient(90deg, #00b894, #55efc4);
            color: white;
            padding: 16px 20px;
            font-weight: bold;
            font-size: 1.1rem;
        }

        .toy-info p {
            margin-bottom: 10px;
            font-size: 15px;
        }

        .toy-badge {
            display: inline-block;
            padding: 6px 12px;
            border-radius: 30px;
            font-size: 13px;
            font-weight: 600;
            margin-right: 8px;
            margin-bottom: 8px;
            background: #f1f3f5;
        }

        .empty-alert {
            margin-top: 25px;
            border-radius: 14px;
        }

        .toy-icon-box {
            width: 90px;
            height: 90px;
            background: linear-gradient(135deg, #fff0f6, #fff9db);
            border-radius: 20px;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 42px;
            margin: 0 auto 15px auto;
            color: #ff6b6b;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg">
    <div class="container">
        <a class="navbar-brand"><i class="bi bi-controller"></i> Toy Store</a>
        <div class="ms-auto">
            <a href="dashboard" class="btn btn-light me-2">Dashboard</a>
            <a href="logout" class="btn btn-dark">Logout</a>
        </div>
    </div>
</nav>

<div class="container">
    <div class="main-card">
        <h2 class="page-title"><i class="bi bi-search"></i> Search Toy by ID</h2>
        <p class="subtitle">Enter the Toy ID below to quickly find toy details.</p>

        <form action="searchToy" method="post" class="mb-3">
            <div class="row g-3 align-items-end">
                <div class="col-md-8">
                    <label class="form-label fw-semibold">Toy ID</label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="bi bi-upc-scan"></i></span>
                        <input type="text" name="toyId" class="form-control" placeholder="Enter Toy ID" required>
                    </div>
                </div>
                <div class="col-md-2 d-grid">
                    <button type="submit" class="btn btn-warning btn-custom">
                        <i class="bi bi-search"></i> Search
                    </button>
                </div>
                <div class="col-md-2 d-grid">
                    <a href="dashboard" class="btn btn-secondary btn-custom">
                        <i class="bi bi-arrow-left"></i> Back
                    </a>
                </div>
            </div>
        </form>

        <%
            Object searched = request.getAttribute("searched");
            Toy toy = (Toy) request.getAttribute("toy");

            if (searched != null) {
                if (toy != null) {
        %>

        <div class="card result-card">
            <div class="result-header">
                <i class="bi bi-check-circle-fill"></i> Toy Found Successfully
            </div>
            <div class="card-body p-4">
                <div class="row align-items-center">
                    <div class="col-md-3 text-center">
                        <div class="toy-icon-box">
                            <i class="bi bi-box-seam-fill"></i>
                        </div>
                        <span class="toy-badge"><i class="bi bi-tag-fill"></i> <%= toy.getCategory() %></span>
                        <span class="toy-badge"><i class="bi bi-emoji-smile"></i> <%= toy.getAgeGroup() %></span>
                    </div>

                    <div class="col-md-9 toy-info">
                        <h4 class="fw-bold text-dark mb-3"><%= toy.getToyName() %></h4>
                        <p><strong>ID:</strong> <%= toy.getToyId() %></p>
                        <p><strong>Category:</strong> <%= toy.getCategory() %></p>
                        <p><strong>Age Group:</strong> <%= toy.getAgeGroup() %></p>
                        <p><strong>Price:</strong> Rs. <%= toy.getPrice() %></p>
                        <p><strong>Quantity:</strong> <%= toy.getQuantity() %></p>
                    </div>
                </div>
            </div>
        </div>

        <%
                } else {
        %>
        <div class="alert alert-danger empty-alert text-center">
            <i class="bi bi-exclamation-triangle-fill"></i> Toy not found. Please check the Toy ID and try again.
        </div>
        <%
                }
            }
        %>
    </div>
</div>

</body>
</html>