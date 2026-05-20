/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pos.controller;
//Se importa la clase producto del paquete model
import com.pos.model.Producto;
//Se importa el repository
import com.pos.repository.ProductRepository;
//Se importa el servicio donde se encuentran las validaciones de los productos
import com.pos.service.ProductService;

import java.util.ArrayList;
public class ProductController {
    private ProductService service;
    
    private ProductRepository repository;
    //Constructor con su respectivo parametro

    public ProductController(ProductService service, ProductRepository repository) {
        this.service = service;
        this.repository = repository;
    }
    
    //Crear el producto 
    public boolean createProduct(Producto producto){
        //Dato de tipo boolean para validar producto
        boolean valid = service.validateProduct(producto);
        
        //se verifican resultados a traves de un if/else
        
        if(valid){
            //Se llama al metodo a traves del objeto para guardar el producto
            repository.guardarProducto(producto);
            System.out.println("Producto guardado correctamente.");
            return true;
        }
        else
            System.out.println("Producto invalido.");
    return false;
    }
    
    //Metodo para listar productos
    
    public void listProducts(){

    ArrayList<Producto> productos =repository.listarProductos();

    for(Producto producto: productos)
        System.out.println(producto);
}
    
    //Eliminar producto
    public void deleteProduct(int id){
        repository.eliminarProducto(id);
    }

public Producto searchById(int id) {
    return repository.buscarPorId(id);
}

public ArrayList<Producto> getProducts() {
    return repository.listarProductos();
} 
    
    
}
