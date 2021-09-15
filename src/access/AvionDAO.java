/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Avion;
import utils.ConnectionDB;

/**
 *
 * @author Ivan
 */
public class AvionDAO {
    
    private Connection conn = null;
    
    public ArrayList<Avion> getAllAviones(){
        ArrayList<Avion> aviones = new ArrayList<Avion>();
        try{
            if(conn == null) conn = ConnectionDB.getConnection();
            
            String sql = "SELECT * FROM avion";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            while(result.next()){
                Avion avion = new Avion(result.getInt(1), result.getString(2), result.getInt(3));
                aviones.add(avion);
            }
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Código Avion 1: " + ex.getErrorCode() 
                                        + "\nError :" + ex.getMessage());
        }
        
        return aviones;
    }

    
    public ArrayList<Avion> getFilteredAviones(String marca, int capacidad){
        ArrayList<Avion> aviones = new ArrayList();
        int opc = -1;
        
        try{
            
            if(conn == null) conn = ConnectionDB.getConnection();
            
            String sql = "SELECT * FROM avion" +
                         "WHERE avion.marca LIKE ?";
            
            if(capacidad != -1){
                if(capacidad>0){
                    sql += " AND avion.capacidad>=?";
                    opc = 1;
                }
            }
            System.out.println(opc);            
            PreparedStatement statement = conn.prepareStatement(sql);
            System.out.println(sql);            
            statement.setString(1, "%"+marca+"%");
            if(opc==1) statement.setInt(2, capacidad);
            
            System.out.println(statement.toString());
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {
                Avion avion = new Avion(result.getInt(1), result.getString(2), result.getInt(3));
                aviones.add(avion);
            }
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Código Avion 2: " + ex.getErrorCode() 
                                        + "\nError :" + ex.getMessage());
        }
        
        return aviones;
    }
    
    public void insertAvion(Avion avion){
        try{
            
            if(conn == null) conn = ConnectionDB.getConnection();
            
            String sql = "INSERT INTO avion(marca, capacidad) VALUES (?,?);";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, avion.getMarca());
            statement.setInt(2, avion.getCapacidad());
            
            int rowsInserted = statement.executeUpdate();
            if(rowsInserted > 0) 
                JOptionPane.showMessageDialog(null, "El registro fue agregado exitosamente");
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Código Avion 3: " + ex.getErrorCode() 
                                        + "\nError :" + ex.getMessage());
        }
    }
    
}
