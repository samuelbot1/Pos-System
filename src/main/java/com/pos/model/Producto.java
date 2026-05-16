/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pos.model;

/**
 *
 * @author samuel Polo
 */
public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int stock;

    public Producto (){}

    public Producto (int id,String nombre, double precio , int stock){
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
}
    
/**  Getter y Setter de las clases producto **/

    public int getId(){
        return id;
    }
    
    public void setId(){
        this.id = id;
    }
    
    public String getNombre() {

        return nombre;  
}

    public void setNombre (String nombre) {

        this.nombre = nombre;

}

    public double getPrecio () {
        return precio;
}

    public void setPrecio (double precio){
        this.precio = precio;
}

    public int getStock () {
        return stock;
}

    public void setStock (int stock){
        this.stock = stock;
}

    @Override
    public String toString() {

        return """
           =========================
                  PRODUCTO
           =========================
           Nombre : %s
           Precio : %.2f
           Stock  : %d
           =========================
           """.formatted(nombre, precio, stock);
}







}


