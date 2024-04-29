import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.*;
import java.io.FileReader;

public class Enemigo {
	
	boolean vivo,focus;
	
	int posX,posY;
	
	String palabra;

	public Enemigo() {
		
		this.focus = false;
		this.vivo = true;
		
		int randomPos = (int) (Math.random() * 650);
		this.posX = randomPos;
		this.posY = 0;
		
		int randomIndex;
		
		try {
	         
            
			JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(new FileReader("users.json"));
            
            
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            randomIndex = (int) (Math.random() * jsonObject.getAsJsonArray("users").size());
            this.palabra = jsonObject.getAsJsonArray("users").get(randomIndex).getAsJsonObject().get("username").getAsString();
            
            
           
            
        } catch (Exception ex) {
            System.out.println(ex.toString());
            this.palabra = "error en " + ex.toString();
        }
		
		
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

	public void setVivo(boolean vivo) {
		this.vivo = vivo;
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
