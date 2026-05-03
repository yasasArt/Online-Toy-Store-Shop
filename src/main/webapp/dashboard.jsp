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
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Online Toy Store Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <style>
        body {
            background: linear-gradient(135deg, #fff6e9, #ffe0f0, #e6f7ff);
            min-height: 100vh;
            font-family: Arial, sans-serif;
        }

        .navbar-custom {
            background: linear-gradient(90deg, #ff6b6b, #ff9f43);
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
        }

        .navbar-brand {
            font-weight: bold;
            font-size: 1.5rem;
            color: white !important;
        }

        .navbar-nav .nav-link {
            color: white !important;
            font-weight: 600;
            margin-right: 10px;
            padding: 8px 14px !important;
            border-radius: 10px;
            transition: 0.3s ease;
        }

        .navbar-nav .nav-link:hover,
        .navbar-nav .nav-link.active {
            background: rgba(255,255,255,0.18);
        }

        .dropdown-menu {
            border: none;
            border-radius: 14px;
            box-shadow: 0 8px 25px rgba(0,0,0,0.12);
            padding: 10px;
        }

        .dropdown-item {
            border-radius: 10px;
            padding: 10px 14px;
            font-weight: 500;
        }

        .dropdown-item:hover {
            background: #fff1f1;
        }

        .dashboard-header {
            background: white;
            border-radius: 20px;
            padding: 30px;
            margin-top: 30px;
            box-shadow: 0 8px 25px rgba(0,0,0,0.08);
        }

        .welcome-title {
            font-weight: bold;
            color: #ff6b6b;
        }

        .user-badge {
            display: inline-block;
            background: linear-gradient(90deg, #6c5ce7, #00b894);
            color: white;
            padding: 8px 18px;
            border-radius: 30px;
            font-size: 14px;
            margin-top: 10px;
        }

        .info-card {
            border: none;
            border-radius: 18px;
            box-shadow: 0 6px 20px rgba(0,0,0,0.08);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            height: 100%;
        }

        .info-card:hover {
            transform: translateY(-8px);
            box-shadow: 0 12px 30px rgba(0,0,0,0.15);
        }

        .card-icon {
            font-size: 2.4rem;
            margin-bottom: 15px;
        }

        .btn-custom {
            border-radius: 12px;
            font-weight: bold;
            padding: 12px;
        }

        .section-title {
            font-weight: bold;
            color: #444;
            margin-top: 35px;
            margin-bottom: 20px;
        }

        .profile-box {
            background: #ffffff;
            border-radius: 18px;
            padding: 20px;
            box-shadow: 0 6px 20px rgba(0,0,0,0.08);
            margin-top: 25px;
        }

        .profile-box p {
            margin-bottom: 10px;
            font-size: 15px;
        }

        .footer-note {
            text-align: center;
            color: #666;
            margin-top: 30px;
            padding-bottom: 20px;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-custom">
    <div class="container">
        <a class="navbar-brand" href="dashboard.jsp">
            <i class="bi bi-controller"></i> Online Toy Store
        </a>

        <button class="navbar-toggler bg-light" type="button" data-bs-toggle="collapse" data-bs-target="#mainNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="mainNavbar">
            <ul class="navbar-nav mx-auto">
                <li class="nav-item">
                    <a class="nav-link active" href="dashboard.jsp">
                        <i class="bi bi-house-door-fill"></i> Dashboard
                    </a>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">
                        <i class="bi bi-bag-fill"></i> Toys
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="viewToys"><i class="bi bi-grid"></i> View Toys</a></li>
                        <li><a class="dropdown-item" href="addToy.jsp"><i class="bi bi-plus-circle"></i> Add Toy</a></li>
                        <li><a class="dropdown-item" href="searchToy.jsp"><i class="bi bi-search"></i> Search Toy</a></li>
                        <li><a class="dropdown-item" href="updateToy.jsp"><i class="bi bi-pencil-square"></i> Update Toy</a></li>
                        <li><a class="dropdown-item" href="deleteToy.jsp"><i class="bi bi-trash"></i> Delete Toy</a></li>
                    </ul>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">
                        <i class="bi bi-cart-fill"></i> Orders
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="addOrder.jsp"><i class="bi bi-plus-circle"></i> Add Order</a></li>
                        <li><a class="dropdown-item" href="viewOrders"><i class="bi bi-list-ul"></i> View Orders</a></li>
                        <li><a class="dropdown-item" href="updateOrder.jsp"><i class="bi bi-pencil-square"></i> Update Order</a></li>
                        <li><a class="dropdown-item" href="deleteOrder.jsp"><i class="bi bi-trash"></i> Delete Order</a></li>
                    </ul>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">
                        <i class="bi bi-tags-fill"></i> Categories
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="addCategory.jsp"><i class="bi bi-plus-circle"></i> Add Category</a></li>
                        <li><a class="dropdown-item" href="viewCategories"><i class="bi bi-list-ul"></i> View Categories</a></li>
                        <li><a class="dropdown-item" href="updateCategory.jsp"><i class="bi bi-pencil-square"></i> Update Category</a></li>
                        <li><a class="dropdown-item" href="deleteCategory.jsp"><i class="bi bi-trash"></i> Delete Category</a></li>
                    </ul>
                </li>
            </ul>

            <div class="ms-auto">
                <a href="logout" class="btn btn-light fw-bold rounded-pill px-4">
                    <i class="bi bi-box-arrow-right"></i> Logout
                </a>
            </div>
        </div>
    </div>
</nav>

<div class="container">
    <div class="dashboard-header">
        <div class="row align-items-center">
            <div class="col-md-8">
                <h2 class="welcome-title">Welcome, <%= loggedUser.getFullName() %> 🎉</h2>
                <p class="text-muted mb-1">Manage your toy store easily from one place.</p>
                <span class="user-badge">
                    <i class="bi bi-person-circle"></i> <%= loggedUser.getRole() %>
                </span>
            </div>
            <div class="col-md-4 text-md-end text-center mt-3 mt-md-0">
                <img src="https://cdn-icons-png.flaticon.com/512/3081/3081559.png" alt="Toy Store" width="110">
            </div>
        </div>
    </div>

    <div class="profile-box">
        <h5 class="mb-3"><i class="bi bi-person-vcard"></i> User Information</h5>
        <p><strong>Full Name:</strong> <%= loggedUser.getFullName() %></p>
        <p><strong>Username:</strong> <%= loggedUser.getUsername() %></p>
        <p><strong>Role:</strong> <%= loggedUser.getRole() %></p>
    </div>

    <h4 class="section-title"><i class="bi bi-grid-fill"></i> Quick Actions</h4>

    <div class="row g-4">
        <div class="col-md-4">
            <div class="card info-card text-center p-4">
                <div class="card-body">
                    <div class="card-icon text-primary"><i class="bi bi-bag-fill"></i></div>
                    <h5 class="card-title">View Toys</h5>
                    <p class="card-text text-muted">See all toys available in the store.</p>
                    <a href="viewToys" class="btn btn-primary btn-custom w-100">Open</a>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card info-card text-center p-4">
                <div class="card-body">
                    <div class="card-icon text-success"><i class="bi bi-plus-circle-fill"></i></div>
                    <h5 class="card-title">Add Toy</h5>
                    <p class="card-text text-muted">Add new toy items into the system.</p>
                    <a href="addToy.jsp" class="btn btn-success btn-custom w-100">Open</a>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card info-card text-center p-4">
                <div class="card-body">
                    <div class="card-icon text-warning"><i class="bi bi-search"></i></div>
                    <h5 class="card-title">Search Toy</h5>
                    <p class="card-text text-muted">Find toy details quickly by keyword.</p>
                    <a href="searchToy.jsp" class="btn btn-warning btn-custom w-100">Open</a>
                </div>
            </div>
        </div>
    </div>

    <div class="footer-note">
        <p>Online Toy Store Management System | Object Oriented Programming Project</p>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>