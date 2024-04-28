import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Packman extends JPanel implements KeyListener{

	
	Player[] jugadores;
	int jugadorIndex;
	
	Image[] imagen;
	
	Image fondo;
	
	Timer timer;
	int shipAnimationIndex;
	
	public Packman()
	{
		shipAnimationIndex = 0;
		
		timer = new Timer(100, ciclo);
		timer.start();
	        
		jugadores = new Player[10];
		jugadorIndex = 0;
		
		ImageIcon icnFondo = new ImageIcon(getClass().getResource("b_0.jpg"));
		
		fondo = icnFondo.getImage();
		
		ImageIcon[] icono = new ImageIcon[18];
		icono[0] = new ImageIcon(getClass().getResource("redfighter0001.png"));
		icono[1] = new ImageIcon(getClass().getResource("redfighter0002.png"));
		icono[2] = new ImageIcon(getClass().getResource("redfighter0003.png"));
		icono[3] = new ImageIcon(getClass().getResource("redfighter0004.png"));
		icono[4] = new ImageIcon(getClass().getResource("redfighter0005.png"));
		icono[5] = new ImageIcon(getClass().getResource("redfighter0006.png"));
		icono[6] = new ImageIcon(getClass().getResource("redfighter0007.png"));
		icono[7] = new ImageIcon(getClass().getResource("redfighter0008.png"));
		icono[8] = new ImageIcon(getClass().getResource("redfighter0009.png"));
		
		icono[9] = new ImageIcon(getClass().getResource("redfighter0009.png"));
		icono[10] = new ImageIcon(getClass().getResource("redfighter0008.png"));
		icono[11] = new ImageIcon(getClass().getResource("redfighter0007.png"));
		icono[12] = new ImageIcon(getClass().getResource("redfighter0006.png"));
		icono[13] = new ImageIcon(getClass().getResource("redfighter0005.png"));
		icono[14] = new ImageIcon(getClass().getResource("redfighter0004.png"));
		icono[15] = new ImageIcon(getClass().getResource("redfighter0003.png"));
		icono[16] = new ImageIcon(getClass().getResource("redfighter0002.png"));
		icono[17] = new ImageIcon(getClass().getResource("redfighter0001.png"));
		
		imagen = new Image[18];
		
		for(int i=0;i<18;i++)
		{
			imagen[i] = icono[i].getImage();
		}
		
		
		addKeyListener(this);
		
	}
	
	ActionListener ciclo = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
        	
        	
        	
    		shipAnimationIndex++;
    		if(shipAnimationIndex>17)
    		{
				shipAnimationIndex=0;
    		}
    		
        	
        }
    };
	


	@Override
	public void paint(Graphics g) {

		super.paint(g);
		
		Graphics g2d = (Graphics2D) g;
		
		/*for(int i = 0; i<jugadorIndex;i++)
		{
			
			if(jugadores[i]!= null)
			{
				g2d.setColor(jugadores[i].getColor());
				g2d.fillRect(jugadores[i].getPosX(),jugadores[i].getPosY(),jugadores[i].getAncho(),jugadores[i].getAlto());
			}
			
		}
		*/
		 g2d.drawImage(fondo,0,0,700,700,this);
		 
		 g2d.drawImage(imagen[shipAnimationIndex], 300,520,100,100,this);
		 
	}
	
	public void addPlayer(Player newPlayer)
	{
		
		jugadores[jugadorIndex] = newPlayer;
		jugadorIndex++;
		System.out.println(jugadorIndex);
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println( e.getKeyCode());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



	public Player[] getJugadores() {
		return jugadores;
	}



	public void setJugadores(Player[] jugadores) {
		this.jugadores = jugadores;
	}


	
}