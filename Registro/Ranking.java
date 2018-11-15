package Registro;

public class Ranking {
	private String nick;
	private int puntuacion;
	
	public Ranking(Usuario u){
		this.nick = u.getNick();
		this.puntuacion = 0;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
}
