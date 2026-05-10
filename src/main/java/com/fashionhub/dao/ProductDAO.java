package com.fashionhub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.fashionhub.model.Product;
import com.fashionhub.util.DBConnection;

public class ProductDAO {

    public ArrayList<Product> getAllProducts() {

        ArrayList<Product> productList =
                new ArrayList<Product>();

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "SELECT * FROM products";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            while(resultSet.next()) {

                Product product =
                        createProduct(resultSet);

                productList.add(product);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return productList;
    }

    public ArrayList<Product> getProductsByCategory(
            String category) {

        ArrayList<Product> productList =
                new ArrayList<Product>();

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "SELECT * FROM products WHERE category=?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setString(1, category);

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            while(resultSet.next()) {

                Product product =
                        createProduct(resultSet);

                productList.add(product);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return productList;
    }

    public ArrayList<Product> searchProducts(
            String keyword) {

        ArrayList<Product> productList =
                new ArrayList<Product>();

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "SELECT * FROM products WHERE name LIKE ?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setString(
                    1,
                    "%" + keyword + "%");

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            while(resultSet.next()) {

                Product product =
                        createProduct(resultSet);

                productList.add(product);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return productList;
    }

    public Product getProductById(int productId) {

        Product product = null;

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "SELECT * FROM products WHERE product_id=?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(1, productId);

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            if(resultSet.next()) {

                product = createProduct(resultSet);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return product;
    }

    private Product createProduct(
            ResultSet resultSet)
            throws Exception {

        Product product =
                new Product();

        product.setProductId(
                resultSet.getInt("product_id"));

        product.setName(
                resultSet.getString("name"));

        product.setBrand(
                resultSet.getString("brand"));

        product.setCategory(
                resultSet.getString("category"));

        product.setPrice(
                resultSet.getDouble("price"));

        product.setDescription(
                resultSet.getString("description"));

        product.setSize(
                resultSet.getString("size"));

        product.setStockQuantity(
                resultSet.getInt("stock_quantity"));

        product.setImageUrl(
                resultSet.getString("image_url"));

        return product;
    }
    public ArrayList<Product> sortProducts(
            String sortType) {

        ArrayList<Product> productList =
                new ArrayList<Product>();

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query = "";

            if(sortType.equals("low")) {

                query =
                        "SELECT * FROM products ORDER BY price ASC";

            } else {

                query =
                        "SELECT * FROM products ORDER BY price DESC";
            }

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            while(resultSet.next()) {

                Product product =
                        createProduct(resultSet);

                productList.add(product);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return productList;
    }
    public ArrayList<Product> searchByCategory(
            String category,
            String keyword) {

        ArrayList<Product> productList =
                new ArrayList<Product>();

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "SELECT * FROM products "
                    +
                    "WHERE category=? "
                    +
                    "AND name LIKE ?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setString(
                    1,
                    category);

            preparedStatement.setString(
                    2,
                    "%" + keyword + "%");

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            while(resultSet.next()) {

                Product product =
                        createProduct(resultSet);

                productList.add(product);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return productList;
    }
    public ArrayList<Product> getProductsByPage(
            int start,
            int limit) {

        ArrayList<Product> productList =
                new ArrayList<Product>();

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "SELECT * FROM products LIMIT ?, ?";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(1, start);

            preparedStatement.setInt(2, limit);

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            while(resultSet.next()) {

                Product product =
                        createProduct(resultSet);

                productList.add(product);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return productList;
    }
}