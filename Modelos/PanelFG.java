/**
 * Logica del juego.Movimiento , Colision , Enemigos , Fin de la partida etc...
 * @author Jonathan Blazquez y Daniel Diaz
 * @version 1.0
 */

package Modelos;

import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Registro.Inventario;
import Registro.Juego;
import Registro.Usuario;
import Registro.VentanaMenu;

public class PanelFG extends JPanel implements KeyListener {
	
	
	Usuario user;//Usuario
	Inventario invent;//Inventario

	public JPanel PanelEmergente;
	
	
	int nMuertos;//Cantidad de enemigos destruidos

	
	/**
	 * Calcular un numero semialeatorio
	 * @param Max
	 * @param Min
	 * @return
	 */
	public static int Aleatorio(int Max, int Min) {
		return (int) (Math.random() * (Max - Min));
	}

	
	
	
	ArrayList v;
	ArrayList ast = new ArrayList();

	NaveGrafica nave;
	Coordenada movimientoIzq = new Coordenada(-25, 0);//movimiento hacia la izquierda
	Coordenada movimientoDer = new Coordenada(25, 0);//movimiento hacia la derecha
	Coordenada movimientoNulo = new Coordenada(0, 0);//sin movimiento

	Boolean FinDeJuego = true;//true se sigue jugando , false se termina juego

	int ContadorAsteroides = 5;//cantidad de enemigos

	TextoGrafico puntos;//Puntuacion
	TextoGrafico vidas;//Vidas
	TextoGrafico Final;//Texto al perder

	int Score;//puntuacion
	int Vidas = 3;//vida inicial

	int Mov = 2;//movimiento de enemigo inicia
	int MAXAST = 5;//cantidad maxima de enemigos iniciales

	public PanelFG(ArrayList vectordeO, Usuario u, Inventario i) {
		this.v = vectordeO;
		this.user = u;
		this.invent = i;
		this.addKeyListener(this);
		setFocusable(true);
	}

	public void paint(Graphics g) {
		// para que no parpadee
		Dimension d = getSize();
		Image Imagen = createImage(d.width, d.height);
		Graphics buff = Imagen.getGraphics();
		//
		Dibujable dib;
		for (int i = 0; i < v.size(); i++) {

			dib = (Dibujable) v.get(i);
			dib.dibujar(buff);
		}

		g.drawImage(Imagen, 0, 0, null);
	}

	public void update(Graphics g) {

		paint(g);

	}

	public void keyReleased(KeyEvent e) {

		int tecla = e.getKeyCode();
		if (tecla == KeyEvent.VK_LEFT) {
			if (this.nave.getX() <= 0) {
				nave.mover(movimientoNulo);

			} else {
				this.nave.mover(movimientoIzq);//movimiento izq
			}
		}
		if (tecla == KeyEvent.VK_RIGHT) {
			if (this.nave.getX() >= 800) {
				nave.mover(movimientoNulo);
			} else {
				this.nave.mover(movimientoDer);//movimiento der
			}
		}
		if (tecla == KeyEvent.VK_Q) {
		}

	}

	//Sonidos
	//al subir de lvl
	class subirN extends Thread {
		public void run() {
			AudioClip lvlup;
			lvlup = java.applet.Applet.newAudioClip(getClass().getResource("/Recursos/lvlup.wav"));
			lvlup.play();
			
			
		}
	}
	
	//al impactar enemigos
	class impacto extends Thread {
		public void run() {
			AudioClip imp;
			imp = java.applet.Applet.newAudioClip(getClass().getResource("/Recursos/impacto.wav"));
			imp.play();
			
			
		}
	}
	
