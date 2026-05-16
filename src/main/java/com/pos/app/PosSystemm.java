/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.pos.app;
import com.pos.model.Producto;
import com.pos.config.DatabaseConfig;

/**
 *
 * @author pc
 */
public class PosSystemm {

    public static void main(String[] args) {
        
        DatabaseConfig.getConnection();
        
        Producto P = new Producto("Samuel" , 200 , 10);
        
        System.out.println(P);
        
        
        
    }
}
