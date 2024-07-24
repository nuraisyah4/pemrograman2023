package com.AdminPage;

import com.CostomerPage.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class OrderDialog extends JDialog {
    private JPanel contentPanel;
    private JTable orderTable;
    private JButton deleteButton;
    private OrderDAO orderDAO;

    public OrderDialog(Frame parent) {
        super(parent, "Daftar Transaksi", true);
        setSize(800, 600);
        setLocationRelativeTo(parent);
        orderDAO = new OrderDAO();
        initializeUI();
        loadOrders();
    }

    private void initializeUI() {
        contentPanel = new JPanel(new BorderLayout());
        orderTable = new JTable();
        deleteButton = new JButton("Hapus");

        contentPanel.add(new JScrollPane(orderTable), BorderLayout.CENTER);
        contentPanel.add(deleteButton, BorderLayout.SOUTH);
        setContentPane(contentPanel);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedOrder();
            }
        });
    }

    private void loadOrders() {
        List<Order> orders = orderDAO.getAllOrders();
        String[] columnNames = {"ID", "Username", "Item Name", "Quantity", "Total Price"};
        Object[][] data = new Object[orders.size()][5];

        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            data[i][0] = i + 1; // ID Placeholder
            data[i][1] = order.getUsername();
            data[i][2] = order.getItemName();
            data[i][3] = order.getQuantity();
            data[i][4] = order.getTotalPrice();
        }

        orderTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    private void deleteSelectedOrder() {
        int selectedRow = orderTable.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) orderTable.getValueAt(selectedRow, 0); // Assuming ID is in the first column
            orderDAO.deleteOrder(id);
            loadOrders();
        } else {
            JOptionPane.showMessageDialog(this, "masukkan order yang akan di hapus.");
        }
    }
}
