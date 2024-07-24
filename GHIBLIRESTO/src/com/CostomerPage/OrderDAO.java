package com.CostomerPage;

import com.app.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDAO {

    private static final String INSERT_ORDER_SQL = "INSERT INTO transaksi (username, item_name, quantity, total_price) VALUES (?, ?, ?, ?)";

    public void insertOrder(Order order) {
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER_SQL)) {

            preparedStatement.setString(1, order.getUsername());
            preparedStatement.setString(2, order.getItemName());
            preparedStatement.setInt(3, order.getQuantity());
            preparedStatement.setDouble(4, order.getTotalPrice());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
