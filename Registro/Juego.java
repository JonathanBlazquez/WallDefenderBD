package Registro;

import java.util.ArrayList;

import java.awt.*;
import Modelos.*;
import javax.swing.JLabel;

public class Juego {
	static Usuario user;
	static Inventario invent;
	static int vidas=3;
	static int puntos;
	
	
	public static int Aleatorio(int Max, int Min) {
		return (int) (Math.random() * (Max - Min));

	}
	
	public Juego(Usuario u, Inventario i) {
		this.user = u;
		this.invent = i;
		Initialize();
	}
	public static void Initialize() {
	
	
		Ventana nuestraventana = new Ventana("Wall Defense");
		nuestraventana.setVisible(true);

		
		nuestraventana.setLocation(400, 200);
		ArrayList ArregloDeObjetos = new ArrayList();
		nuestraventana.setVisible(true);
		Coordenada cor1 = new Coordenada(250, 250);
		Coordenada cor2 = new Coordenada(350, 350);

		// NAVEEEE

		Coordenada cor3 = new Coordenada(475, 500);
		Coordenada cor4 = new Coordenada(425, 575);
		Coordenada cor5 = new Coordenada(525, 575);

		NaveGrafica nave = new NaveGrafica(cor3, cor4, cor5, Color.yellow);

		

		TextoGrafico Vidas = new TextoGrafico("Vidas", Color.red, 1700, 400);
		Vidas.setSize(35);
		ArregloDeObjetos.add(Vidas);
		TextoGrafico Puntos = new TextoGrafico("Puntos", Color.blue, 1700, 700);

		Puntos.setSize(30);
		ArregloDeObjetos.add(Puntos);

		TextoGrafico Puntuaje = new TextoGrafico("0", Color.blue, 1700, 850);
		Puntuaje.setSize(40);
		ArregloDeObjetos.add(Puntuaje);

		TextoGrafico Numvidas = new TextoGrafico("3", Color.red, 1700, 550);
		Numvidas.setSize(40);
		ArregloDeObjetos.add(Numvidas);

		TextoGrafico textofinal = new TextoGrafico("FIN", Color.white, 900, 500);
		textofinal.setSize(120);

		int rango1 = Aleatorio(800, 50);
		Coordenada Salida1 = new Coordenada(rango1, -40);
		RectanguloGrafico asteoride1 = new RectanguloGrafico(Salida1, 25, 25, Color.GREEN);
		int rango2 = Aleatorio(800, 50);
		Coordenada Salida2 = new Coordenada(rango2, -25);
		RectanguloGrafico asteoride2 = new RectanguloGrafico(Salida2, 25, 25, Color.GREEN);
		int rango3 = Aleatorio(800, 50);
		Coordenada Salida3 = new Coordenada(rango3, -80);
		RectanguloGrafico asteoride3 = new RectanguloGrafico(Salida3, 25, 25, Color.GREEN);
		int rango4 = Aleatorio(800, 50);
		Coordenada Salida4 = new Coordenada(rango4, -120);
		RectanguloGrafico asteoride4 = new RectanguloGrafico(Salida4, 25, 25, Color.GREEN);
		int rango5 = Aleatorio(800, 50);
		Coordenada Salida5 = new Coordenada(rango5, -200);
		RectanguloGrafico asteoride5 = new RectanguloGrafico(Salida5, 25, 25, Color.GREEN);

		ArregloDeObjetos.add(asteoride1);
		ArregloDeObjetos.add(asteoride2);
		ArregloDeObjetos.add(asteoride3);
		ArregloDeObjetos.add(asteoride4);
		ArregloDeObjetos.add(asteoride5);
		ArregloDeObjetos.add(nave);

		PanelFG nuestroPanel = new PanelFG(ArregloDeObjetos, user, invent);
		

		nuestroPanel.refNave(nave);
		
		nuestroPanel.refAst(asteoride1, asteoride2, asteoride3, asteoride4, asteoride5);
		
		
		nuestroPanel.refFinal(textofinal);
		nuestroPanel.refPuntos(Numvidas);
		nuestroPanel.refVidas(Puntuaje);
		
		
		nuestraventana.getContentPane().add(nuestroPanel);
		nuestroPanel.setLayout(null);
	
		nuestraventana.setSize(1000, 600);
		nuestraventana.setVisible(true);
		nuestraventana.setBackground(Color.black);
		nuestroPanel.iniciar();

	}
}
