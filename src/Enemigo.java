import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Enemigo {
	
	boolean vivo,focus;
	
	int posX,posY;
	
	int level;
	
	String imagen ;
	
	Timer timer;

	int explocion = 0;

	String palabra;

	public Enemigo(int level) {
		
		timer = new Timer(100, ciclo);
		
		
		int random = (int) (Math.random() * 10);
		
		switch (random)
		{
		case 0:
			imagen = "asteroid.png";
			break;
		case 1:
			imagen = "boarder.png";
			break;
			
		case 2:
			imagen = "core.png";
			break;
		case 3:
			imagen = "cruiser.png";
			break;
		case 4:
			imagen = "fighter.png";
			break;
		case 5:
			imagen = "flamer.png";
			break;
		case 6:
			imagen = "kamikaze.png";
			break;
		case 7:
			imagen = "parafighter.png";
			break;
		case 8:
			imagen = "rocketShip.png";
			break;
		case 9:
			imagen = "shieldShip.png";
			break;
		}
		
		
		
		this.level = level;
		this.focus = false;
		this.vivo = true;
		
		int randomPos = (int) (Math.random() * 600);
		this.posX = randomPos;
		this.posY = 0;
	
		
		int randomIndex;
		
		try {
	         
            
			JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(new FileReader("users.json"));
            
            
            JsonObject jsonObject = jsonElement.getAsJsonObject();

           
            
           
            do {
            	
            	 randomIndex = (int) (Math.random() * jsonObject.getAsJsonArray("users").size());
            	 	
            	  this.palabra = jsonObject.getAsJsonArray("users").get(randomIndex).getAsJsonObject().get("username").getAsString();
            	  
            	  if(this.palabra.length()>level)
            	  {
            		  this.palabra = jsonObject.getAsJsonArray("users").get(randomIndex).getAsJsonObject().get("firstName").getAsString();
            	  }
            	  
            	  if(this.palabra.length()>level)
            	  {
            		  this.palabra = jsonObject.getAsJsonArray("users").get(randomIndex).getAsJsonObject().get("lastName").getAsString();
            	  }
            	  
            	  if(this.palabra.length()>level)
            	  {
            		  this.palabra = jsonObject.getAsJsonArray("users").get(randomIndex).getAsJsonObject().get("eyeColor").getAsString();
            	  }
            	  
            	  
            } while(this.palabra.length()>level);
          
            
            
            
            
           
            
        } catch (Exception ex) {
            System.out.println(ex.toString());
            this.palabra = "error en " + ex.toString();
        }
	
		
		
	}
	
	ActionListener ciclo = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
        	
        	explocion++;
        	
        	
        
        	switch (explocion)
    		{
    		case 0:
    			imagen = "boom1.png";
    			break;
    		case 1:
    			imagen = "boom02.png";
    			break;
    			
    		case 2:
    			imagen = "boom03.png";
    			break;
    		case 3:
    			imagen = "boom04.png";
    			break;
    		case 4:
    			imagen = "boom05.png";
    			break;
    		case 5:
    			imagen = "boom06.png";
    			break;
    		case 6:
    			imagen = "boom07.png";
    			break;
    		case 7:
    			imagen = "boom08.png";
    			break;
    		case 8:
    			imagen = "boom9.png";
    			break;
    		case 9:
    			imagen = "boom10.png";
    			break;
    		case 10:
    			vivo = false;
    			break;
    		}

        }};
	
	
	
	public String getImagen() {
		return imagen;
	}


	public void setImagen(String imagen) {
		this.imagen = imagen;
	}


	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public boolean isFocus() {
		return focus;
	}

	public void setFocus(boolean focus) {
		this.focus = focus;
	}

	public boolean isVivo() {
		return vivo;
	}

	public void setVivo(boolean vivito) {
		if(vivito == false)
		{
			timer.start();
			posX-=100;
			posY-=100;
			imagen = "boom1.png";
			
		}
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}
	
}
