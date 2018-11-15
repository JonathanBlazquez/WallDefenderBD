package Registro;

import java.sql.*;
import java.util.ArrayList;


public class BD {
	private static Connection con;
	private static Statement stat;
	private static ResultSet rs;
	
	public static void main(String[] args){
		String sql = "";
		try{
			Class.forName("orgs.sqlite.JDBC");	
			con = DriverManager.getConnection("jdbc:sqlite:test.db");
			stat = con.createStatement();
			
			try{
				sql = "Create table USUARIO("
						+ "NICK String,"
						+ "PASSWORD String,"
						+ "NOMBRE String,"
						+ "APELLIDOS String,"
						+ "EDAD int,"
						+ "MONEDAS int"
						+ "ELECCION int"
						+ ")";
				stat.executeUpdate(sql);
				
				sql = "Create table INVENTARIO("
						+ "NICK String,"
						+ "P1 int,"
						+ "P2 int,"
						+ "P3 int"
						+ ")";
				stat.executeUpdate(sql);
				
			sql = "Create table RANKING("
						+ "NICK String,"
						+ "PUNTUACION int"
						+ ")";
			stat.executeUpdate(sql);
			
				
			}catch(SQLException e){}
			
				sql = "select * from usuario where nick = 'Administrador'";
				rs = stat.executeQuery(sql);
				if(!rs.next()){
					sql = "Insert into USUARIO values ('Administrador', 'Administrador','','','','99999',1)";
					stat.executeUpdate(sql);
					sql = "Insert into Inventario values ('Administrador', '1', '1', '1')";
					stat.executeUpdate(sql);
				}
	
				rs.close();
				con.close();
				stat.close();

				
		}catch(Exception e){
			System.out.println("ERROR EN SQL: " + sql );
			e.printStackTrace();
		}
		
	}
	
	
	public static String insertarUsuario(Usuario u){
		PreparedStatement stmt;
		String nick = u.getNick();
		String pass = u.getContraseña();
		String nom = u.getNombre();
		String apell = u.getApellido();
		int edad = u.getEdad();
		int monedas = u.getDinero();
		int eleccion = u.getEleccion();
		
		try{
			stmt = con.prepareStatement("INSERT INTO USUARIO VALUES(?,?,?,?,?,?,?)");
			stmt.setString(1, nick);
			stmt.setString(2, pass);
			stmt.setString(3, nom);
			stmt.setString(4, apell);
			stmt.setInt(5, edad);
			stmt.setInt(6, monedas);
			stmt.setInt(7, eleccion);
			stmt.executeUpdate();
			
			return "Usuario añadido";
			
		}catch(Exception e){
			return "Error en la creación de Usuario.";
		}
	}
	
	
	public static Usuario selectUsuario(String nick){
		PreparedStatement stmt;
		Usuario user;
		try{
			stmt = con.prepareStatement("SELECT * FROM USUARIO" + "WHERE NICK = ?");
			stmt.setString(1, nick);
			rs = stmt.executeQuery();
			String nik = rs.getString(1);
			String pass = rs.getString(2);
			String nom = rs.getString(3);
			String apell = rs.getString(4);
			int edad = rs.getInt(5);
			int monedas = rs.getInt(6);
			int eleccion = rs.getInt(7);
			
			user = new Usuario(nom, apell, edad, pass, monedas, nik, eleccion);
			
			return user;
			
		}catch(Exception e){
			return null;
		}
	}
	
	public static String selectPass(String nick){
		PreparedStatement stmt;
		String pass;
		try{
			stmt = con.prepareStatement("SELECT PASSWORD FROM USUARIO" + "WHERE NICK = ?");
			stmt.setString(1, nick);
			rs = stmt.executeQuery();
			pass = rs.getString(1);
			
			return pass;
			
		}catch(Exception e){
			return null;
		}
	}
	
