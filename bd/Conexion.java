package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	private Connection cx = null;
	private String rutaBD = System.getProperty("user.dir")+"/src/bd/database.db";
	
	public Connection conectar() {
		try {
			Class.forName("org.sqlite.JDBC");
			cx=DriverManager.getConnection("jdbc:sqlite:"+rutaBD);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return cx;
	}
	
	public void desconectar() {
		try {
			cx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}