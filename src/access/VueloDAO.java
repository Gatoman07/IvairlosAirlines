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
import model.Vuelo;
import utils.ConnectionDB;

/**
 *
 * @author Ivan
 */
public class VueloDAO {
    
    private Connection conn = null;
    
    public Vuelo getVuelo(int id){
        Vuelo pasajero = null;
        
        try{
            
            if(conn==null) conn = ConnectionDB.getConnection();
            
            String sql = "SELECT * FROM vuelo WHERE idVuelo=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            
            ResultSet result = statement.executeQuery();
            
            pasajero = new Vuelo(result.getInt(1), result.getInt(2), result.getInt(3), result.getInt(4), result.getDate(5), result.getLong(6));
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Código Vuelo 1: " + ex.getErrorCode() 
                                        + "\nError :" + ex.getMessage());
        }
        
        return pasajero;
    }
    
    public ArrayList<String[]> getFechasVuelo(String origen, String destino){
        
        ArrayList<String[]> fechas = new ArrayList<String[]>();
        
        try {
            if(conn == null)
                conn = ConnectionDB.getConnection();
            
            
            
            String sql = "SELECT fecha,precio,idVuelo FROM vuelo WHERE getName(idCiudadOrigen)=? AND getName(idCiudadDestino)=? group by fecha order by precio;";
            
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setString(1, origen);
            statement.setString(2, destino);
            
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {
                fechas.add(new String[] {result.getString(1), result.getString(2), result.getString(3)});
            }
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Código Vuelo 2: " + ex.getErrorCode() 
                                        + "\nError :" + ex.getMessage());
        }
        
        return fechas;
    }
    
    public void insertVuelo(Vuelo vuelo){
        try {
            if(conn == null)
                conn = ConnectionDB.getConnection();
            
            String sql = "INSERT INTO vuelo(idAvion, idCiudadOrigen, idCiudadDestino, fecha, precio) VALUES(?, ?, ?, ?, ?);";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, vuelo.getIdAvion());
            statement.setInt(2, vuelo.getIdCiudadOrigen());
            statement.setInt(3, vuelo.getIdCiudadDestino());
            statement.setDate(4, vuelo.getFecha());
            statement.setLong(5, vuelo.getPrecio());
            
            int rowsInserted = statement.executeUpdate();
            if(rowsInserted > 0) 
                JOptionPane.showMessageDialog(null, "El registro fue agregado exitosamente !");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Código Vuelo 3: " + ex.getErrorCode() 
                                        + "\nError :" + ex.getMessage());
        }
    }
    
    public ArrayList<Integer> getAvionesOcupados(String fecha){
        
        ArrayList<Integer> aviones = new ArrayList<Integer>();
        
        try {
            if(conn == null)
                conn = ConnectionDB.getConnection();
            
            
            
            String sql = "SELECT idAvion FROM vuelo WHERE fecha=?;";
            
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setString(1, fecha);
            
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {
                aviones.add(result.getInt(1));
            }
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Código Vuelo 4: " + ex.getErrorCode() 
                                        + "\nError :" + ex.getMessage());
        }
        
        return aviones;
        
    }
    
}
