/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import access.AvionDAO;
import access.CiudadDAO;
import access.EmpleadoDAO;
import access.PasajeroDAO;
import access.ReservaDAO;
import access.VueloDAO;
import java.sql.Date;
import java.util.ArrayList;
import model.Avion;
import model.Ciudad;
import model.Empleado;
import model.Pasajero;
import model.Reserva;
import model.Vuelo;

/**
 *
 * @author Ivan
 */
public class InitialData {
    
    private ArrayList<Reserva> reservas = null;
    private ReservaDAO reservaDAO = null;
    private ArrayList<Ciudad> ciudadesOrigen = null;
    private ArrayList<Ciudad> ciudadesDestino = null;
    private CiudadDAO ciudadDAO = null;
    private VueloDAO vueloDAO = null;
    private PasajeroDAO pasajeroDAO = null;
    private EmpleadoDAO empleadoDAO = null;
    private ArrayList<Avion> aviones = null;
    private AvionDAO avionDAO = null;
    
    public InitialData() {
        
        reservaDAO = new ReservaDAO();
        this.reservas = reservaDAO.getAllReservas();
        
        ciudadDAO = new CiudadDAO();
        this.ciudadesOrigen = ciudadDAO.getAllCiudades(0);
        this.ciudadesOrigen.add(0, new Ciudad(-1, "Todas las ciudades"));
        
        ciudadDAO = new CiudadDAO();
        this.ciudadesDestino = ciudadDAO.getAllCiudades(1);
        this.ciudadesDestino.add(0, new Ciudad(-1, "Todas las ciudades"));
        
        vueloDAO = new VueloDAO();
        
        pasajeroDAO = new PasajeroDAO();
        
        empleadoDAO = new EmpleadoDAO();
        
    }
    
    public int deleteReserva(String id){
        return reservaDAO.deleteReserva(id);
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }
    
    public ArrayList<Reserva> getFilteredReservas(String idPasajero, String idReserva){
        
        int pasajero = -1;
        
        try{
            pasajero = Integer.parseInt(idPasajero);
        }catch(Exception e){
            
        }
        
        this.reservas = reservaDAO.getFilteredReservas(pasajero, idReserva);
        
        return reservas;
    }
    
    public ArrayList<Ciudad> getCiudadesOrigen(){
        return ciudadesOrigen;
    }
    
    public ArrayList<Ciudad> getCiudadesOrigen(String ciudadDestino) {
        System.out.println("Llamando DAO con destino: "+ciudadDestino);
        ArrayList<Ciudad> validas = ciudadDAO.getFilteredCiudades(1, ciudadDestino);
        validas.add(0, new Ciudad(-1, "Todas las ciudades"));
        
        return validas;
    }

    public ArrayList<Ciudad> getCiudadesDestino() {
        return ciudadesDestino;
    }
    
    public ArrayList<Ciudad> getCiudadesDestino(String ciudadOrigen) {
        System.out.println("Llamando DAO con origen: "+ciudadOrigen);
        ArrayList<Ciudad> validas = ciudadDAO.getFilteredCiudades(0, ciudadOrigen);
        validas.add(0, new Ciudad(-1, "Todas las ciudades"));
        
        return validas;
    }
    
    public ArrayList<String[]> getFechas(String ciudadOrigen, String ciudadDestino){
        
        ArrayList<String[]> fechas = vueloDAO.getFechasVuelo(ciudadOrigen, ciudadDestino);
        fechas.add(0,new String[] {"Fecha", "Costo"});
        
        return fechas;
    }
    
    public boolean isPasajero(String id){
        
        Pasajero pasajero = pasajeroDAO.getPasajero(Integer.parseInt(id));
        
        return pasajero!=null;
    }
    
    public void crearReserva(String idReserva, String clase, String idPasajero, String idVuelo){
        
        Reserva reserva = new Reserva(idReserva,clase, Integer.parseInt(idPasajero),Integer.parseInt(idVuelo));
        
        reservaDAO.insertReserva(reserva);
        
    }
    
    public void actualizarReserva(String idReserva, String clase, String idPasajero, String idVuelo){
        
        Reserva reserva = new Reserva(idReserva,clase, Integer.parseInt(idPasajero),Integer.parseInt(idVuelo));
        
        reservaDAO.updateReserva(reserva);
    }
    
    public void crearPasajero(String idPasajero, String nombre, String apellido, String edad, String genero){
        
        Pasajero pasajero = new Pasajero(Integer.parseInt(idPasajero), nombre, apellido, Integer.parseInt(edad), String.valueOf(genero.charAt(0)));
        
        pasajeroDAO.insertPasajero(pasajero);
        
    }
    
    public String getIdPasajero(String idReserva){
        
        return reservaDAO.getIdPasajero(idReserva);
    }
    
    public boolean correctLogin(String user, String pass){
        
        Empleado empleado = null;
        
        try{
            empleado = empleadoDAO.getEmpleado(Integer.parseInt(user));
            if(user.equals(String.valueOf(empleado.getIdEmpleado())) && pass.equals(empleado.getPass())) return true;
        }catch(Exception e){
            System.out.println(e.toString());
            return false;
        }
        
        return false;
    }
    
    public ArrayList<Avion> getAviones(){
        avionDAO = new AvionDAO();
        
        ArrayList<Avion> aviones = avionDAO.getAllAviones();
        aviones.add(0, new Avion(-1, "Aviones", -1));
        
        return aviones;
        
    }
    
    public void crearVuelo(String idAvion, String origen, String destino, String fecha, String precio){
        
        Vuelo vuelo = null;
        
        int idOrigen = CiudadDAO.getId(origen);
        int idDestino = CiudadDAO.getId(destino);
        
        if(idOrigen==-1){
            ciudadDAO.insertCiudad(new Ciudad(origen));
            idOrigen = CiudadDAO.getId(origen);
        }
        if(idDestino==-1){
            ciudadDAO.insertCiudad(new Ciudad(destino));
            idDestino = CiudadDAO.getId(destino);
        }
            
        int anio = Integer.parseInt(fecha.split("-")[0])-1900;
        int mes = Integer.parseInt(fecha.split("-")[1])-1;
        int dia = Integer.parseInt(fecha.split("-")[2]);
        
        System.out.println("La fecha es: "+ new Date(anio, mes, dia));
        
        vuelo = new Vuelo(Integer.parseInt(idAvion), idOrigen, idDestino, new Date(anio, mes, dia), Long.parseLong(precio));
        
        //(Integer.parseInt(idAvion), CiudadDAO.getId(origen), CiudadDAO.getId(destino))
        
        vueloDAO.insertVuelo(vuelo);
        
    }
    
    public boolean isFree(int idAvion, String fecha){
        
        ArrayList<Integer> ocupados = null;
        ocupados = vueloDAO.getAvionesOcupados(fecha);
        
        return !ocupados.contains(idAvion);
    }
    
    
}
