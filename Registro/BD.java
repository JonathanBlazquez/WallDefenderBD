package Registro;

import java.sql.*;
import java.util.ArrayList;



public class BD {
	private static Connection con;
	private static Statement stmt;
	
	public static void main(String[] args) {
		initBD();
	}
	
	
	public static Connection initBD(){
		ResultSet rs;
		String sql = "";
		try{
			Class.forName("org.sqlite.JDBC");	
			con = DriverManager.getConnection("jdbc:sqlite:PruebaA");
			stmt = con.createStatement();
			
			try{
				sql = "Create table USUARIO (NICK String, PASSWORD String, NOMBRE String, APELLIDOS String, EDAD int, MONEDAS int, ELECCION int)";
				stmt.executeUpdate(sql);
				
				sql = "Create table INVENTARIO (NICK String, P1 int, P2 int, P3 int)";
				stmt.executeUpdate(sql);
				
				sql = "Create table RANKING (NICK String, PUNTUACION int)";
				stmt.executeUpdate(sql);
			
				sql = "select * from usuario where nick = 'Administrador'";
				rs = stmt.executeQuery(sql);
				if(!rs.next()){
					sql = "Insert into USUARIO values ('Administrador', 'Administrador','','','','9999',1)";
					stmt.executeUpdate(sql);
					sql = "Insert into Inventario values ('Administrador', '1', '1', '1')";
					stmt.executeUpdate(sql);
					
					System.out.println("Administrador introducido.");
				}
				
				System.out.println("BD creada");
				
				return con;
				
			}catch(SQLException e){
				System.out.println("Error en la creación de BD. Base de datos ya creada.");
				return con;
			}
			
	
		}catch(Exception e){
			System.out.println("ERROR EN SQL: " + sql );
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	public static int insertarUsuario(Usuario u) throws SQLException{
		String stmtSQL;
		ArrayList<String> nicks = BD.getNicks();
		int cont = 0;
		
		while(cont < nicks.size()) {
			if(u.getNick().equals(nicks.get(cont))) {
				System.out.println("El usuario ya existe.");
				return 0;
			}
			cont++;
		}
		
		try{
			stmtSQL = "insert into USUARIO values(" + 
					"'" + u.getNick() + "'," +
					"'" + u.getContraseña() + "'," +
					"'" + u.getNombre() + "'," +
					"'" + u.getApellido() + "'," +
					u.getEdad() + "," +
					u.getDinero() + "," +
					u.getEleccion() +
					")";
			stmt.executeUpdate(stmtSQL);
			
			/*
			stmt.setString(1, nick);
			stmt.setString(2, pass);
			stmt.setString(3, nom);
			stmt.setString(4, apell);
			stmt.setInt(5, edad);
			stmt.setInt(6, monedas);
			stmt.setInt(7, eleccion);
			stmt.executeUpdate();
			*/
			System.out.println("Usuario añadido");
			return 1;
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println( "Error en la creación de Usuario.");
			return -1;
		}
	}
	
	
	public static Usuario selectUsuario(String nick){
		Usuario user;
		PreparedStatement stat;
		try{
			stat = con.prepareStatement("select * from USUARIO where NICK= ?");
			stat.setString(1, nick);
			ResultSet r = stat.executeQuery();
			String nik = r.getString(1);
			String pass = r.getString(2);
			String nom = r.getString(3);
			String apell = r.getString(4);
			int edad = r.getInt(5);
			int monedas = r.getInt(6);
			int eleccion = r.getInt(7);
			
			user = new Usuario(nom, apell, edad, pass, monedas, nik, eleccion);
			
			return user;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static String selectPass(String nick){
		String pass;
		PreparedStatement stmt;
		ResultSet r = null;
		try{
			stmt = con.prepareStatement("select PASSWORD from USUARIO where NICK= ?");
			stmt.setString(1, nick);
			r = stmt.executeQuery();
			pass = r.getString(1);
			
			return pass;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static String modificarMonedas(Usuario u){
		PreparedStatement stmt;
		String nick = u.getNick();
		int monedas = u.getDinero();
		
		try{
			stmt = con.prepareStatement("update USUARIO set MONEDAS= ? where NICK= ?");
			stmt.setInt(1, monedas);
			stmt.setString(2, nick);
			stmt.executeUpdate();
			
			return "Usuario actualizado";
			
		}catch(Exception e){
			return "Error de actualización de Usuario";
		}
	}
	
	public static int selectMonedas(Usuario u){
		ResultSet rs;
		PreparedStatement stmt;
		String nick = u.getNick();
		
		try{
			stmt = con.prepareStatement("select MONEDAS from USUARIO where NICK= ?");
			stmt.setString(1, nick);
			rs = stmt.executeQuery();
			int monedas = rs.getInt(1);
			
			return monedas;
			
		}catch(Exception e){
			return 0;
		}
	}
	
	public static int selectEleccion(Usuario u){
		ResultSet rs;
		PreparedStatement stmt;
		String nick = u.getNick();
		
		try{
			stmt = con.prepareStatement("select ELECCION from USUARIO WHERE NICK= ?");
			stmt.setString(1, nick);
			rs = stmt.executeQuery();
			int eleccion = rs.getInt(1);
			return eleccion;
			
		}catch(Exception e){
			return 1;
		}
	}
	
	
	public static ArrayList<String> getNicks() throws SQLException{
		ResultSet rs;
		PreparedStatement stmt;
		ArrayList<String> lista = new ArrayList<String>();
		stmt = con.prepareStatement("select NICK from USUARIO");
		rs = stmt.executeQuery();
		while(rs.next()) {
			lista.add(rs.getString("NICK"));
		}
		return lista;
	} 
	
	public static String modificarEleccion(Usuario u){
		PreparedStatement stmt;
		int eleccion = u.getEleccion();
		String nick = u.getNick();
		
		try{
			stmt = con.prepareStatement("update USUARIO set ELECCION= ? where NICK= ?");
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
			stmt = con.prepareStatement("insert into INVENTARIO values (?, ?, ?, ?)");
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
			stmt = con.prepareStatement("update INVENTARIO set P1= ?, set P2= ?, set P3= ? where NICK= ?");
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
		ResultSet rs;
		PreparedStatement stmt;
		Inventario i;
		
		try{
			stmt = con.prepareStatement("select * from INVENTARIO where NICK= ?");
			stmt.setString(1, nick);
			rs = stmt.executeQuery();
			String nik = rs.getString(1);
			int p1 = rs.getInt(2);
			int p2 = rs.getInt(3);
			int p3 = rs.getInt(4);			
			i = new Inventario(nik, p1, p2, p3);

			return i;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static int selectInventarioP1(Inventario i){
		ResultSet rs;
		PreparedStatement stmt;
		String nick = i.getNick();
		
		try{
			stmt = con.prepareStatement("select P1 from INVENTARIO where NICK= ?");
			stmt.setString(1, nick);
			rs = stmt.executeQuery();
			int p1 = rs.getInt(1);
			return p1;
			
		}catch(Exception e){
			return 0;
		}
	}
	
	public static int selectInventarioP2(Inventario i){
		ResultSet rs;
		PreparedStatement stmt;
		String nick = i.getNick();
		
		try{
			stmt = con.prepareStatement("select P2 from INVENTARIO where NICK= ?");
			stmt.setString(1, nick);
			rs = stmt.executeQuery();
			int p2 = rs.getInt(1);
			return p2;
			
		}catch(Exception e){
			return 0;
		}
	}
	
	public static int selectInventarioP3(Inventario i){
		ResultSet rs;
		PreparedStatement stmt;
		String nick = i.getNick();
		
		try{
			stmt = con.prepareStatement("select P3 from INVENTARIO where NICK= ?");
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
	
	public static Statement reiniciarBD(Connection con) {
		try {
			Statement statement = con.createStatement();
			statement.executeUpdate("drop table if exists USUARIO");
			statement.executeUpdate("drop table if exists INVENTARIO");
			con = initBD();
			return con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
