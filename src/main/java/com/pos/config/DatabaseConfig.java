/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pos.config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author pc
 */
public class DatabaseConfig {

    // URL de conexión hacia MySQL
    private static final String URL =
            "jdbc:mysql://localhost:3306/pos_system";

    // Usuario de MySQL
    private static final String USER = "root";

    // Contraseña de MySQL
    private static final String PASSWORD = "TU_PASSWORD";

    // Método para obtener la conexión
    public static Connection getConnection() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            // Intentar conectarse a MySQL
            Connection connection =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Conexion exitosa a MySQL");

            return connection;

        } catch (ClassNotFoundException e) {

            System.out.println("No se encontro el driver de MySQL. Revisa la dependencia en pom.xml");
            throw new RuntimeException(e);

        } catch (SQLException e) {

            System.out.println("Error de conexion a MySQL");
            throw new RuntimeException(e);
        }
    }
}
