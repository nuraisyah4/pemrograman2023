package com.AdminPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuDialog extends JDialog {
    private JTextField nameField;
    private JTextField priceField;
    private JTextField pathImageField;
    private JComboBox<String> categoryComboBox;
    private JButton saveButton;
    private JButton cancelButton;
    private Product product;

    public MenuDialog(JFrame parent, Product product) {
        super(parent, true);
        setTitle(product == null ? "Tambah Menu" : "Edit Menu");
        setSize(400, 300);
        setLocationRelativeTo(parent);

        this.product = product;

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.add(new JLabel("Nama:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Harga:"));
        priceField = new JTextField();
        panel.add(priceField);

        panel.add(new JLabel("Path Gambar:"));
        pathImageField = new JTextField();
        panel.add(pathImageField);

        panel.add(new JLabel("Kategori:"));
        categoryComboBox = new JComboBox<>(new String[]{"makanan", "minuman"});
        panel.add(categoryComboBox);

        saveButton = new JButton("Simpan");
        cancelButton = new JButton("Batal");

        panel.add(saveButton);
        panel.add(cancelButton);

        add(panel, BorderLayout.CENTER);

        if (product != null) {
            nameField.setText(product.getName());
            priceField.setText(String.valueOf(product.getPrice()));
            pathImageField.setText(product.getPathImage());
            categoryComboBox.setSelectedItem(product.getCategory());
        }

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveProduct();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void saveProduct() {
        String name = nameField.getText();
        double price = Double.parseDouble(priceField.getText());
        String pathImage = pathImageField.getText();
        String category = (String) categoryComboBox.getSelectedItem();

        if (product == null) {
            product = new Product(name, price, pathImage, category);
            product.save();
        } else {
            product.setName(name);
            product.setPrice(price);
            product.setPathImage(pathImage);
            product.setCategory(category);
            product.update();
        }

        dispose();
    }
}
