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
import model.Empleado;
import utils.ConnectionDB;

/**
 *
 * @author Ivan
 */
public class EmpleadoDAO {
    
    private Connection conn = null;
    
    public EmpleadoDAO(){
        
    }
    
    public Empleado getEmpleado(int idEmpleado){
        try{
            
            if(conn == null) conn = ConnectionDB.getConnection();
            
            String sql = "SELECT idEmpleado, password FROM empleado WHERE idEmpleado=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setInt(1, idEmpleado);
            System.out.println(statement);
            
            ResultSet result = statement.executeQuery();
            
            while(result.next()){
                System.out.println("Contraseña de la DB: "+result.getString(2));
                return new Empleado(result.getInt(1),"","",0,"","",result.getString(2));
            }
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Código Empleado 1: " + ex.getErrorCode() 
                                        + "\nError :" + ex.getMessage());
        }
        
        return null;
    }
    
}
