package com.fashionhub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.fashionhub.model.User;
import com.fashionhub.util.DBConnection;

public class UserDAO {

    public boolean registerUser(User user) {

        boolean status = false;

        try {

            Connection connection = DBConnection.getConnection();

            String query =
                    "INSERT INTO users(full_name,email,password,phone,gender,address) VALUES(?,?,?,?,?,?)";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setString(1, user.getFullName());

            preparedStatement.setString(2, user.getEmail());

            preparedStatement.setString(3, user.getPassword());

            preparedStatement.setString(4, user.getPhone());

            preparedStatement.setString(5, user.getGender());

            preparedStatement.setString(6, user.getAddress());

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {

                status = true;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return status;
    }

    public User loginUser(String email, String password) {

        User user = null;

        try {

            Connection connection = DBConnection.getConnection();

            String query =
                    "SELECT * FROM users WHERE email=? AND password=?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setString(1, email);

            preparedStatement.setString(2, password);

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            if(resultSet.next()) {

                user = new User();

                user.setUserId(
                        resultSet.getInt("user_id"));

                user.setFullName(
                        resultSet.getString("full_name"));

                user.setEmail(
                        resultSet.getString("email"));

                user.setPhone(
                        resultSet.getString("phone"));

                user.setGender(
                        resultSet.getString("gender"));

                user.setAddress(
                        resultSet.getString("address"));
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return user;
    }
}