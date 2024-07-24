package com.AdminPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DashboardAdmin extends JFrame {
    private JPanel Container;
    private JPanel sidebarPanel;
    private JPanel productPanel;
    private JButton Dashboard_btn;
    private JButton DaftarTransaksi_Btn;
    private JButton ManageMenu_btn;
    private JButton addMenuBtn;
    private JLabel Menu;
    private JTabbedPane AdminDashboard;

    public DashboardAdmin() {
        setTitle("Dashboard Admin");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createUIComponents();
        setContentPane(Container);
    }

    private void createUIComponents() {
        Container = new JPanel(new BorderLayout());

        // siderbar
        sidebarPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding around components

        JLabel sidebarText = new JLabel("Ghibli Restaurant", SwingConstants.CENTER);
        sidebarText.setFont(new Font("Arial", Font.BOLD, 20)); // Customize font size and style
        sidebarText.setPreferredSize(new Dimension(200, 100)); // Set preferred size if needed
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Span all columns
        gbc.anchor = GridBagConstraints.CENTER;
        sidebarPanel.add(sidebarText, gbc);

        //  sidebar buttons
        ManageMenu_btn = new JButton("Manage Menu");
        DaftarTransaksi_Btn = new JButton("Daftar Transaksi");
        addMenuBtn = new JButton("Tambah Menu");

        gbc.gridy = 1;
        gbc.weighty = 0; // No vertical stretching for the buttons
        gbc.anchor = GridBagConstraints.CENTER;
        sidebarPanel.add(ManageMenu_btn, gbc);

        gbc.gridy = 2;
        sidebarPanel.add(DaftarTransaksi_Btn, gbc);

        gbc.gridy = 3;
        sidebarPanel.add(addMenuBtn, gbc);

        gbc.gridy = 4;
        gbc.weighty = 1.0;
        sidebarPanel.add(new JLabel(), gbc);

        AdminDashboard = new JTabbedPane();

        productPanel = new JPanel(new BorderLayout());

        JPanel makananPanel = new JPanel();
        makananPanel.setLayout(new GridLayout(0, 2, 10, 10));
        JScrollPane makananScrollPane = new JScrollPane(makananPanel);
        AdminDashboard.addTab("Makanan", makananScrollPane);

        JPanel minumanPanel = new JPanel();
        minumanPanel.setLayout(new GridLayout(0, 2, 10, 10));
        JScrollPane minumanScrollPane = new JScrollPane(minumanPanel);
        AdminDashboard.addTab("Minuman", minumanScrollPane);

        // mengambil semua products
        loadProducts(makananPanel, "makanan");
        loadProducts(minumanPanel, "minuman");

        Container.add(sidebarPanel, BorderLayout.WEST);
        Container.add(AdminDashboard, BorderLayout.CENTER);

        // Action listener untuk tombol tambah menu
        addMenuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tampilkan dialog untuk menambah menu
                new MenuDialog(DashboardAdmin.this, null).setVisible(true);
                loadProducts(makananPanel, "makanan");
                loadProducts(minumanPanel, "minuman");
            }
        });

        // Action listener untuk tombol daftar transaksi
        // Di dalam kelas DashboardAdmin

        DaftarTransaksi_Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tampilkan dialog daftar transaksi
                new OrderDialog(DashboardAdmin.this).setVisible(true);
            }
        });

    }




    private void loadProducts(JPanel panel, String category) {
        panel.removeAll(); // Hapus semua komponen sebelum menambah yang baru
        List<Product> products = Product.getProductsByCategory(category);

        for (Product product : products) {
            JPanel productBox = new JPanel();
            productBox.setLayout(new BoxLayout(productBox, BoxLayout.Y_AXIS));

            JLabel nameLabel = new JLabel(product.getName());
            JLabel priceLabel = new JLabel("Price: " + product.getPrice());

            // Debug path
            System.out.println("Loading image from: " + product.getPathImage());

            // Assuming pathImage is in resources folder
            ImageIcon originalIcon = new ImageIcon(getClass().getResource(product.getPathImage()));
            Image originalImage = originalIcon.getImage();
            Image scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            JLabel imageLabel = new JLabel(scaledIcon);

            JButton editButton = new JButton("Edit");
            editButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Tampilkan dialog untuk mengedit menu
                    new MenuDialog(DashboardAdmin.this, product).setVisible(true);
                    loadProducts(panel, category);
                }
            });

            productBox.add(imageLabel);
            productBox.add(nameLabel);
            productBox.add(priceLabel);
            productBox.add(editButton);

            panel.add(productBox);
        }

        panel.revalidate();
        panel.repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new DashboardAdmin();
        frame.setVisible(true);
    }
}
