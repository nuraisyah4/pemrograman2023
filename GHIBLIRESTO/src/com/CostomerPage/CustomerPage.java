package com.CostomerPage;


import com.AdminPage.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CustomerPage extends JFrame {
    private JPanel container;
    private JPanel sidebarPanel;
    private JTabbedPane tabbedPane;
    private JButton listMenuBtn;
    private JButton makananBtn;
    private JButton minumanBtn;
    private JButton orderBtn;
    private JLabel sidebarLabel;
    private JPanel cartPanel;
    private List<Order> cartItems = new ArrayList<>();

    public CustomerPage() {
        setTitle("Customer Dashboard");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createUIComponents();
        setContentPane(container);
    }

    private void createUIComponents() {
        container = new JPanel(new BorderLayout());

        // membuat sidebar
        sidebarPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding around components

        sidebarLabel = new JLabel("Ghibli Restaurant", JLabel.CENTER);
        sidebarLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Span all columns
        gbc.anchor = GridBagConstraints.CENTER;
        sidebarPanel.add(sidebarLabel, gbc);

        listMenuBtn = new JButton("List Menu");
        makananBtn = new JButton("Makanan");
        minumanBtn = new JButton("Minuman");
        orderBtn = new JButton("Order");

        gbc.gridy = 1;
        sidebarPanel.add(listMenuBtn, gbc);

        gbc.gridy = 2;
        sidebarPanel.add(makananBtn, gbc);

        gbc.gridy = 3;
        sidebarPanel.add(minumanBtn, gbc);

        gbc.gridy = 4;
        sidebarPanel.add(orderBtn, gbc);

        tabbedPane = new JTabbedPane();
        cartPanel = new JPanel(new BorderLayout());

        JPanel makananPanel = new JPanel();
        makananPanel.setLayout(new GridLayout(0, 2, 10, 10));
        JScrollPane makananScrollPane = new JScrollPane(makananPanel);
        tabbedPane.addTab("Makanan", makananScrollPane);

        JPanel minumanPanel = new JPanel();
        minumanPanel.setLayout(new GridLayout(0, 2, 10, 10));
        JScrollPane minumanScrollPane = new JScrollPane(minumanPanel);
        tabbedPane.addTab("Minuman", minumanScrollPane);

        container.add(sidebarPanel, BorderLayout.WEST);
        container.add(tabbedPane, BorderLayout.CENTER);
        container.add(cartPanel, BorderLayout.SOUTH);

        listMenuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadProducts(makananPanel, "makanan");
                loadProducts(minumanPanel, "minuman");
            }
        });

        makananBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(0);
            }
        });

        minumanBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(1);
            }
        });

        orderBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cartItems.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Your cart is empty.");
                } else {
                    OrderDAO orderDAO = new OrderDAO();
                    for (Order order : cartItems) {
                        orderDAO.insertOrder(order);
                    }
                    JOptionPane.showMessageDialog(null, "Order has been placed successfully!");
                    cartItems.clear();
                    updateCartDisplay();
                }
            }
        });

//        mengambil semua data product
        loadProducts(makananPanel, "makanan");
        loadProducts(minumanPanel, "minuman");
    }

    private void loadProducts(JPanel panel, String category) {
        panel.removeAll();
        List<Product> products = Product.getProductsByCategory(category);

        for (Product product : products) {
            JPanel productBox = new JPanel();
            productBox.setLayout(new BoxLayout(productBox, BoxLayout.Y_AXIS));

            JLabel nameLabel = new JLabel(product.getName());
            JLabel priceLabel = new JLabel("Price: " + product.getPrice());

            ImageIcon originalIcon = new ImageIcon(getClass().getResource(product.getPathImage()));
            Image originalImage = originalIcon.getImage();
            Image scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            JLabel imageLabel = new JLabel(scaledIcon);

            JButton addButton = new JButton("+");
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = "customer_username";
                    int quantity = 1;
                    double totalPrice = product.getPrice() * quantity;

                    Order newOrder = new Order(username, product.getName(), quantity, totalPrice);
                    cartItems.add(newOrder);
                    updateCartDisplay();
                }
            });

            productBox.add(imageLabel);
            productBox.add(nameLabel);
            productBox.add(priceLabel);
            productBox.add(addButton);

            panel.add(productBox);
        }

        panel.revalidate();
        panel.repaint();
    }

    private void updateCartDisplay() {
        cartPanel.removeAll();

        JPanel cartContentPanel = new JPanel();
        cartContentPanel.setLayout(new GridLayout(cartItems.size(), 1, 10, 10));

        for (Order order : cartItems) {
            JLabel orderLabel = new JLabel(String.format("%s: %d x %s (Total: %.2f)", order.getUsername(), order.getQuantity(), order.getItemName(), order.getTotalPrice()));
            cartContentPanel.add(orderLabel);
        }

        cartPanel.add(new JScrollPane(cartContentPanel), BorderLayout.CENTER);
        cartPanel.revalidate();
        cartPanel.repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new CustomerPage();
        frame.setVisible(true);
    }
}
