package Modelos;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.JFrame;

public class Ventana extends JFrame implements WindowListener {

	public Ventana(String titulo) {

		super(titulo);
		setSize(500, 500);
		addWindowListener(this);
		setBackground(Color.white);
	}

	

	public void windowActivated(WindowEvent arg0) {}

	
	public void windowClosed(WindowEvent arg0) {}

	
	public void windowClosing(WindowEvent arg0) {
		
		System.exit(0);
	}

	
	public void windowDeactivated(WindowEvent arg0) {}

	
	public void windowDeiconified(WindowEvent arg0) {}

	
	public void windowIconified(WindowEvent arg0) {}

	
	public void windowOpened(WindowEvent arg0) {}

}
