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
public class Reserva {
    
    private String idReserva;
    private String clase;
    private int idPasajero;
    private String nombreCompleto;
    private int idVuelo;
    private String origen;
    private String destino;
    private Date fecha;

    public Reserva(String idReserva, String clase, int idPasajero, String nombreCompleto, int idVuelo, String origen, String destino, Date fecha) {
        this.idReserva = idReserva;
        this.clase = clase;
        this.idPasajero = idPasajero;
        this.nombreCompleto = nombreCompleto;
        this.idVuelo = idVuelo;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
    }

    public Reserva(String idReserva, String clase, int idPasajero, int idVuelo) {
        this.idReserva = idReserva;
        this.clase = clase;
        this.idPasajero = idPasajero;
        this.idVuelo = idVuelo;
    }

    public String getIdReserva() {
        return idReserva;
    }
    public void setIdReserva(String idReserva) {
        this.idReserva = idReserva;
    }
    public String getClase() {
        return clase;
    }
    public void setClase(String clase) {
        this.clase = clase;
    }
    public int getIdPasajero() {
        return idPasajero;
    }
    public void setIdPasajero(int idPasajero) {
        this.idPasajero = idPasajero;
    }
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    public int getIdVuelo() {
        return idVuelo;
    }
    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }
    public String getOrigen() {
        return origen;
    }
    public void setOrigen(String origen) {
        this.origen = origen;
    }
    public String getDestino() {
        return destino;
    }
    public void setDestino(String destino) {
        this.destino = destino;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return idReserva+" "+clase+" "+idPasajero+" "+nombreCompleto+" "+idVuelo+" "+origen+" "+destino+" "+fecha.toString();
    }
    
    public Object[] toArray(){
        Object[] data = {idReserva, clase, idPasajero, nombreCompleto, idVuelo, origen, destino, fecha};
        return data;
    }
    
}
