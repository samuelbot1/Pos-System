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

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);

        btnGuardar.addActionListener(e -> saveProduct());

        btnEliminar.addActionListener(e -> deleteProduct());

        btnBuscar.addActionListener(e -> searchProduct());
    }

    private void saveProduct() {

        try {

            String nombre = txtNombre.getText();
            double precio = Double.parseDouble(txtPrecio.getText());
            int stock = Integer.parseInt(txtStock.getText());

            Producto producto = new Producto();

            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setStock(stock);

            

            if (saved) {

                JOptionPane.showMessageDialog(this,
                        "Producto guardado correctamente");

                clearFields();
                loadProducts();

            } else {

                JOptionPane.showMessageDialog(this,
                        "Datos invalidos");
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,
                    "Error en los datos");
        }
    }

    private void loadProducts() {

        model.setRowCount(0);

        ArrayList<Producto> productos = controller.getProducts();

        for (Producto producto : productos) {

            model.addRow(new Object[]{
                    producto.getId(),
                    producto.getNombre(),
                    producto.getPrecio(),
                    producto.getStock()
            });
        }
    }

    private void deleteProduct() {

        int row = table.getSelectedRow();

        if (row >= 0) {

            int id = (int) model.getValueAt(row, 0);

            controller.deleteProduct(id);

            JOptionPane.showMessageDialog(this,
                    "Producto eliminado");

            loadProducts();

        } else {

            JOptionPane.showMessageDialog(this,
                    "Seleccione un producto");
        }
    }

    private void searchProduct() {

        try {

            int id = Integer.parseInt(txtBuscar.getText());

            Producto producto = controller.searchById(id);

            if (producto != null) {

                JOptionPane.showMessageDialog(this,
                        "Producto encontrado:\n\n" +
                                "Nombre: " + producto.getNombre() +
                                "\nPrecio: " + producto.getPrecio() +
                                "\nStock: " + producto.getStock());

            } else {

                JOptionPane.showMessageDialog(this,
                        "Producto no encontrado");
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,
                    "Ingrese un ID valido");
        }
    }

    private void clearFields() {

        txtNombre.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
    }
}