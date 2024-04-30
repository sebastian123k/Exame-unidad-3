import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Ventana extends JFrame  {
	
	JPanel panel = new JPanel();
	Packman pacPanel = new Packman();
	JPanel panelNorte = new JPanel();
	JPanel Audio = new JPanel();
	JPanel menu = new JPanel();
	
	int nivel = 1;
	
	int timeDelay = 80;
	
	int direccion;
	float tiempo;
	JLabel lblCronometro = new JLabel();
	
	String keylogger;
	
	
	Player jugador1 = new Player(60,110,10,10,new Color(0,255,0),10);
	Player meta;
	
	Timer timer;
	
	int enemigosDropeados;
	int dropDelay;
	
	int maxPuntaje = 0 ;
	
	
	
	public Ventana()
	{	
		

		reproducirAudioLoop("musica.wav",true);
		
		keylogger = "";
		dropDelay = 0;
		enemigosDropeados = 0;
		
		
				
		 int delay = 60;
	        
	        timer = new Timer(delay, ciclo);
	        
		
		this.setSize(700,750);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("User login");
		
		this.agregarElementos();
		
		this.setVisible(true);
		this.addKeyListener(pacPanel);
		
		
		
		

	}
	

		
		

	
	ActionListener ciclo = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
        	
        	
        	dropDelay ++;
        	if(pacPanel.isNewLevel())
        	{
        		enemigosDropeados = 0;
        		timeDelay-=10;
        		nivel++;
        		pacPanel.setNewLevel(false);
        	}
        	
        	if(pacPanel.isNewGame())
        	{
        		nivel = 1;
        		tiempo = (float) 0.00; 
        		timeDelay = 80;
        		timer.stop();
				panel.remove(pacPanel);
				panel.add(menu,BorderLayout.CENTER);
				panel.repaint();
				panel.revalidate();
				pacPanel.setNewGame(false);
				enemigosDropeados=0;
				if(maxPuntaje<pacPanel.getPuntaje())
				{
					maxPuntaje = pacPanel.getPuntaje();
				}
        	}
        	
        	if(dropDelay>timeDelay && enemigosDropeados<10)
        	{
        		pacPanel.addEnemy(new Enemigo(pacPanel.getEnemylevel()));
				enemigosDropeados++;
				dropDelay= 0;
        	}
        	
    		tiempo+=0.06;
    		lblCronometro.setText("Time " + Integer.toString((int)tiempo ) + "    Level: " + nivel + "   Score: " + pacPanel.getPuntaje());
        	pacPanel.repaint();
    		
        	
        }
    };
    

	
	public void agregarElementos() {
		
		
		panel.setBounds(0,0,690,710);
		panel.setLayout(new BorderLayout());
		
		JButton btnActivarSonido = new JButton();
		btnActivarSonido.setFocusable(false);
		btnActivarSonido.setBounds(500,20,50,50);
		btnActivarSonido.setBackground(Color.LIGHT_GRAY);
		btnActivarSonido.setOpaque(true);
		btnActivarSonido.setIcon(new ImageIcon(getClass().getResource("soundon.png")));
		btnActivarSonido.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (clip != null && clip.isRunning()) {
		            clip.stop();
		            btnActivarSonido.setIcon(new ImageIcon(getClass().getResource("soundof.png")));
		            
		            
		        }
				else
				{
					reproducirAudioLoop("musica.wav",true);
					btnActivarSonido.setIcon(new ImageIcon(getClass().getResource("soundon.png")));
				}
				
			}});
		
		
		JButton btnActivarEfectos = new JButton();
		btnActivarEfectos.setFocusable(false);
		btnActivarEfectos.setBounds(560,20,50,50);
		btnActivarEfectos.setBackground(Color.LIGHT_GRAY);
		btnActivarEfectos.setOpaque(true);
		btnActivarEfectos.setIcon(new ImageIcon(getClass().getResource("efecton.png")));
		btnActivarEfectos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(pacPanel.isEfectos())
				{
					btnActivarEfectos.setIcon(new ImageIcon(getClass().getResource("efectoff.png")));
				}
				else
				{
					btnActivarEfectos.setIcon(new ImageIcon(getClass().getResource("efecton.png")));
				}
				pacPanel.setEfectos(!pacPanel.isEfectos());
				
				
			}});

		
		
		
		JButton precionar = new JButton("iniciar juego");
		precionar.setFont(new Font(Font.SANS_SERIF,Font.BOLD,40));
		precionar.setBackground(new Color(1,1,1,1));
		precionar.setForeground(Color.white);
		precionar.setOpaque(true);
		precionar.setFocusable(false);
		precionar.setBounds(200,450,300,100);
		precionar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				pacPanel.addEnemy(new Enemigo(6));
				enemigosDropeados++;
				
				Audio.setLayout(new GridLayout(1,2));
				panelNorte.setLayout(new GridLayout(1,2));
				
				
				lblCronometro.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
				lblCronometro.setBackground(Color.black);
				lblCronometro.setForeground(Color.white);
				lblCronometro.setOpaque(true);
				lblCronometro.setBorder(new LineBorder(Color.black,15));
				
				
				
				Audio.add(btnActivarSonido);
				Audio.add(btnActivarEfectos);
				
				panelNorte.add(lblCronometro);
				panelNorte.add(Audio);
				
				
				panel.add(panelNorte,BorderLayout.NORTH);
				
				timer.start();
				panel.remove(menu);
				panel.add(pacPanel,BorderLayout.CENTER);
				panel.repaint();
				panel.revalidate();
				
			}});
		
		
		menu.setLayout(null);
		
		JLabel lblfondo = new JLabel();
		
		lblfondo.setOpaque(true);
		lblfondo.setIcon(new ImageIcon(getClass().getResource("fondo.jpg")));
		lblfondo.setBounds(50,0,575,800);
		
		
		
		
		JLabel lblfondo2 = new JLabel(("Max Score: " + maxPuntaje));
		lblfondo2.setFont(new Font(Font.SANS_SERIF,Font.BOLD,30));
		lblfondo2.setBackground(new Color(0,0,0,1));
		lblfondo2.setForeground(Color.white);
		lblfondo2.setOpaque(true);
		lblfondo2.setOpaque(true);
		lblfondo2.setBounds(250,300,300,100);
		
		JLabel lblfondo3 = new JLabel(("SPACEWARGAME"));
		lblfondo3.setFont(new Font(Font.SANS_SERIF,Font.BOLD,50));
		lblfondo3.setBackground(new Color(0,0,0,1));
		lblfondo3.setForeground(Color.white);
		lblfondo3.setOpaque(true);
		lblfondo3.setOpaque(true);
		lblfondo3.setBounds(140,100,500,200);
		
	
		
		
		menu.setBackground(Color.black);
		
		menu.add(precionar);
		menu.add(btnActivarSonido);
		menu.add(btnActivarEfectos);
		menu.add(lblfondo3);
		menu.add(lblfondo2);
		menu.add(lblfondo);
		
		
		

		
		pacPanel.setBackground(Color.blue);
		
		panel.add(menu,BorderLayout.CENTER);
		

		
		this.add(panel);
		
		
	}
	 private static Clip clip;

	    public static void reproducirAudioLoop(String ruta, boolean enBucle) {
	    	
	        try {
	            
	            File archivoAudio = new File(ruta);

	  
	            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(archivoAudio);

	         
	            clip = AudioSystem.getClip();

	            
	            clip.open(audioInputStream);

	           
	            if (enBucle) {
	                clip.loop(Clip.LOOP_CONTINUOUSLY);
	            } else {
	                clip.start();
	            }

	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }

	    public static void detenerReproduccion() {
	        if (clip != null && clip.isRunning()) {
	            clip.stop();
	        }
	    }

}













