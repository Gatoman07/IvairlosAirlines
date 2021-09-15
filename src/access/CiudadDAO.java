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
import model.Ciudad;
import utils.ConnectionDB;

/**
 *
 * @author Ivan
 */
public class CiudadDAO {
    
    private Connection conn = null;
    
    /**
     * 
     * @param lugar
     * @return 
     */
    public ArrayList<Ciudad> getAllCiudades(int lugar) {
        ArrayList<Ciudad> ciudades = new ArrayList();
        try {
            if(conn == null)
                conn = ConnectionDB.getConnection();
            
            String sql = "SELECT idCiudad, nombre FROM ciudad join vuelo on idCiudad=idCiudadOrigen group by idCiudad order by nombre;";
            
            if(lugar == 1) sql="SELECT idCiudad, nombre FROM ciudad join vuelo on idCiudad=idCiudadDestino group by idCiudad order by nombre;";
            
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            while (result.next()) {
                Ciudad ciudad = new Ciudad(result.getInt(1), result.getString(2));
                ciudades.add(ciudad);
            }
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Código Ciudad 1: " + ex.getErrorCode() 
                                        + "\nError :" + ex.getMessage());
        }
        return ciudades;
    }
    
    public ArrayList<Ciudad> getFilteredCiudades(int lugar, String nombreCiudad){
        ArrayList<Ciudad> ciudades = new ArrayList();
        try {
            if(conn == null)
                conn = ConnectionDB.getConnection();
            
            String sql = "SELECT idCiudadDestino, getName(idCiudadDestino) as nombre "
                    + "from ciudad join vuelo on idCiudad = idCiudadOrigen "
                    + "where getName(idCiudadOrigen)=? "
                    + "group by idCiudadDestino order by nombre;";
            
            if(lugar == 1) sql="SELECT idCiudadOrigen, getName(idCiudadOrigen) as nombre "
                    + "from ciudad join vuelo on idCiudad = idCiudadDestino "
                    + "where getName(idCiudadDestino)=? "
                    + "group by idCiudadOrigen order by nombre;";
            
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setString(1, nombreCiudad);
            
            ResultSet result = statement.executeQuery();
            
            System.out.println("Filtrando por: "+lugar+" "+nombreCiudad);
            
            while (result.next()) {
                Ciudad ciudad = new Ciudad(result.getInt(1), result.getString(2));
                ciudades.add(ciudad);
            }
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Código Ciudad 2: " + ex.getErrorCode() 
                                        + "\nError :" + ex.getMessage());
        }
        return ciudades;
    }
    
    public void insertCiudad(Ciudad ciudad){
        try {
            if(conn == null)
                conn = ConnectionDB.getConnection();
            
            String sql = "INSERT INTO ciudad (nombre) VALUES(?);";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, ciudad.getNombre());
            
            int rowsInserted = statement.executeUpdate();
            if(rowsInserted > 0) 
                System.out.println("La ciudad "+ciudad.getNombre()+" se registro exitosamente");
                //JOptionPane.showMessageDialog(null, "El registro fue agregado exitosamente !");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Código Ciudad 3: " + ex.getErrorCode() 
                                        + "\nError :" + ex.getMessage());
        }
    }
    
    public static String getName(int id){
        
        String nombre = "";
        Connection connt = null;
        
        try {
            if(connt == null)
                connt = ConnectionDB.getConnection();
            
            String sql = "SELECT nombre FROM ciudad WHERE idCiudad=?;";
            
            PreparedStatement statement = connt.prepareStatement(sql);        
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            
            if(result.next()) nombre = result.getString(1);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Código Ciudad 4: " + ex.getErrorCode() 
                                        + "\nError :" + ex.getMessage());
        }
        
        return nombre;
    }
    
    public static int getId(String nombre){
        
        int codigo = -1;
        Connection connt = null;
        
        try {
            if(connt == null)
                connt = ConnectionDB.getConnection();
            
            String sql = "SELECT idCiudad FROM ciudad WHERE nombre=?;";
            
            PreparedStatement statement = connt.prepareStatement(sql);        
            statement.setString(1, nombre);
            ResultSet result = statement.executeQuery();
            
            if(result.next()) codigo = result.getInt(1);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Código Ciudad 5: " + ex.getErrorCode() 
                                        + "\nError :" + ex.getMessage());
        }
        
        return codigo;
    }
    
}
