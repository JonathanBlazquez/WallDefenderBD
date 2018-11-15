package Registro;

public class Inventario {
	private String nick;
	private int p1;
	private int p2;
	private int p3;
	
	public Inventario(Usuario u){
		this.nick = u.getNick();
		this.p1 = 1;
		this.p2 = 0;
		this.p3 = 0;
	}
	
	public Inventario(String nick, int p1, int p2, int p3){
		this.nick = nick;
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
	}
	
	public Inventario(){
		this.nick = null;
		this.p1 = 0;
		this.p2 = 0;
		this.p3 = 0;
	}

	public int getP1() {
		return p1;
	}

	public void setP1(int p1) {
		this.p1 = p1;
	}

	public int getP2() {
		return p2;
	}

	public void setP2(int p2) {
		this.p2 = p2;
	}

	public int getP3() {
		return p3;
	}

	public void setP3(int p3) {
		this.p3 = p3;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}
	
	
	
	
}
