/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Ivan
 */
public class Programacion {
    
    private int idEmpleado;
    private int idAvion;

    public Programacion(int idEmpleado, int idAvion) {
        this.idEmpleado = idEmpleado;
        this.idAvion = idAvion;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }
    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    public int getIdAvion() {
        return idAvion;
    }
    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }
    
    
    
}
