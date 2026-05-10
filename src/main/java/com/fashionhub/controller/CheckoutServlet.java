package com.fashionhub.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.fashionhub.dao.CartDAO;
import com.fashionhub.dao.OrderDAO;
import com.fashionhub.model.CartItem;
import com.fashionhub.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkout")
public class CheckoutServlet
        extends HttpServlet {

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession();

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

        double totalAmount = 0;

        for(CartItem item : cartList){

            totalAmount +=
                    item.getPrice() *
                    item.getQuantity();
        }

        OrderDAO orderDAO =
                new OrderDAO();

        boolean status =
                orderDAO.placeOrder(
                        user.getUserId(),
                        cartList,
                        totalAmount);

        if(status){

            response.sendRedirect(
                    "order-success.jsp");

        } else {

            response.getWriter().println(
                    "Order Failed");
        }
    }
}