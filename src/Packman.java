import java.awt.Color;
import java.awt.Font;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Packman extends JPanel implements KeyListener{
	
	char letra;
	int playerHealth;
	int focus;
	int levelPass;
	int velocidad;
	
	int puntaje;
	boolean newLevel = false;
	
	boolean newGame = false;
	
	

	
	public boolean isNewGame() {
		return newGame;
	}

	public void setNewGame(boolean newGame) {
		this.newGame = newGame;
	}

	public boolean isNewLevel() {
		return newLevel;
	}

	public void setNewLevel(boolean newLevel) {
		this.newLevel = newLevel;
	}
	
	int enemylevel = 6;

	public int getEnemylevel() {
		return enemylevel;
	}

	public void setEnemylevel(int enemylevel) {
		this.enemylevel = enemylevel;
	}

	Enemigo[] enemigos;
	int enemigoIndex;
	
	Image[] imagen;
	
	Image fondo;
	
	Timer timer;
	int shipAnimationIndex;
	
	public Packman()
	{
		puntaje = 0;
		velocidad = 2;
		levelPass = 0;
		letra = ' ';
		focus =11;
		
		playerHealth = 3;
		shipAnimationIndex = 0;
		
		timer = new Timer(100, ciclo);
		timer.start();
	        
		enemigos = new Enemigo[10];
		enemigoIndex = 0;
		
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
	
	public int getPlayerHealth() {
		return playerHealth;
	}

	public void setPlayerHealth(int playerHealth) {
		this.playerHealth = playerHealth;
	}

	ActionListener ciclo = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
        	
        	
        	
    		shipAnimationIndex++;
    		if(shipAnimationIndex>17)
    		{
				shipAnimationIndex=0;
    		}
    		
    		if(levelPass==10)
    		{
    			enemigos = new Enemigo[10];
    			enemigoIndex = 0;
    			levelPass = 0;
    			velocidad +=2;
    			enemylevel +=2;
    			newLevel = true;
    			puntaje += 10;
    		}
    		
    		for(int i = 0; i<enemigoIndex;i++)
    		{
    			
    			
    			enemigos[i].setLevel(enemylevel);
    					
    		}
    		
    		for(int i = 0; i<enemigoIndex;i++)
    		{
    			
    			if(enemigos[i]!= null)
    			{
    				enemigos[i].setPosY(enemigos[i].getPosY()+velocidad);
    				
    			}
    			
    		}
    		
    		
    		for(int i = 0; i<enemigoIndex;i++)
    		{
    			
    			if(enemigos[i]!= null && enemigos[i].isVivo() && enemigos[i].getPosY()>600)
    			{
    				playerHealth--;
    				enemigos[i].setVivo(false);
    				levelPass++;
    				if(i==focus)
    				{
    					focus = 11;
    				}
    				
    				if (playerHealth == 0)
    				{
    					
    					String GameOver = JOptionPane.showInputDialog(null, "GAME OVER      Ingresa tu nombre" );
    					enemylevel = 6;
    					velocidad = 2;
    					levelPass = 0;
    					letra = ' ';
    					focus =11;
    					puntaje = 0;
    					
    					playerHealth = 3;
    					shipAnimationIndex = 0;
    					
    					timer = new Timer(100, ciclo);
    					timer.start();
    				        
    					enemigos = new Enemigo[10];
    					enemigoIndex = 0;
    					newGame = true;
    				}
    				
    				
    			}
    			
    		}
    		
    		
    		
        	
        }
    };
	


	@Override
	public void paint(Graphics g) {

		super.paint(g);
		
		Graphics g2d = (Graphics2D) g;
		
		g2d.setColor(new Color(100,100,100));
		g2d.setFont(new Font(Font.SANS_SERIF,Font.BOLD,30));
		
		g2d.drawImage(fondo,0,0,700,700,this);
		
		for(int i = 0; i<enemigoIndex;i++)
		{
			
			if(enemigos[i]!= null && enemigos[i].isVivo())
			{
				
				 g2d.drawImage(imagen[shipAnimationIndex], enemigos[i].getPosX(),enemigos[i].getPosY(),100,100,this);
				 if(enemigos[i].isFocus())
				 {
					g2d.setColor(new Color(255,0,0));
				 }
				 else 
				 {
					 g2d.setColor(new Color(100,100,100)); 
				 }
				 g2d.drawString(enemigos[i].getPalabra(),enemigos[i].getPosX(),enemigos[i].getPosY());
			}
			
		}
		
		for(int i = 0;i<playerHealth;i++)
		{
			g2d.fillRect((400 + i*50),520, 40,40);
		}
		 
		 g2d.drawImage(imagen[shipAnimationIndex], 300,520,100,100,this);
		
	}
	
	public void addEnemy(Enemigo newEnemy)
	{
		
		enemigos[enemigoIndex] = newEnemy;
		enemigoIndex++;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		letra = e.getKeyChar();
		if(newLevel == false)
		{
			
			
			if(focus == 11 )
			{
				for(int i = 0; i<enemigoIndex;i++)
	    		{
	    			
					String aux = "";
					if(enemigos[i].isVivo() && enemigos[i].getPalabra().charAt(0)== letra)
					{
						for(int j = 1; j<enemigos[i].getPalabra().length();j++)
						{
							aux = aux + enemigos[i].getPalabra().charAt(j);
						}
						
						enemigos[i].setPalabra(aux);
						enemigos[i].setFocus(true);
						focus = i;
						break;
					
	    			}
	    			
	    		}
				
			}
			else
			{
				String aux = "";
				if(enemigos[focus].getPalabra().charAt(0) == letra)
				{
					for(int j = 1; j<enemigos[focus].getPalabra().length();j++)
					{
						aux = aux + enemigos[focus].getPalabra().charAt(j);
					}
					
					enemigos[focus].setPalabra(aux);
					System.out.println(aux);
					
					if(enemigos[focus].getPalabra().equals(""))
					{
						puntaje++;
						enemigos[focus].setVivo(false);
						focus = 11;
						levelPass++;
					}
				}
			}
		}		
	}
	

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}






	
}