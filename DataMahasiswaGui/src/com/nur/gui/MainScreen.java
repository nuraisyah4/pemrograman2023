package com.nur.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MainScreen extends JFrame{
    private JPanel panelMain;
    private JList jListMahasiswa;
    private JButton buttonFilter;
    private JTextField textFieldFilter;
    private JTextField textFieldNim;
    private JTextField textFieldNama;
    private JTextField textFieldIpk;
    private JButton buttonSave;
    private JButton buttonDelete;
    private JButton buttonClear;
    private final List<Mahasiswa> arrayListMahasiswa = new ArrayList<>();
    private final DefaultListModel defaultListModel = new DefaultListModel<>();
    class Mahasiswa {
        private String nim;
        private String nama;
        private Float ipk;

        public String getNim() {
            return nim;
        }
        public void setNim(String nim) {
            this.nim = nim;
        }

        public String getNama() {
            return nama;
        }
        public void setNama(String nama) {
            this.nama = nama;
        }

        public Float getIpk() {
            return ipk;
        }
        public void setIpk(Float ipk) {
            this.ipk =ipk;
        }

    }
    public MainScreen() {
        super("Data Mahasiswa");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nim = textFieldNim.getText();
                String nama = textFieldNama.getText();
                Float ipk = Float.parseFloat(textFieldIpk.getText());

                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setNim(nim);
                mahasiswa.setNama(nama);
                mahasiswa.setIpk(ipk);

                arrayListMahasiswa.add(mahasiswa);
                clearFrom();

                fromListMahasiswaToListModel();
            }
        });
        jListMahasiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int index = jListMahasiswa.getSelectedIndex();

                if (index < 0)
                    return;
                String nama = jListMahasiswa.getSelectedValue().toString();
                for (int i = 0; i < arrayListMahasiswa.size(); i++) {
                    if (arrayListMahasiswa.get(i).getNama().equals(nama)) {
                        Mahasiswa mahasiswa = arrayListMahasiswa.get(i);
                        textFieldIpk.setText(String.valueOf(mahasiswa.getIpk()));
                        textFieldNama.setText(mahasiswa.getNama());
                        textFieldNim.setText(mahasiswa.getNim());
                        break;
                    }
                }

            }
        });
        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFrom();
            }
        });
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jListMahasiswa.getSelectedIndex();

                if (index < 0)
                    return;

                String nama = jListMahasiswa.getSelectedValue().toString();

                for (int i = 0; i < arrayListMahasiswa.size(); i++) {
                    if (arrayListMahasiswa.get(i).getNama().equals(nama) ) {
                        arrayListMahasiswa.remove(i);
                        break;
                    }
                }
                clearFrom();
                fromListMahasiswaToListModel();
            }
        });
        buttonFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String KeyWord = textFieldFilter.getText();

                List<String> filtered = new ArrayList<>();

                for (int i = 0; i < arrayListMahasiswa.size(); i++) {
                    if (arrayListMahasiswa.get(i).getNama().contains(KeyWord)) {
                        filtered.add(arrayListMahasiswa.get(i).getNama());
                    }
                }

                refreshListModel(filtered);
            }
        });
    }
    private void fromListMahasiswaToListModel() {
        List<String> listNamaMahasiswa = new ArrayList<>();
        for (int i = 0; i < arrayListMahasiswa.size(); i++){
            listNamaMahasiswa.add(
                    arrayListMahasiswa.get(i).getNama()
            );
        }

        refreshListModel(listNamaMahasiswa);

    }
    private void clearFrom(){
        textFieldIpk.setText("");
        textFieldNama.setText("");
        textFieldNim.setText("");
    }
    private void refreshListModel(List<String> list) {
        defaultListModel.clear();
        defaultListModel.addAll(list);
        jListMahasiswa.setModel(defaultListModel);
    }
    public static void main(String [] args) {
        MainScreen mainScreen = new MainScreen();
        mainScreen.setVisible(true);
    }

}