	public static String modificarMonedas(Usuario u){
		PreparedStatement stmt;
		String nick = u.getNick();
		int monedas = u.getDinero();
		
		try{
			stmt = con.prepareStatement("UPDATE USUARIO SET MONEDAS = ?" + "WHERE NICK = ?");
			stmt.setInt(1, monedas);
			stmt.setString(2, nick);
			stmt.executeUpdate();
			
			return "Usuario actualizado";
			
		}catch(Exception e){
			return "Error de actualización de Usuario";
		}
	}
	
	public static int selectMonedas(Usuario u){
		PreparedStatement stmt;
		String nick = u.getNick();
		
		try{
			stmt = con.prepareStatement("SELECT MONEDAS FROM USUARIO" + "WHERE NICK = ?");
			stmt.setString(1, nick);
			rs = stmt.executeQuery();
			int monedas = Integer.parseInt((String) rs.getObject(1));
			return monedas;
			
		}catch(Exception e){
			return 0;
		}
	}
	
	public static int selectEleccion(Usuario u){
		PreparedStatement stmt;
		String nick = u.getNick();
		
		try{
			stmt = con.prepareStatement("SELECT ELECCION FROM USUARIO" + "WHERE NICK = ?");
			stmt.setString(1, nick);
			rs = stmt.executeQuery();
			int eleccion = rs.getInt(1);
			return eleccion;
			
		}catch(Exception e){
			return 1;
		}
	}
	
	
	public static ArrayList<String> getNicks(){
		PreparedStatement stmt;
		ArrayList<String> users;
		
		try {
			stmt = con.prepareStatement("SELECT NICK FROM USUARIO");
			rs = stmt.executeQuery();
			
			users = (ArrayList<String>) rs.getArray("NICK");
			return users;
			
		}catch(Exception e) {
			return null;
		}
	} 
	
	public static String modificarEleccion(Usuario u){
		PreparedStatement stmt;
		int eleccion = u.getEleccion();
		String nick = u.getNick();
		
		try{
			stmt = con.prepareStatement("UPDATE USUARIO SET ELECCION = ?" + "WHERE NICK = ?");
			stmt.setInt(1, eleccion);
			stmt.setString(2, nick);
			stmt.executeUpdate();
			
			return "Personaje cambiado";
			
		}catch(Exception e){
			return "Personaje no cambiado";
		}
	}
	
	public static String insertarInventario(Inventario i){
		PreparedStatement stmt;
		String nick = i.getNick();
		int p1 = i.getP1();
		int p2 = i.getP2();
		int p3 = i.getP3();
		
		try{
			stmt = con.prepareStatement("INSERT INTO INVENTARIO VALUES (?, ?, ?, ?)");
			stmt.setString(1, nick);
			stmt.setInt(2, p1);
			stmt.setInt(3, p2);
			stmt.setInt(4, p3);
			stmt.executeUpdate();
			
			return "Inventario añadido.";
			
		}catch(Exception e){
			return "Error en Inventario";
		}
	}
	
	
	public static String modificarInventario(Inventario i){
		PreparedStatement stmt;
		int p1 = i.getP1();
		int p2 = i.getP2();
		int p3 = i.getP3();
		String nick = i.getNick();
		
		try{
			stmt = con.prepareStatement("UPDATE INVENTARIO SET P1 = ?, SET P2 = ?, SET P3 = ?" + "WHERE NICK = ?");
			stmt.setInt(1, p1);
			stmt.setInt(2, p2);
			stmt.setInt(3, p3);
			stmt.setString(4, nick);
			stmt.executeUpdate();
			
			return "Compra validada";
		}catch(Exception e){
			return "Compra no validada";
		}
	}
	
