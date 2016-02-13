/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escogecolor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author josea
 */
public class EscogeColor extends JFrame {

    private JPanel colorOriginal = new JPanel();
    private JPanel colorVisto =new JPanel();
    private int r = 0;
    private int g = 0 ;
    private int b = 0;
    private JButton btnPromediar;
    private JLabel textResult; 
    private int[] colores = new int[3];
    private JTextArea textArea;
    
    private Color color2;
    private JButton btnintenta;
    private JSlider sliderR;
    private JSlider sliderG;
    private JSlider sliderB;
    
    public EscogeColor(){
    this.setLayout(new BorderLayout());
    
    textArea = new JTextArea(20,20);
    //textArea.setPreferredSize(new Dimension(100,100));
    textArea.setEditable(false);
    
    JPanel panelCentral = new JPanel( new GridLayout(2,1,10,10));
    panelCentral.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black),BorderFactory.createEmptyBorder(10, 15, 5, 15)));
    JPanel panelSlider = new JPanel(new GridLayout(18,1,10,10));
    panelSlider.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black),BorderFactory.createEmptyBorder(10, 15, 5, 15)));
    
    colorOriginal.setBounds(new Rectangle(400,400));
    colorOriginal.setBorder((BorderFactory.createTitledBorder(null, "Color Original",TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Calibri", 1, 14),Color.BLACK)));
    colorVisto.setBounds(new Rectangle(400,400));
    colorVisto.setBorder((BorderFactory.createTitledBorder(null, "Color Percibido",TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Calibri", 1, 14),Color.WHITE)));
    
    
    /*Generamos el color en random*/
    Random random = new Random();
    for(int i=0;i<3;i++)
        colores[i] = random.nextInt(255);
    
    Color color = new Color(colores[0],colores[1],colores[2]);
    colorOriginal.setBackground(color);
    colorVisto.setBackground(Color.black);
  
    panelCentral.add(colorOriginal);
    panelCentral.add(colorVisto);
    
    /*Agregamos los slider*/
    panelSlider.add(new JLabel("Color R"));
    sliderR = new JSlider(JSlider.HORIZONTAL,0,255,0);
    sliderR.setName("R");
     // registrar componente de escucha de eventos de JSlider
      sliderR.addChangeListener(
         new ChangeListener() {  // clase interna anónima
            // manejar cambio en el valor del control deslizable
            public void stateChanged( ChangeEvent e )
            {
               r=sliderR.getValue();
               color2 = new Color(r,g,b);
               colorVisto.setBackground(color2);
            }
         } // fin de la clase interna anónima
      ); // fin de la llamada a addChangeListener
    panelSlider.add(sliderR);
    
    panelSlider.add(new JLabel("Color G"));
    sliderG = new JSlider(JSlider.HORIZONTAL,0,255,0);
    sliderG.setName("G");
    // registrar componente de escucha de eventos de JSlider
      sliderG.addChangeListener(
         new ChangeListener() {  // clase interna anónima
            // manejar cambio en el valor del control deslizable
            public void stateChanged( ChangeEvent e )
            {
               g=sliderG.getValue();
               color2 = new Color(r,g,b);
               colorVisto.setBackground(color2);
            }
         } // fin de la clase interna anónima

      ); // fin de la llamada a addChangeListener
    panelSlider.add(sliderG);
   
    panelSlider.add(new JLabel("Color B"));
    sliderB = new JSlider(JSlider.HORIZONTAL,0,255,0);
    sliderB.setName("B");
    // registrar componente de escucha de eventos de JSlider
      sliderB.addChangeListener(
         new ChangeListener() {  // clase interna anónima
            // manejar cambio en el valor del control deslizable
            public void stateChanged( ChangeEvent e )
            {
               b=sliderB.getValue();
               color2 = new Color(r,g,b);
               colorVisto.setBackground(color2);
            }
         } // fin de la clase interna anónima
      ); // fin de la llamada a addChangeListener
    panelSlider.add(sliderB);
    
    /*Creamos el boton para promediar*/
    btnPromediar = new JButton("Promediar");
    btnPromediar.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
              float rf = ((colorVisto.getBackground().getRed()*10)/colorOriginal.getBackground().getRed())*10;
              float gf = ((colorVisto.getBackground().getGreen()*10)/colorOriginal.getBackground().getGreen())*10;
              float bf = ((colorVisto.getBackground().getBlue()*10)/colorOriginal.getBackground().getBlue())*10;
              textArea.setText("");
              /*textArea.append(
              "Tu promedio fue \n"
                      + "en R : O = "+colorOriginal.getBackground().getRed()+" V = "+colorVisto.getBackground().getRed()
                            + "    "+rf+"% \n"
                      + "en G : O = "+colorOriginal.getBackground().getGreen()+" V = "+colorVisto.getBackground().getGreen()
                            + "    "+gf+"% \n"
                      + "en B : O = "+colorOriginal.getBackground().getBlue()+" V = "+colorVisto.getBackground().getBlue()
                            + "    "+bf+"% " 
              );*/
              textArea.append("R: "+rf+"% G: "+gf+"% B: "+bf+"%");
              btnintenta.setEnabled(true);
              btnPromediar.setEnabled(false);
              sliderR.enable(false);
              sliderG.enable(false);
              sliderB.enable(false);
          }
        });
    panelSlider.add(btnPromediar);
 
    /*Creamos el texto para mostrar el resultado*/
    textResult = new JLabel("Error: ");
    panelSlider.add(textResult);
    /*Creamos nuestro TextArea*/
    panelSlider.add(textArea);
    
     btnintenta= new JButton("Intentar otra vez");
     btnintenta.setEnabled(false);
     btnintenta.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
                Random random = new Random();
                for(int i=0;i<3;i++)
                    colores[i] = random.nextInt(255);

                Color color = new Color(colores[0],colores[1],colores[2]);
                colorOriginal.setBackground(color);
                sliderR.setValue(0);
                sliderG.setValue(0);
                sliderB.setValue(0);
              btnPromediar.setEnabled(true);
              btnintenta.setEnabled(false);
              textArea.setText("");
              sliderR.enable(true);
              sliderG.enable(true);
              sliderB.enable(true);
          }
        });
     panelSlider.add(btnintenta);
    
    /*Agregamos los componentes a la ventana principal*/
    this.add(panelCentral,BorderLayout.CENTER);
    this.add(panelSlider,BorderLayout.EAST);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    this.setLocation(300,100);
    this.setSize(1000, 620);
    
    }
    
    
    public static void main(String[] args) {
        
       EscogeColor ventana = new EscogeColor();
    }
    
}
