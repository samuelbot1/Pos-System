/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pos.repository;

import com.pos.config.DatabaseConfig;
import com.pos.model.Producto;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



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
                "INSERT INTO productos(,nombre, precio, stock) VALUES (?, ?, ?, ?)";

        
        //Genero la conexion con el sql y agrego el producto
        
        PreparedStatement statement =
                connection.prepareStatement(sql);

       
        
        statement.setString(1, producto.getNombre());
        statement.setDouble(2, producto.getPrecio());
        statement.setInt(3, producto.getStock());

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
 
public ArrayList<Producto> listarProductos(){
    
    ArrayList<Producto> listaProductos = new ArrayList<>();

try{
    
    
    Connection connection = 
            DatabaseConfig.getConnection();
    
    String sql =

               "SELECT * FROM productos";
    
    PreparedStatement statement =
                connection.prepareStatement(sql);
    
    ResultSet rs = statement.executeQuery();
    
    
    while(rs.next()){
        
        Producto producto = new Producto();
        
        producto.setId(rs.getInt("id"));
        producto.setNombre(rs.getString("nombre"));
        producto.setPrecio(rs.getDouble("precio"));
        producto.setStock(rs.getInt("stock"));
        
        listaProductos.add(producto);
        
    }
    
    
}catch (Exception e){

    System.out.println("Error al listar productos");
    e.printStackTrace();
}
return listaProductos;
} 
   
   
public Producto buscarPorId(int id) {

    Producto producto = null;

    try {

        Connection connection =
                DatabaseConfig.getConnection();

        String sql =
                "SELECT * FROM productos WHERE id = ?";

        PreparedStatement statement =
                connection.prepareStatement(sql);

        statement.setInt(1, id);

        ResultSet rs =
                statement.executeQuery();

        if (rs.next()) {

            producto = new Producto();

            producto.setId(rs.getInt("id"));
            producto.setNombre(rs.getString("nombre"));
            producto.setPrecio(rs.getDouble("precio"));
            producto.setStock(rs.getInt("stock"));
        }

    } catch (Exception e) {

        System.out.println("Error al buscar producto por id");
        e.printStackTrace();
    }

    return producto;
}



}
