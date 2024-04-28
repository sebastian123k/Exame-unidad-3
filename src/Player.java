import java.awt.Color;
import java.awt.Graphics;
import java.util.Objects;

public class Player {
	
	
	int posX,posY,alto,ancho,velocidad;
	Color color;
	
	
	public Player(int x,int y,int alto, int ancho,Color color,int velocidad)
	{
		
		this.posX = x;
		this.posY = y;
		this.alto = alto;
		this.ancho = ancho;
		this.color = color;
		this.velocidad = velocidad;
		
	}
	
	
	public boolean intersects(Player otherPlayer)
	{
		if(otherPlayer.getPosX()+velocidad>posX+ancho)
		{
			return false;
		}
		if(otherPlayer.getPosY()+velocidad>posY+alto)
		{
			return false;
		}
	
		if (posX + ancho < otherPlayer.getPosX()-velocidad || otherPlayer.getPosX()-velocidad + otherPlayer.getAncho() < posX) {
		    return false; 
		}

		
		if (posY + alto < otherPlayer.getPosY()-velocidad || otherPlayer.getPosY()-velocidad + otherPlayer.getAlto() < posY) {
		    return false; 
		}

		
		return true;

	}
	
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(alto, ancho, color, posX, posY, velocidad);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		return alto == other.alto && ancho == other.ancho && Objects.equals(color, other.color) && posX == other.posX
				&& posY == other.posY && velocidad == other.velocidad;
	}


	public void draw(Graphics g)
	{
		
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

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	

}