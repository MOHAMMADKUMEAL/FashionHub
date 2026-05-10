package com.fashionhub.controller;

import java.io.IOException;

import com.fashionhub.dao.UserDAO;
import com.fashionhub.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String fullName = request.getParameter("fullName");

        String email = request.getParameter("email");

        String password = request.getParameter("password");

        String phone = request.getParameter("phone");

        String gender = request.getParameter("gender");

        String address = request.getParameter("address");

        User user = new User();

        user.setFullName(fullName);

        user.setEmail(email);

        user.setPassword(password);

        user.setPhone(phone);

        user.setGender(gender);

        user.setAddress(address);

        UserDAO userDAO = new UserDAO();

        boolean status = userDAO.registerUser(user);

        if(status) {

        	response.sendRedirect("login.jsp");
        } else {

            response.getWriter().println(
                    "Registration Failed");
        }
    }
}