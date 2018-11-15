package Modelos;

public class Circulo extends Coordenada {

	private float radio;

	public Circulo() {

		super();
		this.radio = 0;
	}

	public Circulo(Coordenada cor, float r) {
		super(cor);
		this.radio = r;

	}

	public Circulo(Circulo cir) {
		super(cir);
		this.radio = cir.radio;

	}
	
	public float getRadio(){
		return this.radio;
	}
	public void setRadio(float r){
		 this.radio=r;
	}
	public Coordenada getCentro(){
	Coordenada nva = new Coordenada(this.getX(),this.getY());
	return nva;
	}
	
}
