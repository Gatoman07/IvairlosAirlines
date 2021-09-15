/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.ReservaPanel;

/**
 *
 * @author Ivan
 */
public class ChangeEvent implements ActionListener{
    
    ReservaPanel reservaPanel;
    
    public ChangeEvent(ReservaPanel reservaPanel){
        this.reservaPanel = reservaPanel;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        
        //Si selecciono Origen y Destino es todas las ciudades que se cambie y Origen no es Todas las ciudades
        
        // && this.reservaPanel.getCiudadDestinoCbx().getSelectedIndex()==0
        if(actionEvent.getSource() == this.reservaPanel.getCiudadOrigenCbx() && this.reservaPanel.getCiudadDestinoCbx().getSelectedIndex()==0 && this.reservaPanel.getCiudadOrigenCbx().getSelectedIndex()!=0){
            this.reservaPanel.cambiarOpcionesDestino();
        }else if(actionEvent.getSource() == this.reservaPanel.getCiudadDestinoCbx() && this.reservaPanel.getCiudadOrigenCbx().getSelectedIndex()==0 && this.reservaPanel.getCiudadDestinoCbx().getSelectedIndex()!=0){
            this.reservaPanel.cambiarOpcionesOrigen();
        }else if(actionEvent.getSource() == this.reservaPanel.getCiudadDestinoCbx() && this.reservaPanel.getCiudadDestinoCbx().getSelectedIndex()==0){
            this.reservaPanel.opcionesDestinoDefecto();
            this.reservaPanel.opcionesOrigenDefecto();
        }else if(actionEvent.getSource() == this.reservaPanel.getCiudadOrigenCbx() && this.reservaPanel.getCiudadOrigenCbx().getSelectedIndex()==0){
            this.reservaPanel.opcionesDestinoDefecto();
            this.reservaPanel.opcionesOrigenDefecto();
        }else if(actionEvent.getSource() == this.reservaPanel.getCiudadOrigenCbx()){
            
            //Mirar si el destino es posible, si lo es no lo cambio. Pero si no es posible, seteo los destinos con los posibles
            if(!this.reservaPanel.confirmarDestino()) this.reservaPanel.cambiarOpcionesDestino();
            
        }else if(actionEvent.getSource() == this.reservaPanel.getCiudadDestinoCbx()){
            
            //Mirar si el destino es posible, si lo es no lo cambio. Pero si no es posible, seteo los destinos con los posibles
            if(!this.reservaPanel.confirmarOrigen()) this.reservaPanel.cambiarOpcionesOrigen();
            
        }
        
        
        if(actionEvent.getSource() != this.reservaPanel.getClaseCbx() && actionEvent.getSource() != this.reservaPanel.getFechaCbx() && this.reservaPanel.getCiudadOrigenCbx().getSelectedIndex()!=0 && this.reservaPanel.getCiudadDestinoCbx().getSelectedIndex()!=0){
            this.reservaPanel.setFechas();
        }
        
        if(actionEvent.getSource() == this.reservaPanel.getFechaCbx() && this.reservaPanel.getFechaCbx().getSelectedIndex()!=0){
            this.reservaPanel.setCosto();
        }
        
        if(actionEvent.getSource() == this.reservaPanel.getClaseCbx() && this.reservaPanel.getFechaCbx().getSelectedIndex()!=0){
            this.reservaPanel.setCosto();
        }
        
    }
    
}
