package com.pos.view;

import com.pos.controller.ProductController;
import com.pos.model.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ProductView extends JFrame {

    private ProductController controller;

    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTextField txtStock;
    private JTextField txtBuscar;

    private JTable table;
    private DefaultTableModel model;

    public ProductView(ProductController controller) {

        this.controller = controller;

        setTitle("POS SYSTEM");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();

        setVisible(true);

        loadProducts();
    }

    private void initComponents() {

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        topPanel.setBorder(
                BorderFactory.createEmptyBorder(10,10,10,10)
        );

        topPanel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        topPanel.add(txtNombre);

        topPanel.add(new JLabel("Precio:"));
        txtPrecio = new JTextField();
        topPanel.add(txtPrecio);

        topPanel.add(new JLabel("Stock:"));
        txtStock = new JTextField();
        topPanel.add(txtStock);

        JButton btnGuardar = new JButton("Guardar Producto");
        JButton btnEliminar = new JButton("Eliminar Producto");

        topPanel.add(btnGuardar);
        topPanel.add(btnEliminar);

        txtBuscar = new JTextField();
        JButton btnBuscar = new JButton("Buscar por ID");

        topPanel.add(txtBuscar);
        topPanel.add(btnBuscar);

        panel.add(topPanel, BorderLayout.NORTH);

        model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Precio");
        model.addColumn("Stock");
            boolean saved = controller.createProduct(producto);

if (saved) {

    JOptionPane.showMessageDialog(this,
            "Producto guardado correctamente");

    clearFields();
    loadProducts();

} else {

    JOptionPane.showMessageDialog(this,
            "Datos invalidos");
}