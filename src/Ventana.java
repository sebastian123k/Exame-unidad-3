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

public class Ventana extends JFrame  {
	
	JPanel panel = new JPanel();
	Packman pacPanel = new Packman();
	JPanel panelNorte = new JPanel();
	JPanel menu = new JPanel();
	
	int nivel = 1;
	
	int timeDelay = 100;
	
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
        		timeDelay-=20;
        		nivel++;
        		pacPanel.setNewLevel(false);
        	}
        	
        	if(dropDelay>timeDelay && enemigosDropeados<10)
        	{
        		pacPanel.addEnemy(new Enemigo());
				enemigosDropeados++;
				dropDelay= 0;
        	}
        	
    		tiempo+=0.06;
    		lblCronometro.setText("tiempo Transcurrido: " + Integer.toString((int)tiempo ) + "          nivel: " + nivel);
        	pacPanel.repaint();
    		
        	
        }
    };
    
    public void reiniciar()
    {
    	
    	jugador1.setPosX(60); 
    	jugador1.setPosY(110);
    	tiempo = 0;
    }
	
	public void agregarElementos() {
		
		
		panel.setBounds(0,0,690,710);
		panel.setLayout(new BorderLayout());
		
		JButton btnActivarSonido = new JButton("sonido");
		btnActivarSonido.setFocusable(false);
		btnActivarSonido.setBounds(500,20,50,50);
		JButton btnActivarEfectos = new JButton("sonido");
		btnActivarEfectos.setFocusable(false);
		btnActivarEfectos.setBounds(560,20,50,50);

		
		
		
		JButton precionar = new JButton("iniciar juego");
		precionar.setFocusable(false);
		precionar.setBounds(250,300,200,100);
		precionar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				pacPanel.addEnemy(new Enemigo());
				enemigosDropeados++;
				
				panelNorte.setLayout(new GridLayout(1,4));
				lblCronometro.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
				panelNorte.add(lblCronometro);
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













