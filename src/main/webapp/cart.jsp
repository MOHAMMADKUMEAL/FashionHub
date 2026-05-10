<%@ page import="java.util.ArrayList" %>
<%@ page import="com.fashionhub.dao.CartDAO" %>
<%@ page import="com.fashionhub.model.CartItem" %>
<%@ page import="com.fashionhub.model.User" %>

<%
    User user =
        (User) session.getAttribute(
                "loggedInUser");

    if(user == null){

        response.sendRedirect("login.jsp");

        return;
    }

    CartDAO cartDAO =
            new CartDAO();

    ArrayList<CartItem> cartList =
            cartDAO.getCartItems(
                    user.getUserId());

    double total = 0;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Cart</title>

<link rel="stylesheet"
      href="css/style.css">

<style>

.cart-container{

    width: 90%;

    margin: 40px auto;
}

.cart-card{

    background-color: white;

    padding: 20px;

    border-radius: 20px;

    margin-bottom: 20px;

    display: flex;

    align-items: center;

    justify-content: space-between;

    gap: 20px;
}

.cart-left{

    display: flex;

    align-items: center;

    gap: 20px;
}

.cart-card img{

    width: 120px;

    height: 120px;

    object-fit: cover;

    border-radius: 15px;
}

.total-box{

    background-color: white;

    padding: 25px;

    border-radius: 20px;

    margin-top: 30px;
}

.remove-btn{

    background-color: red;

    color: white;

    border: none;

    padding: 10px 20px;

    border-radius: 10px;

    cursor: pointer;
}

.checkout-btn{

    margin-top: 20px;
}

.empty-cart{

    background-color: white;

    padding: 40px;

    border-radius: 20px;

    text-align: center;
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

<div class="cart-container">

<h1>Your Cart</h1>

<%
    if(cartList.size() == 0){
%>

<div class="empty-cart">

    <h2>
        Your Cart Is Empty
    </h2>

</div>

<%
    } else {

    for(CartItem item : cartList){

    double subtotal =
            item.getPrice() *
            item.getQuantity();

    total += subtotal;
%>

<div class="cart-card">

    <div class="cart-left">

        <img src=
        "images/<%= item.getImageUrl() %>">

        <div>

            <h2>
                <%= item.getProductName() %>
            </h2>

            <p>
                Price:
                ₹ <%= item.getPrice() %>
            </p>

            <div style="margin-top:10px;">

    <a href=
    "update-cart?cartId=<%= item.getCartId() %>&quantity=<%= item.getQuantity() - 1 %>">

        <button>

            -

        </button>

    </a>

    <span style="margin:0 15px;">

        <%= item.getQuantity() %>

    </span>

    <a href=
    "update-cart?cartId=<%= item.getCartId() %>&quantity=<%= item.getQuantity() + 1 %>">

        <button>

            +

        </button>

    </a>

</div>

            <p>
                Subtotal:
                ₹ <%= subtotal %>
            </p>

        </div>

    </div>

    <div>

        <a href=
        "remove-cart-item?cartId=<%= item.getCartId() %>">

            <button class="remove-btn">

                Remove

            </button>

        </a>

    </div>

</div>

<%
    }
%>

<div class="total-box">

    <h2>
        Total Amount:
        ₹ <%= total %>
    </h2>

    <form action="checkout"
          method="post">

        <button class="btn checkout-btn">

            Place Order

        </button>

    </form>

</div>

<%
    }
%>

</div>

</body>
</html>