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
import model.Pasajero;
import model.Reserva;
import utils.ConnectionDB;

/**
 *
 * @author Ivan
 */
public class ReservaDAO {
    
    private Connection conn = null;
    
    public ArrayList<Reserva> getAllReservas() {
        ArrayList<Reserva> reservas = new ArrayList();
        try {
            if(conn == null)
                conn = ConnectionDB.getConnection();
            
            String sql = "SELECT idReserva, clase, idPasajero, concat(pasajero.nombre,' ',pasajero.apellido) as NombreCompleto, " +
                         "idVuelo, getName(idCiudadOrigen) as Origen, getName(idCiudadDestino) as Destino, fecha\n" +
                         "FROM reserva NATURAL JOIN pasajero NATURAL JOIN vuelo\n" +
                         "ORDER BY idReserva;";
            Statement statement = conn.createStatement();
            ResultSet result    = statement.executeQuery(sql);
            
            while (result.next()) {
                Reserva reserva = new Reserva(result.getString(1), result.getString(2),
                                              result.getInt(3), result.getString(4), 
                                              result.getInt(5), result.getString(6),
                                              result.getString(7), result.getDate(8));
                reservas.add(reserva);
            }
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Código Reserva 1: " + ex.getErrorCode() 
                                        + "\nError :" + ex.getMessage());
        }
        return reservas;
    }

    
    public ArrayList<Reserva> getFilteredReservas(int idPasajero, String idReserva) {
        ArrayList<Reserva> reservas = new ArrayList();
        int case_ = -1;
        try {
            if(conn == null)
                conn = ConnectionDB.getConnection();
            
            String sql = "SELECT idReserva, clase, idPasajero, concat(pasajero.nombre,' ',pasajero.apellido) as NombreCompleto, " +
                         "idVuelo, getName(idCiudadOrigen) as Origen, getName(idCiudadDestino) as Destino, fecha\n" +
                         "FROM reserva NATURAL JOIN pasajero NATURAL JOIN vuelo\n" +
                         "WHERE idReserva LIKE ?";
            if(idPasajero != -1){
                sql += " AND  idPasajero LIKE ?;";
                case_ = 1;
            }
            
            //System.out.println(case_);            
            PreparedStatement statement = conn.prepareStatement(sql);
            //System.out.println(sql);            
            statement.setString(1, "%"+idReserva+"%");
            if(case_==1) statement.setString(2, "%"+String.valueOf(idPasajero)+"%");
            
            //System.out.println(statement.toString());
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {
                Reserva reserva = new Reserva(result.getString(1), result.getString(2),
                                              result.getInt(3), result.getString(4), 
                                              result.getInt(5), result.getString(6),
                                              result.getString(7), result.getDate(8));
                reservas.add(reserva);
            }
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Código Reserva 2: " + ex.getErrorCode() 
                                        + "\nError :" + ex.getMessage());
        }
        return reservas;
    }
    
    public void insertReserva(Reserva reserva){
        try {
            if(conn == null)
                conn = ConnectionDB.getConnection();
            
            String sql = "INSERT INTO reserva(idReserva, clase, idPasajero, idVuelo) VALUES(?, ?, ?, ?);";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, reserva.getIdReserva());
            statement.setString(2, reserva.getClase());
            statement.setInt(3, reserva.getIdPasajero());
            statement.setInt(4, reserva.getIdVuelo());
            
            int rowsInserted = statement.executeUpdate();
            if(rowsInserted > 0) 
                JOptionPane.showMessageDialog(null, "La reserva fue agregada exitosamente!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Código Reserva 3: " + ex.getErrorCode() 
                                        + "\nError :" + ex.getMessage());
        }
    }
    
    public void updateReserva(Reserva reserva){
        try{
            
            if(conn == null) conn = ConnectionDB.getConnection();
            
            String sql = "UPDATE reserva SET clase = ?, idPasajero = ?, idVuelo = ? where idReserva = ?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setString(1, reserva.getClase());
            statement.setInt(2, reserva.getIdPasajero());
            statement.setInt(3, reserva.getIdVuelo());
            statement.setString(4, reserva.getIdReserva());
            
            int rowsInserted = statement.executeUpdate();
            if(rowsInserted > 0) 
                JOptionPane.showMessageDialog(null, "La actualización fue exitosa");
                        
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Código Reserva 4: " + ex.getErrorCode() 
                                        + "\nError :" + ex.getMessage());
        }
    }
    
    public int deleteReserva(String id){
        
        try{
            
            if(conn == null) conn = ConnectionDB.getConnection();
            
            String sql = "DELETE FROM reserva WHERE idReserva=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setString(1, id);
            
            Reserva aEliminar = getFilteredReservas(-1, id).get(0);
            
            int confirmacion = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar la reserva de:\n"+
                                        aEliminar.getIdPasajero()+" - "+aEliminar.getNombreCompleto()+
                                        " con Vuelo: "+aEliminar.getOrigen()+"-"+aEliminar.getDestino());
            System.out.println("Opcion: "+confirmacion);
            if(confirmacion==0){
                int rowsInserted = statement.executeUpdate();
                if(rowsInserted>0){
                    JOptionPane.showMessageDialog(null, "La reserva fue eliminada exitosamente");
                    return 1;
                }else{
                    System.err.println("Hubo un error eliminando la reserva");
                }
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Código Reserva 5: " + ex.getErrorCode() 
                                        + "\nError :" + ex.getMessage());
        }
        
        return 0;
    }
    
    public String getIdPasajero(String idReserva){
        try{
            
            if(conn==null) conn = ConnectionDB.getConnection();
            
            String sql = "SELECT idPasajero FROM reserva WHERE idReserva=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setString(1, idReserva);
            
            ResultSet result = statement.executeQuery();
            
            while(result.next())
                return result.getString(1);
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Código Reserva 6: " + ex.getErrorCode() 
                                        + "\nError :" + ex.getMessage());
        }
        
        return "";
    }

    
}
