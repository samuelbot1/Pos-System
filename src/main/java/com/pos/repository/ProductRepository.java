/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pos.repository;

import com.pos.config.DatabaseConfig;
import com.pos.model.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;


/**
 *
 * @author pc
 */
public class ProductRepository {
    
   public void guardarProducto(Producto producto) {

    try {

        Connection connection =
                DatabaseConfig.getConnection();

        //Genero la consulta a el sql
        
        String sql =
                "INSERT INTO productos(id,nombre, precio, stock) VALUES (?, ?, ?, ?)";

        
        //Genero la conexion con el sql y agrego el producto
        
        PreparedStatement statement =
                connection.prepareStatement(sql);

        statement.setInt(1,producto.getId());
        
        statement.setString(2, producto.getNombre());
        statement.setDouble(3, producto.getPrecio());
        statement.setInt(4, producto.getStock());

        statement.executeUpdate();

        System.out.println("Producto guardado");

    } catch (Exception e) {

        System.out.println("Error");
    }
   }
   
public void eliminarProducto(int id) {

    try {

        Connection connection =
                DatabaseConfig.getConnection();

        String sql =
                "DELETE FROM productos WHERE id = ?";

        PreparedStatement statement =
                connection.prepareStatement(sql);

        statement.setInt(1, id);

        statement.executeUpdate();

        System.out.println("Producto eliminado");

    } catch (Exception e) {

        System.out.println("Error al eliminar producto");
        e.printStackTrace();
    }
}
 

   
   
}
