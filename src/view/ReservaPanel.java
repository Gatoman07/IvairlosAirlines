/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ChangeEvent;
import controller.InitialData;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.Ciudad;

/**
 *
 * @author Ivan
 */
public class ReservaPanel extends javax.swing.JPanel {

    private String textoBtn;
    private String infoUpdate;
    private InitialData initialData;
    private ArrayList<String[]> fechas;
    private MainWindow principal;
    private static Object[] informacion;
    
    /**
     * Creates new form ReservaPanel
     */
    public ReservaPanel(MainWindow principal, String infoUpdate) {
        textoBtn = "";
        
        this.principal = principal;
        this.infoUpdate = infoUpdate;
        
        initComponents();
        
        initialData = new InitialData();
        
        
        if(!infoUpdate.equals(""))
            this.idPasajeroTF.setText(initialData.getIdPasajero(infoUpdate));
        
        opcionesOrigenDefecto();
        opcionesDestinoDefecto();
        
        this.fechaCbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Fecha"}));
        
        this.claseCbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Economica","Ejecutiva","Primera"}));
        
        
        ChangeEvent algoCambio = new ChangeEvent(this);
        
        this.ciudadOrigenCbx.addActionListener(algoCambio);
        this.ciudadDestinoCbx.addActionListener(algoCambio);
        this.fechaCbx.addActionListener(algoCambio);
        this.claseCbx.addActionListener(algoCambio);
        
        
    }

    public String getTextoBtn() {
        return textoBtn;
    }

    public void setTextoBtn(String textoBtn) {
        this.textoBtn = textoBtn;
        this.reservaBtn.setText(textoBtn);
    }

    public JComboBox<String> getCiudadDestinoCbx() {
        return ciudadDestinoCbx;
    }

    public JComboBox<String> getCiudadOrigenCbx() {
        return ciudadOrigenCbx;
    }

    public JComboBox<String> getFechaCbx() {
        return fechaCbx;
    }

    public void setFechaCbx(JComboBox<String> fechaCbx) {
        this.fechaCbx = fechaCbx;
    }

    public JComboBox<String> getClaseCbx() {
        return claseCbx;
    }
    
    
    public void cambiarOpcionesDestino(){
        
        String opcionOrigen = this.getCiudadOrigenCbx().getSelectedItem().toString();
        
        ArrayList<Ciudad> ciudades = null;
        
        if(opcionOrigen.equals("Todas las ciudades")){
            ciudades = new InitialData().getCiudadesDestino();
            opcionesOrigenDefecto();
        }else ciudades = new InitialData().getCiudadesDestino(opcionOrigen);
        
        String[] arr = new String[ciudades.size()];
        
        for(int i=0; i<ciudades.size(); i++)
            arr[i] = ciudades.get(i).toString();
        
        this.ciudadDestinoCbx.setModel(new DefaultComboBoxModel<>(arr));
        
    }
    
    public void cambiarOpcionesOrigen(){
        
        String opcionDestino = this.getCiudadDestinoCbx().getSelectedItem().toString();
        
        ArrayList<Ciudad> ciudades = null;
        
        if(opcionDestino.equals("Todas las ciudades")){
            ciudades = new InitialData().getCiudadesOrigen();
            opcionesDestinoDefecto();
        }else ciudades = new InitialData().getCiudadesOrigen(opcionDestino);
        
        String[] arr = new String[ciudades.size()];
        
        for(int i=0; i<ciudades.size(); i++)
            arr[i] = ciudades.get(i).toString();
        
        this.ciudadOrigenCbx.setModel(new DefaultComboBoxModel<>(arr));
        
    }
    
    public void opcionesDestinoDefecto(){
        ArrayList<Ciudad> ciudades = initialData.getCiudadesDestino();
        String[] arr = new String[ciudades.size()];
        
        for(int i=0; i<ciudades.size(); i++)
            arr[i] = ciudades.get(i).toString();
        
        this.ciudadDestinoCbx.setModel(new DefaultComboBoxModel<>(arr));
    }
    
