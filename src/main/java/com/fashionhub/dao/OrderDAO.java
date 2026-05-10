package com.fashionhub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.fashionhub.model.CartItem;
import com.fashionhub.model.Order;
import com.fashionhub.util.DBConnection;

public class OrderDAO {

    public boolean placeOrder(
            int userId,
            ArrayList<CartItem> cartList,
            double totalAmount) {

        boolean status = false;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String orderQuery =
                    "INSERT INTO orders(user_id, total_amount, status) VALUES(?,?,?)";

            PreparedStatement orderStatement =
                    connection.prepareStatement(
                            orderQuery,
                            PreparedStatement.RETURN_GENERATED_KEYS);

            orderStatement.setInt(1, userId);

            orderStatement.setDouble(2, totalAmount);

            orderStatement.setString(3, "Placed");

            int orderInserted =
                    orderStatement.executeUpdate();

            if(orderInserted > 0) {

                ResultSet generatedKeys =
                        orderStatement.getGeneratedKeys();

                int orderId = 0;

                if(generatedKeys.next()) {

                    orderId =
                            generatedKeys.getInt(1);
                }

                String itemQuery =
                        "INSERT INTO order_items(order_id, product_id, quantity, price, subtotal) VALUES(?,?,?,?,?)";

                PreparedStatement itemStatement =
                        connection.prepareStatement(
                                itemQuery);

                for(CartItem item : cartList){

                    double subtotal =
                            item.getPrice() *
                            item.getQuantity();

                    itemStatement.setInt(
                            1,
                            orderId);

                    itemStatement.setInt(
                            2,
                            item.getProductId());

                    itemStatement.setInt(
                            3,
                            item.getQuantity());

                    itemStatement.setDouble(
                            4,
                            item.getPrice());

                    itemStatement.setDouble(
                            5,
                            subtotal);

                    itemStatement.executeUpdate();

                    String stockQuery =
                            "UPDATE products "
                            +
                            "SET stock_quantity = stock_quantity - ? "
                            +
                            "WHERE product_id=?";

                    PreparedStatement stockStatement =
                            connection.prepareStatement(
                                    stockQuery);

                    stockStatement.setInt(
                            1,
                            item.getQuantity());

                    stockStatement.setInt(
                            2,
                            item.getProductId());

                    stockStatement.executeUpdate();
                }

                String clearCartQuery =
                        "DELETE FROM cart WHERE user_id=?";

                PreparedStatement clearStatement =
                        connection.prepareStatement(
                                clearCartQuery);

                clearStatement.setInt(1, userId);

                clearStatement.executeUpdate();

                status = true;
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return status;
    }

    public ArrayList<Order> getOrdersByUser(
            int userId) {

        ArrayList<Order> orderList =
                new ArrayList<Order>();

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "SELECT * FROM orders WHERE user_id=? ORDER BY order_id DESC";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(1, userId);

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            while(resultSet.next()) {

                Order order =
                        new Order();

                order.setOrderId(
                        resultSet.getInt("order_id"));

                order.setTotalAmount(
                        resultSet.getDouble(
                                "total_amount"));

                order.setOrderStatus(
                        resultSet.getString(
                                "status"));

                orderList.add(order);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return orderList;
    }
}