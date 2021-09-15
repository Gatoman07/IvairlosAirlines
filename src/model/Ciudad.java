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
public class Ciudad {
    
    private int idCiudad;
    private String nombre;

    public Ciudad(String nombre) {
        this.nombre = nombre;
    }

    public Ciudad(int idCiudad, String nombre) {
        this.idCiudad = idCiudad;
        this.nombre = nombre;
    }

    public int getIdCiudad() {
        return idCiudad;
    }
    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
    
    public Object toArray(){
        Object[] arr = {this.nombre};
        return arr;
    }
    
    
}
