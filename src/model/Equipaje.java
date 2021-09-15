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
public class Equipaje {
    
    private String idEquipaje;
    private float peso;
    private int idPasajero;

    public Equipaje(String idEquipaje, float peso, int idPasajero) {
        this.idEquipaje = idEquipaje;
        this.peso = peso;
        this.idPasajero = idPasajero;
    }

    public String getIdEquipaje() {
        return idEquipaje;
    }
    public void setIdEquipaje(String idEquipaje) {
        this.idEquipaje = idEquipaje;
    }
    public float getPeso() {
        return peso;
    }
    public void setPeso(float peso) {
        this.peso = peso;
    }
    public int getIdPasajero() {
        return idPasajero;
    }
    public void setIdPasajero(int idPasajero) {
        this.idPasajero = idPasajero;
    }
    
    
    
}
