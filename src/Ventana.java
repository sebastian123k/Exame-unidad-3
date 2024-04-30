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
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Ventana extends JFrame  {
	
	JPanel panel = new JPanel();
	Packman pacPanel = new Packman();
	JPanel panelNorte = new JPanel();
	JPanel Audio = new JPanel();
	JPanel menu = new JPanel();
	
	int nivel = 1;
	
	int timeDelay = 40;
	
	int direccion;
	float tiempo;
	JLabel lblCronometro = new JLabel();
	
	String keylogger;
	
	
	Player jugador1 = new Player(60,110,10,10,new Color(0,255,0),10);
	Player meta;
	
	Timer timer;
	
	int enemigosDropeados;
	int dropDelay;
	
	
	
	public Ventana()
	{	
		
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
        		timeDelay-=8;
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
		
		JButton btnActivarSonido = new JButton("sonido");
		btnActivarSonido.setFocusable(false);
		btnActivarSonido.setBounds(500,20,50,50);
		JButton btnActivarEfectos = new JButton("efectos");
		btnActivarEfectos.setFocusable(false);
		btnActivarEfectos.setBounds(560,20,50,50);

		
		
		
		JButton precionar = new JButton("iniciar juego");
		precionar.setFocusable(false);
		precionar.setBounds(250,300,200,100);
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
		
		menu.setBackground(Color.blue);
		
		menu.add(precionar);
		menu.add(btnActivarSonido);
		menu.add(btnActivarEfectos);
		
		

		
		pacPanel.setBackground(Color.blue);
		
		panel.add(menu,BorderLayout.CENTER);
		

		
		this.add(panel);
		
		
	}


}













