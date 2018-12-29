package Registro;

public class Usuario {

	protected String nombre;
	protected String apellido;
	protected int edad;
	protected String contrase�a;
	public int monedas;
	protected String nick;
	private int eleccion;
	

	public Usuario(String nombre, String apellido, int edad, String contrase�a, String nick) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.contrase�a = contrase�a;
		this.monedas = 0;
		this.nick = nick;
		this.eleccion = 1;
	}
	
	public Usuario(String nombre, String apellido, int edad, String contrase�a, int monedas, String nick, int eleccion) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.contrase�a = contrase�a;
		this.monedas = monedas;
		this.nick = nick;
		this.eleccion = eleccion;
	}
	
	public Usuario() {
		super();
		this.nombre = null;
		this.apellido = null;
		this.edad = 0;
		this.eleccion = 1;
		this.contrase�a = null;
		this.monedas = 0;
		this.nick = null;
	}
	
	

	public void setEleccion(int eleccion) {
		this.eleccion = eleccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getContrase�a() {
		return contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}

	public int getDinero() {
		return monedas;
	}

	public void setDinero(int dinero) {
		this.monedas = dinero;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}
	
	public int getEleccion(){
		return eleccion;
	}
	
	public String toString() {
		return(this.getNick() + ", " + this.getNombre() + ", " + this.getApellido() + ", " + this.getContrase�a());
	}

}