	public void keyPressed(KeyEvent e) {

		int tecla = e.getKeyCode();
		if (tecla == KeyEvent.VK_LEFT) {
			if (this.nave.getX() <= 0) {
				nave.mover(movimientoNulo);

			} else {
				this.nave.mover(movimientoIzq);//movimiento izq
			}

		}
		if (tecla == KeyEvent.VK_RIGHT) {
			if (this.nave.getX() >= 800) {
				nave.mover(movimientoNulo);
			} else {
				this.nave.mover(movimientoDer);//movimiento der
			}
		}
		if (tecla == KeyEvent.VK_Q) {
			//lanzamientos de balas
			//con orogorn
			if (user.getEleccion() == 1) {
				CirculoGrafico bala = nave.Bala();
				bala.pintar(Color.yellow);
				nave.balas.add(bala);
				v.add(bala);
				
			//con logolos
			} else if (user.getEleccion() == 2) {
				CirculoGrafico bala = nave.Bala();
				bala.pintar(Color.blue);
				nave.balas.add(bala);
				v.add(bala);
				
				
			}
			//con gondolf
			else if (user.getEleccion() == 3) {
				CirculoGrafico bala = nave.Bala();
				bala.pintar(Color.red);
				nave.balas.add(bala);
				v.add(bala);
				
				
			}

		}

	}

	public void keyTyped(KeyEvent e) {
	}

	public void refNave(NaveGrafica n) {
		this.nave = n;
	}

	public void refAst(RectanguloGrafico a, RectanguloGrafico b, RectanguloGrafico c, RectanguloGrafico d,
			RectanguloGrafico e) {

		ast.add(a);
		ast.add(b);
		ast.add(c);
		ast.add(d);
		ast.add(e);
	}

	public void refPuntos(TextoGrafico a) {

		this.puntos = a;

	}

	public void refVidas(TextoGrafico b) {

		this.vidas = b;

	}

