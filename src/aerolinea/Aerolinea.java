/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aerolinea;

import access.ReservaDAO;
import access.CiudadDAO;
import com.mysql.cj.util.StringUtils;
import model.Reserva;
import view.MainWindow;

/**
 *
 * @author Ivan
 */
public class Aerolinea {
    
    public static void main(String[] args) {
        
        new MainWindow();
//        System.out.println(CiudadDAO.getName(1));
//        System.out.println(CiudadDAO.getId("Medellin"));
//        
//        ReservaDAO prueba = new ReservaDAO();
//        
//        for(Reserva avion: prueba.getAllReservas()){
//            System.out.println(avion.toString());
//        }

//        String str = "hola";
//        String[] nombre = str.split(" ");
//        String nuevo = "";
//        
//        for(String palabra: nombre){
//            nuevo += palabra.substring(0, 1).toUpperCase() + palabra.substring(1) + " ";
//        }
//        
//        System.out.println(nuevo.trim());
        
    }
    
}
