package com.AdminPage;

import com.app.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.CostomerPage.*;

public class OrderDAO {

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM transaksi";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String username = rs.getString("username");
                String itemName = rs.getString("item_name");
                int quantity = rs.getInt("quantity");
                double totalPrice = rs.getDouble("total_price");

                orders.add(new Order(username, itemName, quantity, totalPrice));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    public void deleteOrder(int id) {
        String query = "DELETE FROM transaksi WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
