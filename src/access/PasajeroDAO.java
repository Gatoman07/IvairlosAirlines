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
import javax.swing.JOptionPane;
import model.Pasajero;
import utils.ConnectionDB;

/**
 *
 * @author Ivan
 */
public class PasajeroDAO {
    
    private Connection conn = null;
    
    public Pasajero getPasajero(int id){
        
        Pasajero pasajero = null;
        
        try{
            
            if(conn==null) conn = ConnectionDB.getConnection();
            
            String sql = "SELECT * FROM pasajero WHERE idPasajero=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            
            ResultSet result = statement.executeQuery();
            
            while(result.next())
                pasajero = new Pasajero(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4), result.getString(5));
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Código Pasajero 1: " + ex.getErrorCode() 
                                        + "\nError :" + ex.getMessage());
        }
        
        return pasajero;
    }
    
    public void insertPasajero(Pasajero pasajero){
        try {
            if(conn == null)
                conn = ConnectionDB.getConnection();
            
            String sql = "INSERT INTO pasajero(idPasajero, nombre, apellido, edad, genero) VALUES(?, ?, ?, ?, ?);";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, pasajero.getIdPasajero());
            statement.setString(2, pasajero.getNombre());
            statement.setString(3, pasajero.getApellido());
            statement.setInt(4, pasajero.getEdad());
            statement.setString(5, pasajero.getGenero());
            
            int rowsInserted = statement.executeUpdate();
            if(rowsInserted > 0) 
                System.out.println("El pasajero se creo todo bien");
                //JOptionPane.showMessageDialog(null, "El registro fue agregado exitosamente !");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Código Pasajero 2: " + ex.getErrorCode() 
                                        + "\nError :" + ex.getMessage());
        }
    }
    
}
