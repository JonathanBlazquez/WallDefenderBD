package Registro;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class VentanaCharacter extends JFrame{
	private JLabel labelDinero;
	private JButton btnArco;
	private JButton btnBaston;
	private JButton btnMonedas;
	private JLabel lblChar;
	private JLabel lblArco;
	private JLabel lblBaston;
	private JLabel iconoMonedas;
	private JButton btnSalir;
	private JFrame character;
	private JLabel lblOrogorn_1;
	private JLabel lblGondolf;
	private JPanel panel_4;
	private JPanel panel_5;
	private JLabel lblNewLabel;
	private JPanel panel_7;
	private JLabel lblVolverMenu;
	private JButton btnNewButton;
	private JLabel lblOrogorn;
	private static Usuario user;
	private static Inventario invent;
	private int monedas;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCharacter window = new VentanaCharacter(user, invent);
					window.character.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public VentanaCharacter(Usuario u, Inventario i){
		this.user = u;
		this.invent = i;
		Initialize();
	}
	
	public void Initialize(){
		monedas = user.getDinero();
		final AudioClip atras;
		atras = java.applet.Applet.newAudioClip(getClass().getResource("/Recursos/atras.wav"));
		//final AudioClip musicatienda;
		//musicatienda = java.applet.Applet.newAudioClip(getClass().getResource("/Recursos/musicatienda.wav"));
		//musicatienda.play();
		character = new JFrame("");
		character.setLocation(400, 200);
		character.setSize(1000, 600);
		character.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		character.setResizable(false);
		character.getContentPane().setLayout(null);
		character.setVisible(true);
		labelDinero = new JLabel("Monedas: " + user.getDinero());
		labelDinero.setForeground(new Color(218, 165, 32));
		labelDinero.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		labelDinero.setBounds(697, 8, 238, 78);
		character.getContentPane().add(labelDinero);
		
		iconoMonedas = new JLabel("");
		iconoMonedas.setIcon(new ImageIcon(getClass().getResource("/Recursos/MonedaOro.png")));
		iconoMonedas.setBounds(926, 22, 46, 40);
		character.getContentPane().add(iconoMonedas);
		
		btnArco = new JButton("");
		btnArco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(invent.getP2() == 1){
					user.setEleccion(2);
					JOptionPane.showMessageDialog(getContentPane(), "Personaje Legolos elegido.");
				}else{
					System.out.println("Personaje bloqueado");
				}
			}
		});
		
		btnArco.setBorder(new LineBorder(new Color(204, 153, 51), 3, true));
		btnArco.setIcon(new ImageIcon(getClass().getResource("/Recursos/arco.png")));
		btnArco.setContentAreaFilled(false);
		btnArco.setOpaque(false);
		btnArco.setBounds(43, 162, 200, 200);
		character.getContentPane().add(btnArco);
		
		btnBaston = new JButton("");
		btnBaston.setBorder(new LineBorder(new Color(204, 153, 51), 3, true));
		btnBaston.setIcon(new ImageIcon(getClass().getResource("/Recursos/baston.png")));
		btnBaston.setContentAreaFilled(false);
		btnBaston.setOpaque(false);
		btnBaston.setBounds(697, 162, 200, 200);
		character.getContentPane().add(btnBaston);
		
		btnBaston.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(invent.getP3() == 1){
					user.setEleccion(3);
					JOptionPane.showMessageDialog(getContentPane(), "Personaje Gondolf elegido.");
				}else{
					System.out.println("Personaje bloqueado");
				}
			}
		});
		
		btnSalir = new JButton();
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMenu menu = new VentanaMenu(user, invent);
				character.setVisible(false);
				atras.play();
				//musicatienda.stop();
			}
		});
		btnSalir.setIcon(new ImageIcon(getClass().getResource("/Recursos/Flecha.png")));
		btnSalir.setOpaque(false);
		btnSalir.setContentAreaFilled(false);
		btnSalir.setBounds(new Rectangle(1160, 685, 100, 75));
		btnSalir.setBorderPainted(false);
		btnSalir.setBounds(878, 494, 133, 78);
		character.getContentPane().add(btnSalir);
		
		lblChar = new JLabel("PERSONAJES");
		lblChar.setBackground(Color.BLACK);
		lblChar.setBorder(new LineBorder(new Color(204, 153, 51), 5, true));
		lblChar.setHorizontalAlignment(SwingConstants.CENTER);
		lblChar.setForeground(Color.ORANGE);
		lblChar.setFont(new Font("Myriad Pro Light", Font.PLAIN, 50));
		lblChar.setBounds(257, 13, 405, 73);
		character.getContentPane().add(lblChar);
		
		if(invent.getP1() == 1){
			lblOrogorn = new JLabel("EQUIPAR");
		}else{
			lblOrogorn = new JLabel("BLOQUEADO");
		}
		
		if(invent.getP2() == 1){
			lblArco = new JLabel("EQUIPAR");
		}else{
			lblArco = new JLabel("BLOQUEADO");
		}	
		

		if(invent.getP3() == 1){
			lblBaston = new JLabel("EQUIPAR");
		}else{
			lblBaston = new JLabel("BLOQUEADO");
		}	
		
		lblArco.setBorder(new LineBorder(new Color(204, 153, 51), 3, true));
		lblArco.setHorizontalAlignment(SwingConstants.CENTER);
		lblArco.setForeground(Color.ORANGE);
		lblArco.setFont(new Font("Myriad Pro Light", Font.PLAIN, 25));
		lblArco.setBounds(43, 361, 200, 54);
		character.getContentPane().add(lblArco);
		
		lblBaston.setBorder(new LineBorder(new Color(204, 153, 51), 3, true));
		lblBaston.setHorizontalAlignment(SwingConstants.CENTER);
		lblBaston.setForeground(Color.ORANGE);
		lblBaston.setFont(new Font("Myriad Pro Light", Font.PLAIN, 25));
		lblBaston.setBounds(697, 361, 200, 54);
		character.getContentPane().add(lblBaston);
		
		lblOrogorn.setBackground(Color.BLUE);
		lblOrogorn.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrogorn.setForeground(Color.ORANGE);
		lblOrogorn.setFont(new Font("Myriad Pro Light", Font.PLAIN, 25));
		lblOrogorn.setBorder(new LineBorder(new Color(204, 153, 51), 3, true));
		lblOrogorn.setBounds(367, 361, 200, 54);
		character.getContentPane().add(lblOrogorn);
		
		JLabel lblLegolos = new JLabel("LEGOLOS");
		lblLegolos.setHorizontalAlignment(SwingConstants.CENTER);
		lblLegolos.setForeground(Color.ORANGE);
		lblLegolos.setFont(new Font("Myriad Pro Light", Font.PLAIN, 25));
		lblLegolos.setBorder(new LineBorder(new Color(204, 153, 51), 3, true));
		lblLegolos.setBounds(43, 112, 200, 54);
		character.getContentPane().add(lblLegolos);
		
		JButton btnOrogorn = new JButton("");
		btnOrogorn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(invent.getP1() == 1){
					user.setEleccion(1);
					JOptionPane.showMessageDialog(getContentPane(), "Personaje Orogorn elegido.");
				}else{
					System.out.println("Personaje bloqueado");
				}
			}
		});
		btnOrogorn.setIcon(new ImageIcon(getClass().getResource("/Recursos/dagas.png")));
		btnOrogorn.setOpaque(false);
		btnOrogorn.setContentAreaFilled(false);
		btnOrogorn.setBorder(new LineBorder(new Color(204, 153, 51), 3, true));
		btnOrogorn.setBounds(367, 162, 200, 200);
		character.getContentPane().add(btnOrogorn);
		
		lblOrogorn_1 = new JLabel("OROGORN");
		lblOrogorn_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrogorn_1.setForeground(Color.ORANGE);
		lblOrogorn_1.setFont(new Font("Myriad Pro Light", Font.PLAIN, 25));
		lblOrogorn_1.setBorder(new LineBorder(new Color(204, 153, 51), 3, true));
		lblOrogorn_1.setBounds(367, 112, 200, 54);
		character.getContentPane().add(lblOrogorn_1);
		
		lblGondolf = new JLabel("GONDOLF");
		lblGondolf.setHorizontalAlignment(SwingConstants.CENTER);
		lblGondolf.setForeground(Color.ORANGE);
		lblGondolf.setFont(new Font("Myriad Pro Light", Font.PLAIN, 25));
		lblGondolf.setBorder(new LineBorder(new Color(204, 153, 51), 3, true));
		lblGondolf.setBounds(697, 112, 200, 54);
		character.getContentPane().add(lblGondolf);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(42, 111, 200, 55);
		character.getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		panel_1.setBounds(367, 112, 200, 55);
		character.getContentPane().add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.BLACK);
		panel_2.setBounds(697, 112, 200, 55);
		character.getContentPane().add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 0, 0));
		panel_3.setBounds(43, 361, 200, 55);
		character.getContentPane().add(panel_3);
		
		panel_4 = new JPanel();
		panel_4.setBackground(Color.BLACK);
		panel_4.setBounds(367, 361, 200, 55);
		character.getContentPane().add(panel_4);
		
		panel_5 = new JPanel();
		panel_5.setBackground(Color.BLACK);
		panel_5.setBounds(697, 361, 200, 55);
		character.getContentPane().add(panel_5);
		
		panel_7 = new JPanel();
		panel_7.setBackground(new Color(184, 134, 11));
		panel_7.setForeground(Color.BLACK);
		panel_7.setBounds(257, 13, 405, 73);
		character.getContentPane().add(panel_7);
		
		lblVolverMenu = new JLabel("Volver Menu");
		lblVolverMenu.setFont(new Font("Myriad Pro Light", Font.PLAIN, 21));
		lblVolverMenu.setForeground(Color.RED);
		lblVolverMenu.setBounds(867, 471, 161, 33);
		character.getContentPane().add(lblVolverMenu);
		ImageIcon fot = new ImageIcon(getClass().getResource("/Recursos/masmonedas.png"));
		this.repaint();
		
		
		
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(getClass().getResource("/Recursos/tienday.jpg")));
		background.setBounds(0, 0, 994, 565);
		character.getContentPane().add(background);
		
		
	}
	
}

