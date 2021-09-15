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
public class Avion {
    
    private int idAvion;
    private String marca;
    private int capacidad;

    public Avion(int idAvion, String marca, int capacidad) {
        this.idAvion = idAvion;
        this.marca = marca;
        this.capacidad = capacidad;
    }

    public int getIdAvion() {
        return idAvion;
    }
    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public int getCapacidad() {
        return capacidad;
    }
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        
        if(idAvion==-1) return marca;
        
        return idAvion+" - "+marca+" - "+capacidad;
    }
    
    
    
    
    
}