	public static Inventario selectInventario(String nick){
		PreparedStatement stmt;
		Inventario i;
		
		try{
			stmt = con.prepareStatement("SELECT * FROM INVENTARIO" + "WHERE NICK = ?");
			rs = stmt.executeQuery();
			String nik = rs.getString(1);
			int p1 = rs.getInt(2);
			int p2 = rs.getInt(3);
			int p3 = rs.getInt(4);
			
			i = new Inventario(nik, p1, p2, p3);
			
			return i;
			
		}catch(Exception e){
			return null;
		}
	}
	
	
	public static int selectInventarioP1(Inventario i){
		PreparedStatement stmt;
		String nick = i.getNick();
		
		try{
			stmt = con.prepareStatement("SELECT P1 FROM INVENTARIO" + "WHERE NICK = ?");
			stmt.setString(1, nick);
			rs = stmt.executeQuery();
			int p1 = rs.getInt(1);
			return p1;
			
		}catch(Exception e){
			return 0;
		}
	}
	
	public static int selectInventarioP2(Inventario i){
		PreparedStatement stmt;
		String nick = i.getNick();
		
		try{
			stmt = con.prepareStatement("SELECT P2 FROM INVENTARIO" + "WHERE NICK = ?");
			stmt.setString(1, nick);
			rs = stmt.executeQuery();
			int p2 = rs.getInt(1);
			return p2;
			
		}catch(Exception e){
			return 0;
		}
	}
	
	public static int selectInventarioP3(Inventario i){
		PreparedStatement stmt;
		String nick = i.getNick();
		
		try{
			stmt = con.prepareStatement("SELECT P3 FROM INVENTARIO" + "WHERE NICK = ?");
			stmt.setString(1, nick);
			rs = stmt.executeQuery();
			int p3 = rs.getInt(1);
			return p3;
			
		}catch(Exception e){
			return 0;
		}
	}
	
//	
//	public static String insertarRanking(Ranking r){
//		PreparedStatement stmt;
//		String nick = r.getNick();
//		int puntuacion = r.getPuntuacion();
//		
//		try{
//			stmt = con.prepareStatement("INSERT INTO RANKING VALUES (?, ?)");
//			stmt.setString(1, nick);
//			stmt.setInt(2, puntuacion);
//			stmt.executeUpdate();
//			
//			return "Puntuación añadida";
//			
//		}catch(Exception e){
//			return "Puntuación no añadida";
//		}
//	}
//	
//	public static String modificarRanking(Ranking r){
//		PreparedStatement stmt;
//		String nick = r.getNick();
//		int puntuacion = r.getPuntuacion();
//		
//		try{
//			stmt = con.prepareStatement("UPDATE RANKING SET PUNTUACION = ?" + "WHERE NICK = ?");
//			stmt.setInt(1, puntuacion);
//			stmt.setString(2, nick);
//			stmt.executeUpdate();
//			
//			return "Puntuación modificada";
//			
//		}catch(Exception e){
//			return "Puntuación no modificado";
//		}
//	} 
//	
//	public static String[] selectRankingNick(){
//		PreparedStatement stmt;
//		String[] arrayNick = new String[10];
//		try {
//			stmt = con.prepareStatement("SELECT TOP 10 FROM RANKING" + "ORDER BY PUNTUACION DESC");
//			rs = stmt.executeQuery();
//			Array a = rs.getArray("NICK");
//			arrayNick = (String[]) a.getArray();
//			
//			return arrayNick;
//			
//			
//		} catch (Exception e) {
//			arrayNick = null;
//			return arrayNick;
//		}
//	
//	}
//	
//	public static String[] selectRankingPuntuacion(){
//		PreparedStatement stmt;
//		String[] arrayPuntuacion = new String[10];
//		try{
//			stmt = con.prepareStatement("SELECT TOP 10 PUNTUACION FROM RANKING" + "ORDER BY PUNTUACION DESC");
//			rs = stmt.executeQuery();
//			Array a = rs.getArray("PUNTUACION");
//			arrayPuntuacion = (String[]) a.getArray();
//			
//			return arrayPuntuacion;
//			
//			
//		}catch(Exception e){
//			arrayPuntuacion = null;
//			return arrayPuntuacion;
//		}
//	}
//	
//
}
