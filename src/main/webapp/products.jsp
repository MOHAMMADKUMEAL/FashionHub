<%@ page import="com.fashionhub.model.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Comparator" %>
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
    ProductDAO productDAO =
            new ProductDAO();

    String category =
            request.getParameter("category");

    String search =
            request.getParameter("search");

    String sort =
            request.getParameter("sort");

    ArrayList<Product> productList;

    if(search != null &&
       !search.trim().equals("") &&
       category != null &&
       sort != null &&
       !sort.equals("")){

        productList =
                productDAO.searchByCategory(
                        category,
                        search);

        if(sort.equals("low")){

            Collections.sort(
                productList,

                new Comparator<Product>() {

                    public int compare(
                            Product p1,
                            Product p2) {

                        return Double.compare(
                                p1.getPrice(),
                                p2.getPrice());
                    }
                });

        } else {

            Collections.sort(
                productList,

                new Comparator<Product>() {

                    public int compare(
                            Product p1,
                            Product p2) {

                        return Double.compare(
                                p2.getPrice(),
                                p1.getPrice());
                    }
                });
        }

    } else if(search != null &&
              !search.trim().equals("") &&
              category != null){

        productList =
                productDAO.searchByCategory(
                        category,
                        search);

    } else if(search != null &&
              !search.trim().equals("")){

        productList =
                productDAO.searchProducts(
                        search);

    } else if(sort != null &&
              !sort.equals("")){

        productList =
                productDAO.sortProducts(
                        sort);

    } else if(category != null){

        productList =
                productDAO.getProductsByCategory(
                        category);

    } else {

        productList =
                productDAO.getAllProducts();
    }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>FashionHub</title>

<link rel="stylesheet"
      href="css/style.css">

</head>

<body>

<!-- NAVBAR -->

<div class="navbar">

    <div class="logo">
        FashionHub
    </div>

    <!-- SEARCH FORM -->

    <form action="products.jsp"
          method="get">

        <input type="hidden"
               name="category"

               value="<%= category == null ? "" : category %>">

        <div class="search-box">

            <input type="text"
                   name="search"

                   placeholder="Search fashion...">

        </div>

    </form>

    <div class="nav-links">

        <a href=
        "products.jsp?category=Men&search=">

            Men

        </a>

        <a href=
        "products.jsp?category=Women&search=">

            Women

        </a>

        <a href=
        "products.jsp?category=Kids&search=">

            Kids

        </a>

        <a href=
        "products.jsp">

            All Products

        </a>

        <a href=
        "cart.jsp">

            Cart

        </a>

        <a href=
        "orders.jsp">

            My Orders

        </a>

        <a href=
        "logout">

            Logout

        </a>

    </div>

</div>

<!-- HERO SECTION -->

<div class="hero">

    <div class="hero-content">

        <h1>FashionHub</h1>

        <p>
            Discover Modern Fashion Trends
        </p>

    </div>

</div>

<!-- SORTING -->

<div style="width:90%;
            margin:auto;
            margin-bottom:20px;
            display:flex;
            justify-content:flex-end;">

    <form action="products.jsp"
          method="get">

        <input type="hidden"
               name="category"

               value="<%= category == null ? "" : category %>">

        <input type="hidden"
               name="search"

               value="<%= search == null ? "" : search %>">

        <select name="sort"
                onchange="this.form.submit()"

                style="
                padding:12px;
                border-radius:10px;
                border:1px solid #ccc;
                width:220px;">

            <option value="">

                Sort By

            </option>

            <option value="low">

                Price Low To High

            </option>

            <option value="high">

                Price High To Low

            </option>

        </select>

    </form>

</div>

<!-- PRODUCTS -->

<div class="products-container">

<%
    for(Product product : productList){
%>

<div class="product-card">

    <a href=
    "product-details.jsp?id=<%= product.getProductId() %>">

        <img src=
        "images/<%= product.getImageUrl() %>"
        alt="Product Image">

    </a>

    <h3>
        <%= product.getName() %>
    </h3>

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

    <p class="price">
        ₹ <%= product.getPrice() %>
    </p>

    <%
        if(product.getStockQuantity() > 0){
    %>

    <a href=
    "product-details.jsp?id=<%= product.getProductId() %>">

        <button class="btn">

            View Product

        </button>

    </a>

    <%
        } else {
    %>

    <button class="btn"
            style="background-color:gray;">

        Out Of Stock

    </button>

    <%
        }
    %>

</div>

<%
    }
%>

</div>

</body>
</html>