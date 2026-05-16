package com.pos.app;

import com.pos.model.Producto;
import com.pos.repository.ProductRepository;
import java.util.ArrayList;

public class Mainapp {

    public static void main(String[] args) {

        ProductRepository repository = new ProductRepository();

        System.out.println("Probando conexion con MySQL y productos...");

        Producto producto = new Producto();
        producto.setNombre("Coca Cola");
        producto.setPrecio(3500);
        producto.setStock(10);

        repository.guardarProducto(producto);

        System.out.println("\nProductos guardados en la base de datos:");

        ArrayList<Producto> productos = repository.listarProductos();

        for (Producto item : productos) {
            System.out.println(item);
        }

        System.out.println("\nBuscando producto con id 1:");

        Producto encontrado = repository.buscarPorId(1);

        if (encontrado != null) {
            System.out.println(encontrado);
        } else {
            System.out.println("No se encontro producto con ese id.");
        }

        System.out.println("\nPrueba terminada.");
    }
}
