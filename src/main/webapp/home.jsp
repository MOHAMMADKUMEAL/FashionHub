<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.fashionhub.model.User" %>

<%
    User user =
        (User) session.getAttribute("loggedInUser");

    if(user == null){

        response.sendRedirect("login.jsp");

        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home - FashionHub</title>

<style>

body{
    font-family: Arial;
    background-color: #f5f5f5;
}

.container{
    width: 500px;
    margin: 100px auto;
    background-color: white;
    padding: 30px;
    border-radius: 10px;
    text-align: center;
}

a{
    text-decoration: none;
    color: white;
    background-color: black;
    padding: 10px 20px;
}

</style>

</head>

<body>

<div class="container">

<h1>Welcome to FashionHub</h1>

<h2>
    Hello,
    <%= user.getFullName() %>
</h2>

<br><br>

<a href="logout">
    Logout
</a>

</div>

</body>
</html>