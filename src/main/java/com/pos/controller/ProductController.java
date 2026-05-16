/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pos.controller;
//Se importa la clase producto del paquete model
import com.pos.model.Producto;
//Se importa el servicio donde se encuentran las validaciones de los productos
import com.pos.service.ProductService;

public class ProductController {
    private ProductService service;

    //Constructor con su respectivo parametro
    public ProductController(ProductService service) {
        this.service = service;
    }
    
    public void createProduct(Producto producto){
        //Dato de tipo boolean para validar producto
        boolean valid = service.validateProduct(producto);
        
        //se verifican resultados a traves de un if/else
        
        if(valid)
            System.out.println("Producto valido");
        
        else
            System.out.println("Producto invalido");
    }  
}
