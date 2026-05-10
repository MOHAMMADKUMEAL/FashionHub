package com.fashionhub.controller;

import java.io.IOException;

import com.fashionhub.dao.CartDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/remove-cart-item")
public class RemoveCartServlet
        extends HttpServlet {

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int cartId =
                Integer.parseInt(
                        request.getParameter(
                                "cartId"));

        CartDAO cartDAO =
                new CartDAO();

        cartDAO.removeCartItem(cartId);

        response.sendRedirect("cart.jsp");
    }
}