package com.pos.repository;

import com.pos.config.DatabaseConfig;
import com.pos.model.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductRepository {

    public void guardarProducto(Producto producto) {

        String sql =
                "INSERT INTO productos(nombre, precio, stock) VALUES (?, ?, ?)";

        try (
                Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, producto.getNombre());
            statement.setDouble(2, producto.getPrecio());
            statement.setInt(3, producto.getStock());

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarProducto(int id) {

        String sql = "DELETE FROM productos WHERE id = ?";

        try (
                Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Producto> listarProductos() {

        ArrayList<Producto> productos = new ArrayList<>();

        String sql = "SELECT * FROM productos";

        try (
                Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql);
                ResultSet rs = statement.executeQuery()
        ) {

            while (rs.next()) {

                Producto producto = new Producto();

                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));

                productos.add(producto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return productos;
    }

    public Producto buscarPorId(int id) {

        Producto producto = null;

        String sql = "SELECT * FROM productos WHERE id = ?";

}