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

import javax.swing.*;

public class Ventana extends JFrame implements KeyListener {
	
	JPanel panel = new JPanel();
	Packman pacPanel = new Packman();
	JPanel panelNorte = new JPanel();
	JPanel menu = new JPanel();
	
	int direccion;
	float tiempo;
	JLabel lblCronometro = new JLabel();
	
	
	Player jugador1 = new Player(60,110,10,10,new Color(0,255,0),10);
	Player meta;
	
	Timer timer;
	
	
	public Ventana()
	{	
		
		direccion = 1;
		pacPanel.addPlayer(jugador1);
				
		 int delay = 120;
	        
	        timer = new Timer(delay, ciclo);
	        
		
		this.setSize(700,750);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("User login");
		
		this.agregarElementos();
		
		this.setVisible(true);
		this.addKeyListener(pacPanel);
		this.addKeyListener(this);
		
		
		

	}
	

		
		

	
	ActionListener ciclo = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
        	
        	
        	
    		tiempo+=0.12;
    		lblCronometro.setText("tiempo Transcurrido: " + Integer.toString((int)tiempo));
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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean colicionMundial()
	{
		for (Player objeto : pacPanel.getJugadores()) {
			
			if(objeto != null && !objeto.equals(jugador1) && jugador1.intersects(objeto))
			{
				return true;
			}
		}
		
		return false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		
		
		if(e.getKeyCode() == 38)
		{
			jugador1.setPosY(jugador1.getPosY()-jugador1.getVelocidad());
		
			if(!colicionMundial())
			{
				direccion = 1;
			}
			
			jugador1.setPosY(jugador1.getPosY()+jugador1.getVelocidad());
			
		}
		
		if(e.getKeyCode() == 40)
		{
			jugador1.setPosY(jugador1.getPosY()+jugador1.getVelocidad());
			
			if(!colicionMundial())
			{
				direccion = 2;
			}
			
			jugador1.setPosY(jugador1.getPosY()-jugador1.getVelocidad());
		}
		
		if(e.getKeyCode() == 37)
		{
			jugador1.setPosX(jugador1.getPosX()-jugador1.getVelocidad());
			
			if(!colicionMundial())
			{
				direccion = 3;
			}
			jugador1.setPosX(jugador1.getPosX()+jugador1.getVelocidad());
			
		}
		
		if(e.getKeyCode() == 39)
		{
			jugador1.setPosX(jugador1.getPosX()+jugador1.getVelocidad());
			if(!colicionMundial())
			{
				direccion = 4;
			}
			jugador1.setPosX(jugador1.getPosX()-jugador1.getVelocidad());
			
		} 
		
	
		
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}













