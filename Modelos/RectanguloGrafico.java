package Modelos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class RectanguloGrafico extends Rectangulo implements Dibujable {

	Color color;
	
	public static int Aleatorio(int Max, int Min)
    {
        return (int) (Math.random() *(Max-Min));
    }
	
	public RectanguloGrafico(Coordenada cor,float x, float y,Color uncolor ) {
	super(cor,x,y);
	this.color=uncolor;
	}
	
	
	
	public void dibujar(Graphics dw) {
		
		
		dw.setColor(color);
		dw.fillRect((int)this.getX(),(int)this.getY(),(int)this.Getlado(1),(int)this.Getlado(2));
	}
	public void pintar(Color uncolor) {
		
		this.color=uncolor;
		
	}
	
	
	
	
	public void Ciclo(int mov){
		
		float x=this.getY();
		
		//velocidad
		this.setY(x+=mov);
		
	}
	
}