	public void refFinal(TextoGrafico c) {

		this.Final = c;

	}
	//Todas las colisiones
	public void Colision() {
		for (int i = 0; i < nave.balas.size(); i++) {

			CirculoGrafico bala = (CirculoGrafico) nave.balas.get(i);
			for (int j = 0; j < ast.size(); j++) {

				RectanguloGrafico aste = (RectanguloGrafico) ast.get(j);
				Coordenada Corbala = new Coordenada(bala.getX(), bala.getY());
				Coordenada derecha = new Coordenada(aste.getX() + 30, aste.getY());
				Coordenada izquierda = new Coordenada(aste.getX() - 15, aste.getY());
				Coordenada medio = new Coordenada(aste.getX(), aste.getY());
				//impacatamos enemigos
				if (Corbala.getX() > izquierda.getX() && Corbala.getX() < derecha.getX()
						&& Corbala.getY() < medio.getY() && Corbala.getY() + 25 > medio.getY()) {
					nMuertos++;
					aste.pintar(Color.black);
					bala.pintar(Color.black);
					bala.setY(2000);
					aste.setY(2000);
					nave.balas.remove(bala);
					ast.remove(aste);
					
					ContadorAsteroides--;
					Score += 5;
					puntos.SetColor(Color.black);
					String NuevoScore = "" + Score;
					TextoGrafico Nrpuntos = new TextoGrafico(NuevoScore, Color.blue, 1700, 850);
					Nrpuntos.setSize(40);
					
					vidas.SetColor(Color.black);
					String nuevaaVidaa = "" + Vidas;
					TextoGrafico Nrvidas = new TextoGrafico(nuevaaVidaa, Color.red, 1700, 550);
					Nrvidas.setSize(40);
					vidas = Nrvidas;
					
					
					
					puntos = Nrpuntos;
					v.add(puntos);
					v.add(vidas);
					impacto imp =new  impacto();
					imp.start();
					

				}//nos impactan
				if ((medio.getY() > 475 && medio.getY() < 550) && (nave.cor1.getX() < medio.getX())
						&& (nave.cor2.getX() > medio.getX())) {

					Score = Score - 5;
					Vidas--;
					System.out.println(Vidas);

					String nuevoScore = "" + Score;
					
					vidas.SetColor(Color.black);
					puntos.SetColor(Color.black);
					String nuevaaVidaa = "" + Vidas;
					TextoGrafico Nrvidas = new TextoGrafico(nuevaaVidaa, Color.red, 1700, 550);
					Nrvidas.setSize(40);
					vidas = Nrvidas;

					TextoGrafico NrPuntos = new TextoGrafico(nuevoScore, Color.blue, 1700, 850);
					NrPuntos.setSize(40);
					puntos = NrPuntos;

					v.add(vidas);
					v.add(puntos);
					ast.remove(aste);
					aste.setY(2000);

					ContadorAsteroides--;

				}

			}

		}

	}
	//Iniciar juego
	public void iniciar() {
		while (FinDeJuego) {

			try {
				if (!nave.balas.isEmpty()) {
					nave.Ciclo();
				}
				for (int i = 0; i < ast.size(); i++) {

					RectanguloGrafico rect = (RectanguloGrafico) ast.get(i);
					rect.Ciclo(Mov);
					if (rect.getY() > 525) {

						int rango = Aleatorio(800, 50);//rango de aparicion de enemigos
						rect.setY(0);
						rect.setX(rango);

						Score = Score - 5;
						Vidas--;
						

						String nuevoScore = "" + Score;
						String nuevaaVidaa = "" + Vidas;
						vidas.SetColor(Color.black);
						puntos.SetColor(Color.black);

						TextoGrafico Nrvidas = new TextoGrafico(nuevaaVidaa, Color.red, 1700, 550);
						Nrvidas.setSize(40);
						vidas = Nrvidas;

						TextoGrafico NrPuntos = new TextoGrafico(nuevoScore, Color.blue, 1700, 850);
						NrPuntos.setSize(40);
						puntos = NrPuntos;

						v.add(vidas);
						v.add(puntos);

					}

				}
				
				//Nivelado
				if (ContadorAsteroides < MAXAST) {

					int rango = Aleatorio(800, 50);
					Coordenada Inicio = new Coordenada(rango, 0);
					RectanguloGrafico nuevo = new RectanguloGrafico(Inicio, 25, 25, Color.green);
					ast.add(nuevo);
					v.add(nuevo);
					nuevo.Ciclo(Mov);
					ContadorAsteroides++;
				}
				//Nivel 1
				int nivel = 1;
				String niveles = "" + nivel;
				TextoGrafico TextoNivel = new TextoGrafico("Nivel", Color.ORANGE, 1700, 950);
				TextoNivel.setSize(60);
				TextoGrafico TextoNrNivel = new TextoGrafico(niveles, Color.pink, 1700, 1050);
				TextoNrNivel.setSize(60);
				v.add(TextoNrNivel);
				v.add(TextoNivel);
				//Nivel 2
				if (Score >= 50 && Score < 100) {
					nivel = 2;
					
					if(Score == 50){
					subirN lvl = new subirN();
					lvl.start();
					}
					
					String NuevoNivel = "" + nivel;
					TextoNrNivel.SetColor(Color.black);
					TextoGrafico NrNivel = new TextoGrafico(NuevoNivel, Color.ORANGE, 1700, 1050);
					NrNivel.setSize(60);
					TextoNrNivel = NrNivel;
					v.add(TextoNrNivel);
					Mov = 3;
					MAXAST = 6;
					for (int i = 0; i < ast.size(); i++) {

						RectanguloGrafico rect = (RectanguloGrafico) ast.get(i);
						rect.pintar(Color.MAGENTA);

					}
				}
				//Nivel 3
				if (Score >= 100 && Score < 200) {
					nivel = 3;
					if(Score == 100){
						subirN lvl = new subirN();
						lvl.start();
						}
					String NuevoNivel = "" + nivel;
					TextoNrNivel.SetColor(Color.black);
					TextoGrafico NrNivel = new TextoGrafico(NuevoNivel, Color.ORANGE, 1700, 1050);
					NrNivel.setSize(60);
					TextoNrNivel = NrNivel;
					v.add(TextoNrNivel);
					Mov = 4;
					MAXAST = 5;
					for (int i = 0; i < ast.size(); i++) {

						RectanguloGrafico rect = (RectanguloGrafico) ast.get(i);
						rect.pintar(Color.BLUE);

					}
				}
				//Nivel 4
				if (Score >= 200 && Score < 300) {
					nivel = 4;
					if(Score == 200){
						subirN lvl = new subirN();
						lvl.start();
						}
					String NuevoNivel = "" + nivel;
					TextoNrNivel.SetColor(Color.black);
					TextoGrafico NrNivel = new TextoGrafico(NuevoNivel, Color.ORANGE, 1700, 1050);
					NrNivel.setSize(60);
					TextoNrNivel = NrNivel;
					v.add(TextoNrNivel);
					Mov = 4;
					MAXAST = 6;
					for (int i = 0; i < ast.size(); i++) {

						RectanguloGrafico rect = (RectanguloGrafico) ast.get(i);
						rect.pintar(Color.orange);

					}
				}
				//Nivel 5
				if (Score >= 300 && Score < 400) {
					nivel = 5;
					if(Score == 300){
						subirN lvl = new subirN();
						lvl.start();
						
						
						}
					String NuevoNivel = "" + nivel;
					TextoNrNivel.SetColor(Color.black);
					TextoGrafico NrNivel = new TextoGrafico(NuevoNivel, Color.ORANGE, 1700, 1050);
					NrNivel.setSize(60);
					TextoNrNivel = NrNivel;
					v.add(TextoNrNivel);
					Mov = 5;
					MAXAST = 6;
					for (int i = 0; i < ast.size(); i++) {

						RectanguloGrafico rect = (RectanguloGrafico) ast.get(i);
						rect.pintar(Color.white);

					}
				}
				//Nivel 6
				if (Score >= 400 && Score < 1000000) {
					nivel = 6;
					if(Score == 400){
						subirN lvl = new subirN();
						lvl.start();
						}
					String NuevoNivel = "" + nivel;
					TextoNrNivel.SetColor(Color.black);
					TextoGrafico NrNivel = new TextoGrafico(NuevoNivel, Color.ORANGE, 1700, 1050);
					NrNivel.setSize(60);
					TextoNrNivel = NrNivel;
					v.add(TextoNrNivel);
					Mov = 6;
					MAXAST = 6;
					for (int i = 0; i < ast.size(); i++) {

						RectanguloGrafico rect = (RectanguloGrafico) ast.get(i);
						rect.pintar(Color.YELLOW);

					}
				}
				
				//Volver a jugar 
				class VolverJugar extends Thread
				{
				   public void run()
				   {
					   Juego j= new Juego(user, invent);
				   } 
				}
				
				
				if (Vidas <= 0) {
					FinDeJuego = false;
					user.setDinero(user.getDinero()+(Score/5));
					
					
					// Icon icono = new
					// ImageIcon(getClass().getResource("C:\\Users\\Dani\\Desktop\\Recursos\\Esqueleto"));
					String[] options = { "Volver al Menu", "Ver puntuacion", "Volver a jugar", "Cerrar Juego" };
					int seleccion = JOptionPane.showOptionDialog(PanelEmergente,
							"Es necesario que seleccione una opcion", "Titulo", JOptionPane.DEFAULT_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					//Volver menu
					if (seleccion == 0) {

						VentanaMenu vm = new VentanaMenu(user, invent);
						Window w = SwingUtilities.getWindowAncestor(PanelFG.this);
						w.dispose();
					//Puntuación
					} else if (seleccion == 1) {

						JOptionPane.showMessageDialog(null,"Nick : "+user.getNick()+ "\nTu puntuacion ha sido de " + Score+"\nHas conseguido "+(Score/5)  +" monedas!!"+
								"\nHas matado "+nMuertos+" enemigos");
						
						
						
						VentanaMenu vm = new VentanaMenu(user, invent);
						Window w = SwingUtilities.getWindowAncestor(PanelFG.this);
						w.dispose();
						
						
					} //Rejugar
					else if (seleccion == 2) {
						
						VolverJugar juego = new VolverJugar();
						juego.start();
						Window w = SwingUtilities.getWindowAncestor(PanelFG.this);
						w.dispose();
					
						//Salir
					} else if (seleccion == 3) {

						System.exit(0);
					}

					// v.add(Final);
				}
				Colision();
				Thread.sleep(50);
			} catch (InterruptedException err) {
				System.out.println(err);
			}
			repaint();

		}

	}

}