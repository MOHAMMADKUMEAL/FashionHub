package com.fashionhub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.fashionhub.model.CartItem;
import com.fashionhub.util.DBConnection;

public class CartDAO {

    public boolean addToCart(
            int userId,
            int productId,
            int quantity) {

        boolean status = false;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String checkQuery =
                    "SELECT * FROM cart WHERE user_id=? AND product_id=?";

            PreparedStatement checkStatement =
                    connection.prepareStatement(
                            checkQuery);

            checkStatement.setInt(1, userId);

            checkStatement.setInt(2, productId);

            ResultSet resultSet =
                    checkStatement.executeQuery();

            if(resultSet.next()) {

                int existingQuantity =
                        resultSet.getInt("quantity");

                int newQuantity =
                        existingQuantity + quantity;

                String updateQuery =
                        "UPDATE cart SET quantity=? WHERE user_id=? AND product_id=?";

                PreparedStatement updateStatement =
                        connection.prepareStatement(
                                updateQuery);

                updateStatement.setInt(
                        1,
                        newQuantity);

                updateStatement.setInt(
                        2,
                        userId);

                updateStatement.setInt(
                        3,
                        productId);

                int rowsUpdated =
                        updateStatement.executeUpdate();

                if(rowsUpdated > 0){

                    status = true;
                }

            } else {

                String insertQuery =
                        "INSERT INTO cart(user_id, product_id, quantity) VALUES(?,?,?)";

                PreparedStatement insertStatement =
                        connection.prepareStatement(
                                insertQuery);

                insertStatement.setInt(
                        1,
                        userId);

                insertStatement.setInt(
                        2,
                        productId);

                insertStatement.setInt(
                        3,
                        quantity);

                int rowsInserted =
                        insertStatement.executeUpdate();

                if(rowsInserted > 0){

                    status = true;
                }
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return status;
    }

    public ArrayList<CartItem> getCartItems(
            int userId) {

        ArrayList<CartItem> cartList =
                new ArrayList<CartItem>();

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "SELECT c.cart_id, p.product_id, p.name, p.price, p.image_url, c.quantity "
                    +
                    "FROM cart c "
                    +
                    "JOIN products p "
                    +
                    "ON c.product_id = p.product_id "
                    +
                    "WHERE c.user_id=?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(1, userId);

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            while(resultSet.next()) {

                CartItem item =
                        new CartItem();

                item.setCartId(
                        resultSet.getInt("cart_id"));

                item.setProductId(
                        resultSet.getInt("product_id"));

                item.setProductName(
                        resultSet.getString("name"));

                item.setPrice(
                        resultSet.getDouble("price"));

                item.setQuantity(
                        resultSet.getInt("quantity"));

                item.setImageUrl(
                        resultSet.getString("image_url"));

                cartList.add(item);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return cartList;
    }

    public boolean removeCartItem(int cartId) {

        boolean status = false;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "DELETE FROM cart WHERE cart_id=?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(1, cartId);

            int rowsDeleted =
                    preparedStatement.executeUpdate();

            if(rowsDeleted > 0) {

                status = true;
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return status;
    }
    public boolean updateQuantity(
            int cartId,
            int quantity) {

        boolean status = false;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String stockQuery =
                    "SELECT p.stock_quantity "
                    +
                    "FROM cart c "
                    +
                    "JOIN products p "
                    +
                    "ON c.product_id = p.product_id "
                    +
                    "WHERE c.cart_id=?";

            PreparedStatement stockStatement =
                    connection.prepareStatement(
                            stockQuery);

            stockStatement.setInt(1, cartId);

            ResultSet stockResult =
                    stockStatement.executeQuery();

            int availableStock = 0;

            if(stockResult.next()) {

                availableStock =
                        stockResult.getInt(
                                "stock_quantity");
            }

            if(quantity > availableStock){

                quantity = availableStock;
            }

            if(quantity <= 0){

                String deleteQuery =
                        "DELETE FROM cart WHERE cart_id=?";

                PreparedStatement deleteStatement =
                        connection.prepareStatement(
                                deleteQuery);

                deleteStatement.setInt(1, cartId);

                deleteStatement.executeUpdate();

            } else {

                String updateQuery =
                        "UPDATE cart SET quantity=? WHERE cart_id=?";

                PreparedStatement updateStatement =
                        connection.prepareStatement(
                                updateQuery);

                updateStatement.setInt(1, quantity);

                updateStatement.setInt(2, cartId);

                updateStatement.executeUpdate();
            }

            status = true;

        } catch(Exception e) {

            e.printStackTrace();
        }

        return status;
    }
}