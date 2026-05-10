package com.fashionhub.controller;

import java.io.IOException;

import com.fashionhub.dao.UserDAO;
import com.fashionhub.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String email =
                request.getParameter("email");

        String password =
                request.getParameter("password");

        UserDAO userDAO =
                new UserDAO();

        User user =
                userDAO.loginUser(
                        email,
                        password);

        if(user != null) {

            HttpSession session =
                    request.getSession();

            session.setAttribute(
                    "loggedInUser",
                    user);

            response.sendRedirect(
                    "products.jsp");

        } else {

            response.sendRedirect(
                    "login.jsp?error=Invalid Email or Password");
        }
    }
}