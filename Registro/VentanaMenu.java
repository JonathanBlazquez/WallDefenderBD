package Registro;

import javax.swing.*;
import javax.swing.border.LineBorder;

import Modelos.Coordenada;
import Modelos.NaveGrafica;
import Modelos.PanelFG;
import Modelos.RectanguloGrafico;
import Modelos.TextoGrafico;
import Modelos.Ventana;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.awt.SystemColor;

public class VentanaMenu extends JFrame {
	private JButton btnTienda;
	private JButton btnJugar;
	private JButton btnCharacter;
	private JButton btnSalir;
	private JButton btnvol;
	private JFrame menu;
	private JLabel lblNewLabel;
	private JButton button;
	private JLabel lblNewLabel_1;
	private JLabel label;
	private JLabel label_1;
	private static Usuario user;
	private static Inventario invent;
	public AudioClip musicafondo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMenu window = new VentanaMenu(user, invent);
					window.menu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaMenu(Usuario u, Inventario i) {
		VentanaMenu.user = u;
		VentanaMenu.invent = i;
		Initialize();
	}

	public void Initialize() {

		//musicafondo = java.applet.Applet.newAudioClip(getClass().getResource("/Recursos/menu.wav"));
		//musicafondo.play();
		
		final AudioClip pasarventana;
		pasarventana = java.applet.Applet.newAudioClip(getClass().getResource("/Recursos/pasarventana.wav"));
		

		final AudioClip atras;
		atras = java.applet.Applet.newAudioClip(getClass().getResource("/Recursos/atras.wav"));
		try {
			user.getEleccion();
		}catch(NullPointerException e) {
			user.setEleccion(1);
		}
		menu = new JFrame();
		menu.setVisible(true);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setResizable(false);
		menu.getContentPane().setLayout(null);
		menu.setBounds(400, 200, 1000, 600);

		btnJugar = new JButton("Jugar");
		btnJugar.setForeground(new Color(255, 0, 0));
		btnJugar.setFont(new Font("Brush Script MT", Font.BOLD, 65));
		btnJugar.setBounds(268, 71, 419, 132);
		btnJugar.setOpaque(false);
		btnJugar.setContentAreaFilled(false);
		btnJugar.setBorder(null);

		menu.getContentPane().add(btnJugar);

		btnTienda = new JButton("Tienda");
		btnTienda.setForeground(new Color(255, 0, 0));
		btnTienda.setFont(new Font("Brush Script MT", Font.BOLD, 65));
		btnTienda.setBounds(268, 214, 419, 129);
		btnTienda.setOpaque(false);
		btnTienda.setContentAreaFilled(false);
		btnTienda.setBorder(null);

		menu.getContentPane().add(btnTienda);
		
		btnCharacter = new JButton("Character");
		btnCharacter.setForeground(new Color(255, 0, 0));
		btnCharacter.setFont(new Font("Brush Script MT", Font.BOLD, 65));
		btnCharacter.setBounds(287, 361, 400, 117);
		btnCharacter.setOpaque(false);
		btnCharacter.setContentAreaFilled(false);
		btnCharacter.setBorder(null);

		menu.getContentPane().add(btnCharacter);

		btnSalir = new JButton();
		btnSalir.setIcon(new ImageIcon(getClass().getResource("/Recursos/Flecha.png")));
		btnSalir.setOpaque(false);
		btnSalir.setContentAreaFilled(false);
		btnSalir.setBounds(new Rectangle(1160, 685, 100, 75));
		btnSalir.setBorderPainted(false);
		btnSalir.setBounds(836, 488, 85, 41);
		menu.getContentPane().add(btnSalir);

		btnTienda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//musicafondo.stop();
				pasarventana.play();
				VentanaTienda tienda = new VentanaTienda(user, invent);
				menu.setVisible(false);
			}

		});
		
		class Hilo extends Thread {
			public void run() {
				Juego j = new Juego(user, invent);
			}
		};

		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Hilo t = new Hilo();
				t.start();
				
				pasarventana.play();
				menu.setVisible(false);
				//musicafondo.stop();
				
			}
		});
		
		btnCharacter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//musicafondo.stop();	
				pasarventana.play();
				VentanaCharacter car = new VentanaCharacter(user, invent);
				menu.setVisible(false);
			}
		});


		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try {
					VentanaPrincipal vp = new VentanaPrincipal();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				menu.setVisible(false);
				//musicafondo.stop();
				atras.play();
				
				
			}
		});


		
		final JButton lblNewLabel_1 = new JButton("");
		final JButton lblNewLabel_2 = new JButton("");
		lblNewLabel_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel_2.setVisible(true);
				lblNewLabel_2.setEnabled(true);
				lblNewLabel_1.setVisible(false);
				lblNewLabel_1.setEnabled(false);
			}
		});

		lblNewLabel_1.setOpaque(false);
		lblNewLabel_1.setFont(new Font("Bauhaus 93", Font.PLAIN, 60));
		lblNewLabel_1.setContentAreaFilled(false);
		lblNewLabel_1.setBorder(null);

		lblNewLabel_1.setBounds(36, 464, 77, 53);

		ImageIcon fot = new ImageIcon(getClass().getResource("/Recursos/von.png"));
		Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lblNewLabel_1.getWidth(), lblNewLabel_1.getHeight(),
				Image.SCALE_DEFAULT));
		lblNewLabel_1.setIcon(icono);
		this.repaint();
		menu.getContentPane().add(lblNewLabel_1);

		lblNewLabel_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel_1.setVisible(true);
				lblNewLabel_1.setEnabled(true);
				lblNewLabel_2.setVisible(false);
				lblNewLabel_2.setEnabled(false);

			}
		});

		lblNewLabel_2.setOpaque(false);
		lblNewLabel_2.setFont(new Font("Bauhaus 93", Font.PLAIN, 60));
		lblNewLabel_2.setContentAreaFilled(false);
		lblNewLabel_2.setBorder(null);

		lblNewLabel_2.setBounds(36, 464, 77, 53);

		ImageIcon fot5 = new ImageIcon(getClass().getResource("/Recursos/voff.png"));
		Icon icono5 = new ImageIcon(fot5.getImage().getScaledInstance(lblNewLabel_2.getWidth(),
				lblNewLabel_2.getHeight(), Image.SCALE_DEFAULT));
		lblNewLabel_2.setIcon(icono5);
		this.repaint();
		menu.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_2.setVisible(false);
		
		
		JLabel lbl1 = new JLabel("");
		lbl1.setBounds(268, 226, 400, 117);
		ImageIcon fot2 = new ImageIcon(getClass().getResource("/Recursos/sword1.png"));
		Icon icono2 = new ImageIcon(
				fot2.getImage().getScaledInstance(lbl1.getWidth(), lbl1.getHeight(), Image.SCALE_DEFAULT));
		lbl1.setIcon(icono2);
		this.repaint();
		menu.getContentPane().add(lbl1);

		JLabel lbl2 = new JLabel("");
		lbl2.setBounds(258, 362, 417, 132);
		ImageIcon fot3 = new ImageIcon(getClass().getResource("/Recursos/sword1.png"));
		Icon icono3 = new ImageIcon(
				fot3.getImage().getScaledInstance(lbl2.getWidth(), lbl2.getHeight(), Image.SCALE_DEFAULT));
		lbl2.setIcon(icono3);
		this.repaint();
		menu.getContentPane().add(lbl2);

		JLabel lbl3 = new JLabel("");
		lbl3.setBounds(258, 83, 398, 116);
		ImageIcon fot4 = new ImageIcon(getClass().getResource("/Recursos/sword1.png"));
		Icon icono4 = new ImageIcon(fot4.getImage().getScaledInstance(lbl3.getWidth(), lbl3.getHeight(), Image.SCALE_DEFAULT));
		lbl3.setIcon(icono4);
		this.repaint();
		menu.getContentPane().add(lbl3);

		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(getClass().getResource("/Recursos/fondom.jpg")));
		background.setBounds(0, 0, 994, 565);
		menu.getContentPane().add(background);

	}

}