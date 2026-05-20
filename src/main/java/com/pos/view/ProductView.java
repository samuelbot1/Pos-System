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

    // =========================
    // ESTILOS
    // =========================

    Color fondo = new Color(24, 24, 28);
    Color panelColor = new Color(35, 35, 40);
    Color botonColor = new Color(0, 120, 215);
    Color texto = Color.WHITE;

    // PANEL PRINCIPAL
    panel.setBackground(fondo);

    topPanel.setBackground(panelColor);

    // LABELS
    for(Component c : topPanel.getComponents()){

        if(c instanceof JLabel){

            c.setForeground(texto);

            c.setFont(
                    new Font("Segoe UI",
                            Font.BOLD,
                            14)
            );
        }
    }

    // TEXTFIELDS
    txtNombre.setBackground(new Color(50,50,55));
    txtNombre.setForeground(Color.WHITE);
    txtNombre.setCaretColor(Color.WHITE);

    txtPrecio.setBackground(new Color(50,50,55));
    txtPrecio.setForeground(Color.WHITE);
    txtPrecio.setCaretColor(Color.WHITE);

    txtStock.setBackground(new Color(50,50,55));
    txtStock.setForeground(Color.WHITE);
    txtStock.setCaretColor(Color.WHITE);

    txtBuscar.setBackground(new Color(50,50,55));
    txtBuscar.setForeground(Color.WHITE);
    txtBuscar.setCaretColor(Color.WHITE);

    // BOTONES
    btnGuardar.setBackground(botonColor);
    btnGuardar.setForeground(Color.WHITE);
    btnGuardar.setFocusPainted(false);

    btnEliminar.setBackground(new Color(180,40,40));
    btnEliminar.setForeground(Color.WHITE);
    btnEliminar.setFocusPainted(false);

    btnBuscar.setBackground(new Color(70,70,70));
    btnBuscar.setForeground(Color.WHITE);
    btnBuscar.setFocusPainted(false);

    // TABLA
    table.setBackground(new Color(40,40,45));
    table.setForeground(Color.WHITE);

    table.setGridColor(new Color(70,70,70));

    table.setRowHeight(28);

    table.setFont(new Font("Segoe UI", Font.PLAIN, 13));

    table.getTableHeader().setBackground(new Color(30,30,30));

    table.getTableHeader().setForeground(Color.WHITE);

    table.getTableHeader().setFont(
            new Font("Segoe UI",
                    Font.BOLD,
                    14)
    );

    // SCROLL
    scrollPane.getViewport().setBackground(fondo);

    // FRAME
    getContentPane().setBackground(fondo);

    // EVENTOS
    btnGuardar.addActionListener(e -> saveProduct());

    btnEliminar.addActionListener(e -> deleteProduct());

    btnBuscar.addActionListener(e -> searchProduct());
}

   private void saveProduct() {

    String nombre = txtNombre.getText();

    double precio;
    int stock;

    try {

        precio = Double.parseDouble(txtPrecio.getText());
        stock = Integer.parseInt(txtStock.getText());

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(this,
                "Precio o stock invalidos");

        return;
    }

    Producto producto = new Producto();

    producto.setNombre(nombre);
    producto.setPrecio(precio);
    producto.setStock(stock);

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
}

    private void loadProducts() {

    model.setRowCount(0);

    try {

        ArrayList<Producto> productos =
                controller.getProducts();

        System.out.println(productos);

        for (Producto producto : productos) {

            Object[] fila = {
                    producto.getId(),
                    producto.getNombre(),
                    producto.getPrecio(),
                    producto.getStock()
            };

            model.addRow(fila);
        }

    } catch (Exception e) {

        e.printStackTrace();

        JOptionPane.showMessageDialog(this,
                e.getMessage());
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