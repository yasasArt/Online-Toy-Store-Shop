<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Toy</title>
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

        .page-card {
            background: #fff;
            border-radius: 22px;
            padding: 32px;
            margin-top: 40px;
            box-shadow: 0 12px 35px rgba(0,0,0,0.10);
        }

        .page-title {
            font-weight: bold;
            color: #0dcaf0;
            margin-bottom: 8px;
        }

        .subtitle {
            color: #666;
            margin-bottom: 25px;
        }

        .form-label {
            font-weight: 600;
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

        .toy-icon-box {
            width: 95px;
            height: 95px;
            background: linear-gradient(135deg, #e3f9ff, #eefcff);
            border-radius: 22px;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 42px;
            margin: 0 auto 15px auto;
            color: #0dcaf0;
        }

        .side-note {
            background: #f8f9fa;
            border-left: 5px solid #0dcaf0;
            border-radius: 14px;
            padding: 18px;
            height: 100%;
        }

        .side-note h5 {
            font-weight: bold;
            margin-bottom: 12px;
        }

        .side-note p {
            color: #666;
            margin-bottom: 10px;
            font-size: 14px;
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
    <div class="page-card">
        <div class="row g-4 align-items-start">
            <div class="col-lg-8">
                <h2 class="page-title"><i class="bi bi-pencil-square"></i> Update Toy</h2>
                <p class="subtitle">Edit the toy details below and save the updated information.</p>

                <%
                    String message = (String) request.getAttribute("message");
                    if (message != null) {
                %>
                <div class="alert alert-danger"><%= message %></div>
                <% } %>

                <form action="updateToy" method="post">
                    <div class="row g-3">
                        <div class="col-md-6">
                            <label class="form-label">Toy ID</label>
                            <div class="input-group">
                                <span class="input-group-text"><i class="bi bi-upc-scan"></i></span>
                                <input type="text" name="toyId" class="form-control" placeholder="Enter Toy ID" required>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <label class="form-label">Toy Name</label>
                            <div class="input-group">
                                <span class="input-group-text"><i class="bi bi-box-seam"></i></span>
                                <input type="text" name="toyName" class="form-control" placeholder="Enter Toy Name" required>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <label class="form-label">Category</label>
                            <div class="input-group">
                                <span class="input-group-text"><i class="bi bi-tags"></i></span>
                                <input type="text" name="category" class="form-control" placeholder="Enter Category" required>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <label class="form-label">Age Group</label>
                            <div class="input-group">
                                <span class="input-group-text"><i class="bi bi-emoji-smile"></i></span>
                                <input type="text" name="ageGroup" class="form-control" placeholder="Enter Age Group" required>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <label class="form-label">Price</label>
                            <div class="input-group">
                                <span class="input-group-text"><i class="bi bi-currency-dollar"></i></span>
                                <input type="number" step="0.01" name="price" class="form-control" placeholder="Enter Price" required>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <label class="form-label">Quantity</label>
                            <div class="input-group">
                                <span class="input-group-text"><i class="bi bi-boxes"></i></span>
                                <input type="number" name="quantity" class="form-control" placeholder="Enter Quantity" required>
                            </div>
                        </div>
                    </div>

                    <div class="d-flex gap-2 mt-4">
                        <button type="submit" class="btn btn-info text-white btn-custom w-100">
                            <i class="bi bi-check-circle-fill"></i> Update Toy
                        </button>
                        <a href="dashboard" class="btn btn-secondary btn-custom w-100">
                            <i class="bi bi-arrow-left"></i> Back
                        </a>
                    </div>
                </form>
            </div>

            <div class="col-lg-4">
                <div class="toy-icon-box">
                    <i class="bi bi-joystick"></i>
                </div>

                <div class="side-note">
                    <h5><i class="bi bi-lightbulb-fill text-warning"></i> Update Tips</h5>
                    <p>Enter the correct Toy ID to update the existing toy record.</p>
                    <p>Make sure the toy name, category, age group, price, and quantity are correct before submitting.</p>
                    <p>Use this page to keep your store inventory accurate and up to date.</p>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>