package com.AdminPage;

import com.app.config.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private int id;
    private String name;
    private double price;
    private String pathImage;
    private String category; // Menambahkan kategori produk

    // Constructor
    public Product(String name, double price, String pathImage, String category) {
        this.name = name;
        this.price = price;
        this.pathImage = pathImage;
        this.category = category;
    }

    // Getters and setters
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Method to get products by category
    public static List<Product> getProductsByCategory(String category) {
        List<Product> products = new ArrayList<>();
        String table = category.equalsIgnoreCase("makanan") ? "makanan" : "minuman";

        try (Connection conn = DatabaseConfig.getConnection()) {
            String sql = "SELECT * FROM " + table;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Product product = new Product(
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("pathImage"),
                        category
                );
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public void save() {
        String table = category.equalsIgnoreCase("makanan") ? "makanan" : "minuman";

        try (Connection conn = DatabaseConfig.getConnection()) {
            String sql = "INSERT INTO " + table + " (name, price, pathImage) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.setString(3, pathImage);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        String table = category.equalsIgnoreCase("makanan") ? "makanan" : "minuman";

        try (Connection conn = DatabaseConfig.getConnection()) {
            String sql = "UPDATE " + table + " SET name = ?, price = ?, pathImage = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.setString(3, pathImage);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
