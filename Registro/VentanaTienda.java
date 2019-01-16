package Registro;

import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaTienda extends JFrame {
	private int monedas;
	private JLabel labelDinero;
	private JButton btnCompraArco;
	private JButton btnCompraBaston;
	private JButton btnMonedas;
	private JLabel lblTienda;
	private JLabel lblPrecioArco;
	private JLabel lblPrecioBaston;
	private JLabel iconoMonedas;
	private JButton btnSalir;
	private JFrame tienda;
	private JLabel lblOrogorn_1;
	private JLabel lblGondolf;
	private JPanel panel_4;
	private JPanel panel_5;
	private JLabel lblNewLabel;
	private JPanel panel_7;
	private JLabel lblVolverMenu;
	private JButton btnNewButton;
	private static Usuario user;
	private static Inventario invent;
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaTienda window = new VentanaTienda(user, invent);
					window.tienda.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public VentanaTienda(Usuario u, Inventario i){
		this.user = u;
		this.invent = i;
		this.monedas = BD.selectMonedas(user);
		Initialize();
	}
	
	
	public void Initialize(){
		
//
//		AudioClip coins;
//		coins = java.applet.Applet.newAudioClip(getClass().getResource("/Recursos/moneda.wav"));
//		
//		AudioClip atras;
//		atras = java.applet.Applet.newAudioClip(getClass().getResource("/Recursos/atras.wav"));
//
//		AudioClip musicatienda;
//		musicatienda = java.applet.Applet.newAudioClip(getClass().getResource("/Recursos/musicatienda.wav"));
//		musicatienda.play();
		tienda = new JFrame("");
		tienda.setLocation(400, 200);
		tienda.setSize(1000, 600);
		tienda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tienda.setResizable(false);
		tienda.getContentPane().setLayout(null);
		tienda.setVisible(true);
		labelDinero = new JLabel("Monedas: " + BD.selectMonedas(user));
		labelDinero.setForeground(new Color(255, 102, 0));
		labelDinero.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		labelDinero.setBounds(697, 0, 215, 78);
		tienda.getContentPane().add(labelDinero);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		tienda.setLocation(dim.width/2-tienda.getSize().width/2, dim.height/2-tienda.getSize().height/2);
		
		iconoMonedas = new JLabel("");
		iconoMonedas.setIcon(new ImageIcon(getClass().getResource("/Recursos/MonedaOro.png")));
		iconoMonedas.setBounds(905, 13, 55, 48);
		tienda.getContentPane().add(iconoMonedas);
		
		btnCompraArco = new JButton("");
		btnCompraArco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(BD.selectMonedas(user) >= 3000 ){
					if(BD.selectInventarioP2(invent)==0) {
						monedas -= 3000;
						invent.setP2(1);
						BD.modificarInventario(invent);
					}else {
						System.out.println("Personaje ya comprado.");
					}
				}else{
					System.out.println("No hay suficientes monedas");
				}
			}
		});
	
		
		btnCompraArco.setBorder(new LineBorder(new Color(204, 153, 51), 3, true));
		btnCompraArco.setIcon(new ImageIcon(getClass().getResource("/Recursos/arco.png")));
		btnCompraArco.setContentAreaFilled(false);
		btnCompraArco.setOpaque(false);
		btnCompraArco.setBounds(43, 162, 200, 200);
		tienda.getContentPane().add(btnCompraArco);
		
		btnCompraBaston = new JButton("");
		btnCompraBaston.setBorder(new LineBorder(new Color(204, 153, 51), 3, true));
		btnCompraBaston.setIcon(new ImageIcon(getClass().getResource("/Recursos/baston.png")));
		btnCompraBaston.setContentAreaFilled(false);
		btnCompraBaston.setOpaque(false);
		btnCompraBaston.setBounds(697, 162, 200, 200);
		tienda.getContentPane().add(btnCompraBaston);
		

		
		btnCompraBaston.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(BD.selectMonedas(user) >= 5000 ){
					if(BD.selectInventarioP2(invent)==0) {
						monedas -= 5000;
						invent.setP3(1);
						BD.modificarInventario(invent);
					}else {
						System.out.println("Personaje ya comprado.");
					}
				}else{
					System.out.println("No hay suficientes monedas");
				}
			}
		});
		
		
		
		btnSalir = new JButton();
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMenu menu = new VentanaMenu(user, invent);
				tienda.setVisible(false);
				//atras.play();
				//musicatienda.stop();
			}
		});
		btnSalir.setIcon(new ImageIcon(getClass().getResource("/Recursos/Flecha.png")));
		btnSalir.setOpaque(false);
		btnSalir.setContentAreaFilled(false);
		btnSalir.setBounds(new Rectangle(1160, 685, 100, 75));
		btnSalir.setBorderPainted(false);
		btnSalir.setBounds(804, 443, 133, 78);
		tienda.getContentPane().add(btnSalir);
		
		lblTienda = new JLabel("TIENDA");
		lblTienda.setBackground(Color.BLACK);
		lblTienda.setBorder(new LineBorder(new Color(204, 153, 51), 5, true));
		lblTienda.setHorizontalAlignment(SwingConstants.CENTER);
		lblTienda.setForeground(Color.ORANGE);
		lblTienda.setFont(new Font("Myriad Pro Light", Font.PLAIN, 50));
		lblTienda.setBounds(257, 13, 405, 73);
		tienda.getContentPane().add(lblTienda);
		
		lblPrecioArco = new JLabel("Monedas: 3000");
		lblPrecioArco.setBorder(new LineBorder(new Color(204, 153, 51), 3, true));
		lblPrecioArco.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecioArco.setForeground(Color.ORANGE);
		lblPrecioArco.setFont(new Font("Old English Text MT", Font.PLAIN, 25));
		lblPrecioArco.setBounds(43, 361, 200, 54);
		tienda.getContentPane().add(lblPrecioArco);
		
		lblPrecioBaston = new JLabel("Monedas: 5000");
		lblPrecioBaston.setBorder(new LineBorder(new Color(204, 153, 51), 3, true));
		lblPrecioBaston.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecioBaston.setForeground(Color.ORANGE);
		lblPrecioBaston.setFont(new Font("Old English Text MT", Font.PLAIN, 25));
		lblPrecioBaston.setBounds(697, 361, 200, 54);
		tienda.getContentPane().add(lblPrecioBaston);
		
		JLabel lblGratis = new JLabel("GRATIS");
		lblGratis.setBackground(Color.BLUE);
		lblGratis.setHorizontalAlignment(SwingConstants.CENTER);
		lblGratis.setForeground(Color.ORANGE);
		lblGratis.setFont(new Font("Myriad Pro Light", Font.PLAIN, 25));
		lblGratis.setBorder(new LineBorder(new Color(204, 153, 51), 3, true));
		lblGratis.setBounds(367, 361, 200, 54);
		tienda.getContentPane().add(lblGratis);
		
		JLabel lblOrogorn = new JLabel("LEGOLOS");
		lblOrogorn.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrogorn.setForeground(Color.ORANGE);
		lblOrogorn.setFont(new Font("Myriad Pro Light", Font.PLAIN, 25));
		lblOrogorn.setBorder(new LineBorder(new Color(204, 153, 51), 3, true));
		lblOrogorn.setBounds(43, 112, 200, 54);
		tienda.getContentPane().add(lblOrogorn);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setIcon(new ImageIcon(getClass().getResource("/Recursos/dagas.png")));
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorder(new LineBorder(new Color(204, 153, 51), 3, true));
		button.setBounds(367, 162, 200, 200);
		tienda.getContentPane().add(button);
		
		lblOrogorn_1 = new JLabel("OROGORN");
		lblOrogorn_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrogorn_1.setForeground(Color.ORANGE);
		lblOrogorn_1.setFont(new Font("Myriad Pro Light", Font.PLAIN, 25));
		lblOrogorn_1.setBorder(new LineBorder(new Color(204, 153, 51), 3, true));
		lblOrogorn_1.setBounds(367, 112, 200, 54);
		tienda.getContentPane().add(lblOrogorn_1);
		
		lblGondolf = new JLabel("GONDOLF");
		lblGondolf.setHorizontalAlignment(SwingConstants.CENTER);
		lblGondolf.setForeground(Color.ORANGE);
		lblGondolf.setFont(new Font("Myriad Pro Light", Font.PLAIN, 25));
		lblGondolf.setBorder(new LineBorder(new Color(204, 153, 51), 3, true));
		lblGondolf.setBounds(697, 112, 200, 54);
		tienda.getContentPane().add(lblGondolf);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(42, 111, 200, 55);
		tienda.getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		panel_1.setBounds(367, 112, 200, 55);
		tienda.getContentPane().add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.BLACK);
		panel_2.setBounds(697, 112, 200, 55);
		tienda.getContentPane().add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 0, 0));
		panel_3.setBounds(43, 361, 200, 55);
		tienda.getContentPane().add(panel_3);
		
		panel_4 = new JPanel();
		panel_4.setBackground(Color.BLACK);
		panel_4.setBounds(367, 361, 200, 55);
		tienda.getContentPane().add(panel_4);
		
		panel_5 = new JPanel();
		panel_5.setBackground(Color.BLACK);
		panel_5.setBounds(697, 361, 200, 55);
		tienda.getContentPane().add(panel_5);
		
		panel_7 = new JPanel();
		panel_7.setBackground(new Color(184, 134, 11));
		panel_7.setForeground(Color.BLACK);
		panel_7.setBounds(257, 13, 405, 73);
		tienda.getContentPane().add(panel_7);
		
		lblVolverMenu = new JLabel("Volver Menu");
		lblVolverMenu.setFont(new Font("Myriad Pro Light", Font.PLAIN, 21));
		lblVolverMenu.setForeground(Color.RED);
		lblVolverMenu.setBounds(833, 507, 149, 33);
		tienda.getContentPane().add(lblVolverMenu);
		
		
		JButton btnMonedas = new JButton("");
		btnMonedas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//coins.play();
				monedas += 1;
				user.setDinero(monedas);
				labelDinero.setText("Monedas: " + monedas);
				BD.modificarMonedas(user);
			}
		});
		

		btnMonedas.setOpaque(false);
		btnMonedas.setFont(new Font("Bauhaus 93", Font.PLAIN, 60));
		btnMonedas.setContentAreaFilled(false);
		btnMonedas.setBorder(null);
		btnMonedas.setBounds(64, 451, 133, 89);
		ImageIcon fot = new ImageIcon(getClass().getResource("/Recursos/masmonedas.png"));
		Icon icono = new ImageIcon(fot.getImage().getScaledInstance(btnMonedas.getWidth(), btnMonedas.getHeight(),
				Image.SCALE_DEFAULT));
		btnMonedas.setIcon(icono);
		this.repaint();
		tienda.getContentPane().add(btnMonedas);
		
		
		
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(getClass().getResource("/Recursos/tienday.jpg")));
		background.setBounds(0, 0, 994, 565);
		tienda.getContentPane().add(background);
		
		
	}
	
	private int getMonedas() {
		try{
			return monedas;
		}
		
		catch(Exception except){
			return 0;
		}
	}
	
	
	
}