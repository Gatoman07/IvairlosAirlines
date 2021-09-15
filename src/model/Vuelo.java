/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Ivan
 */
public class Vuelo {
    
    private int idVuelo;
    private int idAvion;
    private int idCiudadOrigen;
    private int idCiudadDestino;
    private Date fecha;
    private long precio;

    public Vuelo(int idAvion, int idCiudadOrigen, int idCiudadDestino, Date fecha, long precio) {
        this.idAvion = idAvion;
        this.idCiudadOrigen = idCiudadOrigen;
        this.idCiudadDestino = idCiudadDestino;
        this.fecha = fecha;
        this.precio = precio;
    }

    public Vuelo(int idVuelo, int idAvion, int idCiudadOrigen, int idCiudadDestino, Date fecha, long precio) {
        this.idVuelo = idVuelo;
        this.idAvion = idAvion;
        this.idCiudadOrigen = idCiudadOrigen;
        this.idCiudadDestino = idCiudadDestino;
        this.fecha = fecha;
        this.precio = precio;
    }

    public int getIdVuelo() {
        return idVuelo;
    }
    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }
    public int getIdAvion() {
        return idAvion;
    }
    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }
    public int getIdCiudadOrigen() {
        return idCiudadOrigen;
    }
    public void setIdCiudadOrigen(int idCiudadOrigen) {
        this.idCiudadOrigen = idCiudadOrigen;
    }
    public int getIdCiudadDestino() {
        return idCiudadDestino;
    }
    public void setIdCiudadDestino(int idCiudadDestino) {
        this.idCiudadDestino = idCiudadDestino;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public long getPrecio() {
        return precio;
    }
    public void setPrecio(long precio) {
        this.precio = precio;
    }
    
    
}
