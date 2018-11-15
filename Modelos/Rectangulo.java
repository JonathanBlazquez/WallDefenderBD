package Modelos;

public class Rectangulo extends Coordenada {

	private float lado1;
	private float lado2;
	
	public Rectangulo(){
		
		super();
		this.lado1=0;
		this.lado2=0;
	}
	
	public Rectangulo(Coordenada cor,float x,float y){
		
		super(cor);
		this.lado1=x;
		this.lado2=y;
		
	}

	public Rectangulo(Rectangulo rc){
		super(rc.getX(),rc.getY());
		this.lado1=rc.lado1;
		this.lado2=rc.lado2;
	}
	
	public float Getlado(int lado){
		
		if(lado==1){
			
			return this.lado1;
		}
		if(lado==2){
			
			return this.lado2;
		}
		if(lado !=1 && lado !=2){
			return 0;
		}
		
		return 0;
		
	}
	
	
}