    public void opcionesOrigenDefecto(){
        ArrayList<Ciudad> ciudades = initialData.getCiudadesOrigen();
        String[] arr = new String[ciudades.size()];
        
        for(int i=0; i<ciudades.size(); i++)
            arr[i] = ciudades.get(i).toString();
        
        this.ciudadOrigenCbx.setModel(new DefaultComboBoxModel<>(arr));
    }
    
    //Mirar si el destino es posible, si lo es no lo cambio. Pero si no es posible, seteo los destinos con los posibles
    public boolean confirmarDestino(){
        
        ArrayList<Ciudad> ciudades = initialData.getCiudadesDestino(this.ciudadOrigenCbx.getSelectedItem().toString());
        
        for(Ciudad ciudad: ciudades){
            if(ciudad.getNombre().equals(this.ciudadDestinoCbx.getSelectedItem().toString())) return true;
        }
        
        return false;
    }
    
    public boolean confirmarOrigen(){
        
        ArrayList<Ciudad> ciudades = initialData.getCiudadesOrigen(this.ciudadDestinoCbx.getSelectedItem().toString());
        
        for(Ciudad ciudad: ciudades){
            System.out.println("Revisando: "+ciudad.getNombre());
            if(ciudad.getNombre().equals(this.ciudadOrigenCbx.getSelectedItem().toString())) return true;
        }
        
        return false;
    }
    
    public void setFechas(){
        //Ubicar las posibles fechas para el vuelo seleccionado
        fechas = initialData.getFechas(this.getCiudadOrigenCbx().getSelectedItem().toString(), this.getCiudadDestinoCbx().getSelectedItem().toString());
        
        String[] arr = new String[fechas.size()];
        
        for(int i=0; i<fechas.size(); i++)
            arr[i] = fechas.get(i)[0].toString();
        
        this.fechaCbx.setModel(new DefaultComboBoxModel<>(arr));
        
    }
    
    public void setCosto(){
        
        String costo = fechas.get(fechaCbx.getSelectedIndex())[1];
        int plus = 0;
        if(claseCbx.getSelectedIndex()==1) plus=20000;
        else if(claseCbx.getSelectedIndex()==2) plus=60000;
        this.costoTF.setText(String.valueOf(Long.parseLong(costo)+plus));
        
    }
    
