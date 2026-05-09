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
    double grandTotal = cartService.getCartTotal(loggedUser.getUsername());
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
        <a href="profile.jsp">Profile</a>
        <a href="../logout" class="logout">Logout</a>
    </aside>

    <main class="content">
        <h1>My Cart</h1>
        <p class="muted">Review your selected toys before checkout.</p>

        <% if ("deleted".equals(request.getParameter("msg"))) { %>
            <div class="alert success">Cart item removed successfully.</div>
        <% } %>

        <% if (cartItems != null && !cartItems.isEmpty()) { %>

        <div class="table-panel">
            <table>
                <thead>
                <tr>
                    <th>Toy</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total</th>
                    <th>Action</th>
                </tr>
                </thead>

                <tbody>
                <% for (CartItem item : cartItems) { %>
                <tr>
                    <td><%= item.getToyName() %></td>
                    <td>Rs. <%= item.getPrice() %></td>
                    <td><%= item.getQuantity() %></td>
                    <td>Rs. <%= item.getTotal() %></td>
                    <td>
                        <a class="delete-btn"
                           href="${pageContext.request.contextPath}/deleteCartItem?cartId=<%= item.getCartId() %>"
                           onclick="return confirm('Remove this item from cart?');">
                           Delete
                        </a>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>

        <div class="panel right">
            <h2>Total: Rs. <%= grandTotal %></h2>
            <a href="checkout.jsp" class="main-btn">Proceed to Checkout</a>
            <a href="../viewToys" class="secondary-btn">Continue Shopping</a>
        </div>

        <% } else { %>

        <div class="panel">
            <h2>Your cart is empty 🛒</h2>
            <p>No toys added yet. Browse the toy catalog and add items.</p>
            <a href="../viewToys" class="main-btn">Start Shopping</a>
        </div>

        <% } %>

    </main>

</div>

</body>
</html>