<%@ page import="java.util.ArrayList" %>
<%@ page import="com.fashionhub.dao.OrderDAO" %>
<%@ page import="com.fashionhub.model.Order" %>
<%@ page import="com.fashionhub.model.User" %>

<%
    User user =
        (User) session.getAttribute(
                "loggedInUser");

    if(user == null){

        response.sendRedirect("login.jsp");

        return;
    }

    OrderDAO orderDAO =
            new OrderDAO();

    ArrayList<Order> orderList =
            orderDAO.getOrdersByUser(
                    user.getUserId());
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>My Orders</title>

<link rel="stylesheet"
      href="css/style.css">

<style>

.orders-container{

    width: 90%;

    margin: 40px auto;
}

.order-card{

    background-color: white;

    padding: 25px;

    border-radius: 20px;

    margin-bottom: 20px;
}

.status{

    color: green;

    font-weight: bold;
}

</style>

</head>

<body>

<div class="navbar">

    <div class="logo">
        FashionHub
    </div>

    <div class="nav-links">

        <a href="products.jsp">
            Products
        </a>

        <a href="cart.jsp">
            Cart
        </a>

        <a href="orders.jsp">
            My Orders
        </a>

        <a href="logout">
            Logout
        </a>

    </div>

</div>

<div class="orders-container">

<h1>My Orders</h1>

<%
    if(orderList.size() == 0){
%>

<div class="order-card">

    <h2>
        No Orders Found
    </h2>

</div>

<%
    } else {

    for(Order order : orderList){
%>

<div class="order-card">

    <h2>
        Order ID:
        <%= order.getOrderId() %>
    </h2>

    <p>
        Total Amount:
        ₹ <%= order.getTotalAmount() %>
    </p>

    <p class="status">
        Status:
        <%= order.getOrderStatus() %>
    </p>

</div>

<%
    }
    }
%>

</div>

</body>
</html>