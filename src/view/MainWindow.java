package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.UIManager;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainWindow extends JFrame {
    
    JPanel tablaPanel;
    JPanel botonesPanel;
    
    public MainWindow(){
        initComponents();
    }
    
    private void initComponents(){
        setTitle("IvairlosAirlines - MVC");
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        
        tablaPanel = new ResultsPanel();
        setContentPane(tablaPanel);
        botonesPanel = new BtnsMainPanel((ResultsPanel) tablaPanel, this);
        add(botonesPanel);
        setSize(1020, 600);
        setResizable(false);
        tablaPanel.setBackground(new Color(204, 204, 204));
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize  = getSize();
        setLocation((screenSize.width  - frameSize.width)  / 2, 
                    (screenSize.height - frameSize.height) / 2);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        
        
    }
    
    public void crearPanel(int panel, String nombreBoton, String idPasajero, String infoUpdate){
        
        this.botonesPanel.setVisible(false);
        this.botonesPanel.removeAll();
        this.tablaPanel.setVisible(false);
        this.tablaPanel.removeAll();
        if(panel==0){
            ReservaPanel nuevaReserva = new ReservaPanel(this, infoUpdate);
            nuevaReserva.setTextoBtn(nombreBoton);
            this.tablaPanel.add(nuevaReserva);
        }else if(panel==1){
            PasajeroPanel nuevoPasajero = new PasajeroPanel(this);
            nuevoPasajero.setIdPasajero(idPasajero);
            this.tablaPanel.add(nuevoPasajero);
        }else if(panel==2){
            Login login = new Login(this);
            this.tablaPanel.add(login);
        }else if(panel==3){
            VueloPanel vuelo = new VueloPanel(this);
            this.tablaPanel.add(vuelo);
        }
        this.tablaPanel.setVisible(true);
        
    }
    
    
}