    public static void finalizarRegistro(){
        
        if(informacion[4].toString().equals(""))
            new InitialData().crearReserva(informacion[0].toString(), informacion[1].toString(), informacion[2].toString(), informacion[3].toString());
        else
            new InitialData().actualizarReserva(informacion[0].toString(), informacion[1].toString(), informacion[2].toString(), informacion[3].toString());
    
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        idPasajeroLbl = new javax.swing.JLabel();
        ciudadOrigenLbl = new javax.swing.JLabel();
        ciudadDestinoLbl = new javax.swing.JLabel();
        fechaLbl = new javax.swing.JLabel();
        claseLbl = new javax.swing.JLabel();
        costoLabel = new javax.swing.JLabel();
        idPasajeroTF = new javax.swing.JTextField();
        costoTF = new javax.swing.JTextField();
        ciudadOrigenCbx = new javax.swing.JComboBox<>();
        ciudadDestinoCbx = new javax.swing.JComboBox<>();
        claseCbx = new javax.swing.JComboBox<>();
        reservaBtn = new javax.swing.JLabel();
        tituloLbl = new javax.swing.JLabel();
        fechaCbx = new javax.swing.JComboBox<>();
        iconoBtn = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 204));
        setPreferredSize(new java.awt.Dimension(1000, 600));

        idPasajeroLbl.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        idPasajeroLbl.setText("idPasajero");

        ciudadOrigenLbl.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        ciudadOrigenLbl.setText("Ciudad Origen");

        ciudadDestinoLbl.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        ciudadDestinoLbl.setText("Ciudad Destino");

        fechaLbl.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        fechaLbl.setText("Fecha");

        claseLbl.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        claseLbl.setText("Clase");

        costoLabel.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        costoLabel.setText("Costo");

        idPasajeroTF.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        idPasajeroTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                idPasajeroTFKeyTyped(evt);
            }
        });

        costoTF.setEditable(false);
        costoTF.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N

        ciudadOrigenCbx.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        ciudadOrigenCbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        ciudadDestinoCbx.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        ciudadDestinoCbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        claseCbx.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        claseCbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        reservaBtn.setBackground(new java.awt.Color(255, 255, 255));
        reservaBtn.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        reservaBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        reservaBtn.setText(textoBtn);
        reservaBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reservaBtn.setOpaque(true);
        reservaBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reservaBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                reservaBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                reservaBtnMouseExited(evt);
            }
        });

        tituloLbl.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        tituloLbl.setText("RESERVA");

        fechaCbx.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        fechaCbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        iconoBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utils/avion.png"))); // NOI18N
        iconoBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        iconoBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconoBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(iconoBtn)
                .addGap(361, 361, 361)
                .addComponent(tituloLbl)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(costoLabel)
                            .addComponent(claseLbl))
                        .addGap(165, 165, 165)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(claseCbx, 0, 190, Short.MAX_VALUE)
                            .addComponent(costoTF)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(idPasajeroLbl)
                                        .addComponent(ciudadOrigenLbl))
                                    .addGap(80, 80, 80))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(ciudadDestinoLbl)
                                    .addGap(70, 70, 70)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(fechaLbl)
                                .addGap(164, 164, 164)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fechaCbx, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ciudadDestinoCbx, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(idPasajeroTF)
                            .addComponent(ciudadOrigenCbx, 0, 190, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                .addComponent(reservaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(154, 154, 154))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(iconoBtn)
                    .addComponent(tituloLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idPasajeroLbl)
                    .addComponent(idPasajeroTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ciudadOrigenLbl)
                    .addComponent(ciudadOrigenCbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ciudadDestinoLbl)
                            .addComponent(ciudadDestinoCbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fechaLbl)
                            .addComponent(fechaCbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(reservaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(claseLbl)
                    .addComponent(claseCbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(costoLabel)
                    .addComponent(costoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(102, 102, 102))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void reservaBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reservaBtnMouseExited
        reservaBtn.setBackground(Color.white);
        reservaBtn.setForeground(Color.black);
    }//GEN-LAST:event_reservaBtnMouseExited

    private void reservaBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reservaBtnMouseEntered
        reservaBtn.setBackground(new Color(17, 153, 250));
        reservaBtn.setForeground(Color.white);
    }//GEN-LAST:event_reservaBtnMouseEntered

    private void reservaBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reservaBtnMouseClicked
        
        int correct = comprobarCampos();
        
        //Revisar que todo este lleno
        if(correct==1){
            
            int confirm = JOptionPane.showConfirmDialog(null, "Esta seguro que desea agendar la reserva para:"
                        + "\n"+idPasajeroTF.getText()+" con vuelo:"+"\n"+ciudadOrigenCbx.getSelectedItem()+" - "+ciudadDestinoCbx.getSelectedItem()
                        +"\nPara el dia: "+fechaCbx.getSelectedItem()+"\nCon un costo de: "+costoTF.getText()+"\nEn clase "+claseCbx.getSelectedItem());
            
            if(confirm==0){
                //Revisar si ya esta el pasajero o no
                
                String reserva = infoUpdate;
                if(reserva.equals("")) reserva = crearIdReserva();
                
                System.out.println("SE VA A ENVIAR ESTE IDRESERVA: "+reserva);
                
                if(!initialData.isPasajero(idPasajeroTF.getText())){
                    confirm = JOptionPane.showConfirmDialog(null, "La informacion del pasajero no fue encontrada en nuestra base de datos\n"
                            + "¿Desea realizar el registro del usuario?");
                
                    //NUEVO USUARIO
                    
                    if(confirm==0){
                        crearUsuario();
                        informacion = new Object[]{reserva, claseCbx.getSelectedItem().toString(), idPasajeroTF.getText(), fechas.get(fechaCbx.getSelectedIndex())[2], infoUpdate};
                    }
                    
                
                }else{
                    if(!infoUpdate.equals("")) initialData.actualizarReserva(reserva, claseCbx.getSelectedItem().toString(), idPasajeroTF.getText(), fechas.get(fechaCbx.getSelectedIndex())[2]);
                    else initialData.crearReserva(reserva, claseCbx.getSelectedItem().toString(), idPasajeroTF.getText(), fechas.get(fechaCbx.getSelectedIndex())[2]);
                
                    principal.dispose();
                    new MainWindow();
                    
                }
            }
        }else{
            switch(correct){
                case 2: JOptionPane.showMessageDialog(null, "El campo idPasajero esta vacio");    break;
                case 3: JOptionPane.showMessageDialog(null, "Por favor seleccione las ciudades para su vuelo");     break;
                case 4: JOptionPane.showMessageDialog(null, "Por favor seleccione una fecha para su vuelo");        break;
                default: JOptionPane.showMessageDialog(null, "El campo idPasajero no es válido");
            }
        }
        
        
    }//GEN-LAST:event_reservaBtnMouseClicked

    private void idPasajeroTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idPasajeroTFKeyTyped
        char caracter = evt.getKeyChar();

        // Verificar si la tecla pulsada no es un digito
        if(((caracter < '0') ||
           (caracter > '9')) &&
           (caracter != '\b' /*corresponde a BACK_SPACE*/))
        {
           evt.consume();  // ignorar el evento de teclado
        }else if (idPasajeroTF.getText().length()== 10){
            evt.consume();
        }
    }//GEN-LAST:event_idPasajeroTFKeyTyped

    private void iconoBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconoBtnMouseClicked
        principal.dispose();
        new MainWindow();
    }//GEN-LAST:event_iconoBtnMouseClicked

    
    private int comprobarCampos(){
        
        if(idPasajeroTF.getText().trim().equals("") || idPasajeroTF.getText().split(" ").length>1) return 2;
        if(ciudadOrigenCbx.getSelectedIndex()==0 || ciudadDestinoCbx.getSelectedIndex()==0) return 3;
        if(fechaCbx.getSelectedIndex()==0) return 4;
        try{
            if(Long.parseLong(idPasajeroTF.getText())>2145000000) return 5;
        }catch(Exception ex){
            return 6; 
        }
        return 1;
        
    }
    
    private String crearIdReserva(){
        String clave = "";
        
        int random = 0;
        
        for(int i=0; i<6; i++){
            if(i<3){//Establecer letra
                do{
                    random = (int)(Math.random()*(122-65+1)+65);
                }while(random>90 && random<97);
                
                clave += (char) random;
                
                
            }else{//Establecer numero
                do{
                    random = (int)(Math.random()*11-1);
                }while(random>9);
                
                clave += random;
            }
        }
        
        return clave;
        
    }
    
    private void crearUsuario(){
        
        principal.crearPanel(1, "", idPasajeroTF.getText(), "");
        
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ciudadDestinoCbx;
    private javax.swing.JLabel ciudadDestinoLbl;
    private javax.swing.JComboBox<String> ciudadOrigenCbx;
    private javax.swing.JLabel ciudadOrigenLbl;
    private javax.swing.JComboBox<String> claseCbx;
    private javax.swing.JLabel claseLbl;
    private javax.swing.JLabel costoLabel;
    private javax.swing.JTextField costoTF;
    private javax.swing.JComboBox<String> fechaCbx;
    private javax.swing.JLabel fechaLbl;
    private javax.swing.JLabel iconoBtn;
    private javax.swing.JLabel idPasajeroLbl;
    private javax.swing.JTextField idPasajeroTF;
    private javax.swing.JLabel reservaBtn;
    private javax.swing.JLabel tituloLbl;
    // End of variables declaration//GEN-END:variables
}
