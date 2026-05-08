<%@ page import="com.toystore.model.*" %>
<%@ page import="com.toystore.service.CartService" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User loggedUser = (User) session.getAttribute("loggedUser");
    if (loggedUser == null || !"customer".equalsIgnoreCase(loggedUser.getRole())) {
        response.sendRedirect("../login.jsp");
        return;
    }

    CartService cartService = new CartService();
    List<CartItem> cartItems = cartService.getCartByCustomer(loggedUser.getUsername());
%>

<!DOCTYPE html>
<html>
<head>
    <title>My Cart</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>

<div class="dashboard-layout">
    <aside class="sidebar customer-side">
        <h2>ToyLand</h2>
        <a href="customerDashboard.jsp">Dashboard</a>
        <a href="../viewToys">Toy Catalog</a>
        <a href="cart.jsp">My Cart</a>
        <a href="myOrders.jsp">My Orders</a>
        <a href="../logout" class="logout">Logout</a>
    </aside>

    <main class="content">
        <h1>My Cart</h1>

        <div class="table-panel">
            <table>
                <thead>
                <tr>
                    <th>Toy</th>
                    <th>Price</th>
                    <th>Qty</th>
                    <th>Total</th>
                </tr>
                </thead>

                <tbody>
                <% for (CartItem item : cartItems) { %>
                    <tr>
                        <td><%= item.getToyName() %></td>
                        <td>Rs. <%= item.getPrice() %></td>
                        <td><%= item.getQuantity() %></td>
                        <td>Rs. <%= item.getTotal() %></td>
                    </tr>
                <% } %>
                </tbody>
            </table>
        </div>

        <div class="panel right">
            <h2>Total: Rs. <%= cartService.getCartTotal(loggedUser.getUsername()) %></h2>
            <a href="checkout.jsp" class="main-btn">Checkout</a>
        </div>
    </main>
</div>

</body>
</html>