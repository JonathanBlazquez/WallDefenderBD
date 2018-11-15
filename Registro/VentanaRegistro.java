package Registro;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Font;
import java.applet.AudioClip;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.awt.Window.Type;
import javax.swing.JPasswordField;

public class VentanaRegistro extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JFrame frmRegistroUsuario;
	public JTextField textFieldNombre;
	public JTextField textFieldApell;
	public JTextField textFieldEdad;
	public JTextField textFieldNick;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistro window = new VentanaRegistro();
					window.frmRegistroUsuario.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaRegistro() {
		
		frmRegistroUsuario = new JFrame();
		frmRegistroUsuario.setType(Type.POPUP);
		frmRegistroUsuario.setTitle("Registro Usuario");
		frmRegistroUsuario.setForeground(Color.ORANGE);
		frmRegistroUsuario.setBounds(500, 200, 800, 500);
		frmRegistroUsuario.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Recursos/icono.png")));
		frmRegistroUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRegistroUsuario.getContentPane().setLayout(new GridLayout(7, 0, 0, 0));
		frmRegistroUsuario.setResizable(false);// Para no maximizar la
												// pantalla,siempre el mismo
												// tamaño
		JPanel panelNom = new JPanel();
		panelNom.setBackground(Color.PINK);
		frmRegistroUsuario.setVisible(true);
		frmRegistroUsuario.getContentPane().add(panelNom);
		panelNom.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Bauhaus 93", Font.PLAIN, 26));
		lblNombre.setBounds(45, 13, 173, 40);
		panelNom.add(lblNombre);
		textFieldApell = new JTextField();
		textFieldApell.addKeyListener(new KeyAdapter() {
			
			public void keyReleased(KeyEvent arg0) {
				
				String nombre = textFieldApell.getText();
				nombre = nombre.toUpperCase();
				for (int i = 0; i < nombre.length(); i++) {
					if (nombre.charAt(i) < 'A' || nombre.charAt(i) > 'Z') {
						JOptionPane.showMessageDialog(frmRegistroUsuario, "Caracteres no permitidos");
						textFieldApell.setText(textFieldApell.getText().substring(0, textFieldApell.getText().length() - 1));

					}
				}

			}
		});
		textFieldApell.setFont(new Font("Bauhaus 93", Font.PLAIN, 26));

		textFieldNombre = new JTextField();
		textFieldNombre.setFont(new Font("Bauhaus 93", Font.PLAIN, 26));

		textFieldNombre.addKeyListener(new KeyAdapter() {
			
			public void keyReleased(KeyEvent arg0) {
				String nombre = textFieldNombre.getText();
				nombre = nombre.toUpperCase();
				for (int i = 0; i < nombre.length(); i++) {
					if (nombre.charAt(i) < 'A' || nombre.charAt(i) > 'Z') {
						JOptionPane.showMessageDialog(frmRegistroUsuario, "Caracteres no permitidos");
						textFieldNombre.setText(textFieldNombre.getText().substring(0, textFieldNombre.getText().length() - 1));
						//u.setNombre(nombre);
					}
				}

			}
		});
		textFieldNombre.setBounds(280, 13, 452, 35);
		panelNom.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		JPanel panelApell = new JPanel();
		frmRegistroUsuario.getContentPane().add(panelApell);
		panelApell.setLayout(null);

		JLabel lblApellidos = new JLabel("Apellido");
		lblApellidos.setFont(new Font("Bauhaus 93", Font.PLAIN, 26));
		lblApellidos.setBounds(45, 13, 107, 40);
		panelApell.add(lblApellidos);

		textFieldApell.setEditable(true);

		textFieldApell.setColumns(10);
		textFieldApell.setBounds(280, 13, 452, 35);
		panelApell.add(textFieldApell);

		JPanel panelEdad = new JPanel();
		frmRegistroUsuario.getContentPane().add(panelEdad);
		panelEdad.setLayout(null);

		JLabel lblNewLabel = new JLabel("Edad");
		lblNewLabel.setFont(new Font("Bauhaus 93", Font.PLAIN, 26));
		lblNewLabel.setBounds(45, 23, 114, 16);
		panelEdad.add(lblNewLabel);

		textFieldEdad = new JTextField();
		textFieldEdad.addKeyListener(new KeyAdapter() {
				
			public void keyReleased(KeyEvent e) {
					try {
						int	k = Integer.parseInt(textFieldEdad.getText());
						if (k>100||k<0) {
							JOptionPane.showMessageDialog(frmRegistroUsuario, "La edad solo puede estar entre 0 y 100 años");
								textFieldEdad.setText(textFieldEdad.getText().substring(0, textFieldEdad.getText().length() - 1));
						}
						
					}catch(Exception ex) {
		
						for (int i = 0; i < textFieldEdad.getText().length(); i++) {
							if (Character.isDigit(textFieldEdad.getText().charAt(i)) == false) {
								JOptionPane.showMessageDialog(frmRegistroUsuario, "Caracteres no permitidos");
								textFieldEdad.setText(textFieldEdad.getText().substring(0, textFieldEdad.getText().length() - 1));
							}
						}	
					}
					
			}
		});
		textFieldEdad.setFont(new Font("Bauhaus 93", Font.PLAIN, 26));
		textFieldEdad.setColumns(10);
		textFieldEdad.setBounds(280, 13, 452, 35);
		panelEdad.add(textFieldEdad);

		JPanel panelNick = new JPanel();
		frmRegistroUsuario.getContentPane().add(panelNick);
		panelNick.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Nick");
		lblNewLabel_1.setFont(new Font("Bauhaus 93", Font.PLAIN, 26));
		lblNewLabel_1.setBounds(45, 21, 56, 16);
		panelNick.add(lblNewLabel_1);

		textFieldNick = new JTextField();
		textFieldNick.setFont(new Font("Bauhaus 93", Font.PLAIN, 26));
		textFieldNick.setColumns(10);
		textFieldNick.setBounds(280, 14, 452, 35);
		panelNick.add(textFieldNick);
		
		JPanel panelPass = new JPanel();
		frmRegistroUsuario.getContentPane().add(panelPass);
		panelPass.setLayout(null);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setFont(new Font("Bauhaus 93", Font.PLAIN, 26));
		lblContrasea.setBounds(45, 21, 176, 16);
		panelPass.add(lblContrasea);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(283, 13, 451, 35);
		panelPass.add(passwordField_1);

		JPanel panelPass2 = new JPanel();
		frmRegistroUsuario.getContentPane().add(panelPass2);
		panelPass2.setLayout(null);

		JLabel lblRepetirContrasea = new JLabel("Repetir contrase\u00F1a");
		lblRepetirContrasea.setFont(new Font("Bauhaus 93", Font.PLAIN, 26));
		lblRepetirContrasea.setBounds(45, 23, 237, 25);
		panelPass2.add(lblRepetirContrasea);

		passwordField = new JPasswordField();
		passwordField.setBounds(283, 13, 451, 35);
		panelPass2.add(passwordField);

		JPanel panelButton = new JPanel();
		frmRegistroUsuario.getContentPane().add(panelButton);
		panelButton.setLayout(null);

		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				char[] pass = passwordField.getPassword();
				char[] pass2 = passwordField_1.getPassword();

				if (textFieldNombre.getText().isEmpty() || textFieldApell.getText().isEmpty() || textFieldEdad.getText().isEmpty()
						|| textFieldNick.getText().isEmpty() || passwordField_1.getPassword().toString().isEmpty()
						|| passwordField.getPassword().toString().isEmpty()) {
					JOptionPane.showMessageDialog(frmRegistroUsuario, "Tienes que rellenar todos los espacios");

				} else {
					if (Arrays.equals(pass, pass2)) {
						String str = textFieldEdad.getText();
						Usuario user = new Usuario(textFieldNombre.getText(),textFieldApell.getText(),Integer.parseInt(str),passwordField_1.getPassword().toString(),0,textFieldNick.getText(),1);
						Inventario invent = new Inventario(user);
						
						BD.insertarUsuario(user);
						BD.insertarInventario(invent);
					
						new VentanaPrincipal(user, invent);
						frmRegistroUsuario.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(frmRegistroUsuario, "Verifica que las contraseñas son iguales");
					}
				}
			}
		});
		btnRegistrarse.setBackground(new Color(154, 205, 50));
		btnRegistrarse.setForeground(new Color(0, 0, 0));
		btnRegistrarse.setFont(new Font("Snap ITC", Font.PLAIN, 26));
		btnRegistrarse.setBounds(126, 13, 258, 40);
		panelButton.add(btnRegistrarse);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Usuario u = new Usuario();
				Inventario i = new Inventario();
				new VentanaPrincipal(u, i);
				frmRegistroUsuario.dispose();

			}
		});
		btnCancelar.setBackground(new Color(255, 0, 0));
		btnCancelar.setForeground(new Color(0, 0, 0));
		btnCancelar.setFont(new Font("Snap ITC", Font.PLAIN, 26));
		btnCancelar.setBounds(514, 13, 209, 40);
		panelButton.add(btnCancelar);
		
		panelNom.setBackground(Color.PINK);
		panelApell.setBackground(Color.PINK);
		panelEdad.setBackground(Color.PINK);
		panelNick.setBackground(Color.PINK);
		panelPass.setBackground(Color.PINK);
		panelPass2.setBackground(Color.PINK);
		panelButton.setBackground(Color.PINK);
	
	}
}