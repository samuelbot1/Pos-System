/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pos.service;

import com.pos.model.Producto;

public class ProductService {

    //Metodo principal 
    public boolean validateProduct(Producto producto){
        //Validacion de nombre que no este vacio
        if(producto.getNombre() == null  || producto.getNombre().trim().isEmpty()){
            System.out.println("El nombre debe ser obligatorio.");
            return false;
        }
        //Validacion que el nombre no sea demasiado corto
        if(producto.getNombre().length() < 3 ){
            System.out.println("El nombre es demasiado corto.");
            return false;
        }
        //Validacion que el nombre no sea demasiado largo
        if(100<producto.getNombre().length()){
            System.out.println("El nombre es demasiado largo.");
            return false;
        }
        
        //Validacion del precio menor o igual a cero
        
        if(producto.getPrecio()<= 0){
            System.out.println("El precio debe ser mayor a cero.");
            return false;
        }
        
        //Validacion del stock no puede ser un valor negativo
        
        if(producto.getStock()<0){
            System.out.println("El stock no puede ser un valor negativo.");
            return false;
        }
        return true;
    }
    
}
