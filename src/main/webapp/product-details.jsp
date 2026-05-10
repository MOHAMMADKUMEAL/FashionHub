<%@ page import="com.fashionhub.model.User" %>
<%@ page import="com.fashionhub.dao.ProductDAO" %>
<%@ page import="com.fashionhub.model.Product" %>

<%
    User user =
        (User) session.getAttribute(
                "loggedInUser");

    if(user == null){

        response.sendRedirect("login.jsp");

        return;
    }
%>

<%
    int productId =
        Integer.parseInt(
                request.getParameter("id"));

    ProductDAO productDAO =
            new ProductDAO();

    Product product =
            productDAO.getProductById(productId);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Product Details</title>

<link rel="stylesheet"
      href="css/style.css">

<style>

.details-container{

    width: 80%;

    margin: 50px auto;

    background-color: white;

    padding: 30px;

    border-radius: 20px;

    display: flex;

    gap: 40px;
}

.details-image{

    width: 400px;
}

.details-image img{

    width: 100%;

    border-radius: 20px;
}

.details-info{

    flex: 1;
}

.details-info h1{

    margin-bottom: 20px;
}

.details-info p{

    margin-top: 15px;

    font-size: 18px;
}

.stock{

    margin-top: 15px;

    color: green;

    font-weight: bold;
}

.out-stock{

    color: red;
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

<div class="details-container">

    <div class="details-image">

        <img src=
        "images/<%= product.getImageUrl() %>">

    </div>

    <div class="details-info">

        <h1>
            <%= product.getName() %>
        </h1>

        <p>
            Brand:
            <%= product.getBrand() %>
        </p>

        <p>
            Category:
            <%= product.getCategory() %>
        </p>

        <p>
            Size:
            <%= product.getSize() %>
        </p>

        <p>
            Description:
            <%= product.getDescription() %>
        </p>

        <p class="price">
            ₹ <%= product.getPrice() %>
        </p>

        <%
            if(product.getStockQuantity() > 0){
        %>

        <p class="stock">

            In Stock:
            <%= product.getStockQuantity() %>

        </p>

        <form action="add-to-cart"
              method="post">

            <input type="hidden"
                   name="productId"

                   value=
                   "<%= product.getProductId() %>">

            <button class="btn">

                Add To Cart

            </button>

        </form>

        <%
            } else {
        %>

        <p class="stock out-stock">

            Out Of Stock

        </p>

        <button class="btn"
                style="background-color:gray;">

            Out Of Stock

        </button>

        <%
            }
        %>

    </div>

</div>

</body>
</html>