package com.fashionhub.controller;

import java.io.IOException;

import com.fashionhub.dao.CartDAO;
import com.fashionhub.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession();

        User user =
                (User) session.getAttribute(
                        "loggedInUser");

        if(user == null) {

            response.sendRedirect("login.jsp");

            return;
        }

        int userId =
                user.getUserId();

        int productId =
                Integer.parseInt(
                        request.getParameter(
                                "productId"));

        int quantity = 1;

        CartDAO cartDAO =
                new CartDAO();

        boolean status =
                cartDAO.addToCart(
                        userId,
                        productId,
                        quantity);

        if(status) {

            response.sendRedirect("cart.jsp");

        } else {

            response.getWriter().println(
                    "Failed To Add Cart");
        }
    }
}