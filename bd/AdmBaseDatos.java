package bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.objetos.Persona;

public class AdmBaseDatos {
	private Conexion cx;
	private static String tablaPersona = "Personas";
	private static String colPersona_id = "id";
	private static String colPersona_Nombre = "nombre";
	private static String colPersona_InteresDeporte = "interesDeporte";
	private static String colPersona_InteresMusica = "interesMusica";
	private static String colPersona_InteresEspectaculo = "interesEspectaculo";
	private static String colPersona_InteresCiencia = "interesCiencia";

	public AdmBaseDatos() {
		cx = new Conexion();
	}

	public boolean insertarPersona(Persona nuevaPersona) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("INSERT INTO " + tablaPersona + " VALUES(null,?,?,?,?,?)");
			ps.setString(1, nuevaPersona.nombre());
			ps.setInt(2, nuevaPersona.interesDeporte());
			ps.setInt(3, nuevaPersona.interesMusica());
			ps.setInt(4, nuevaPersona.interesEspectaculo());
			ps.setInt(5, nuevaPersona.interesCiencia());
			ps.executeUpdate();
			cx.desconectar();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Object[]> obtenerPersonasEnListaObject() {
		List<Object[]> lista = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = cx.conectar().prepareStatement("SELECT * FROM " + tablaPersona);
			rs = ps.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt(colPersona_id);
				String nombre = rs.getString(colPersona_Nombre);
				Integer intDeporte = rs.getInt(colPersona_InteresDeporte);
				Integer intMusica = rs.getInt(colPersona_InteresMusica);
				Integer intEspectaculo = rs.getInt(colPersona_InteresEspectaculo);
				Integer intCiencia = rs.getInt(colPersona_InteresCiencia);

				lista.add(new Object[] { id, nombre, intDeporte, intMusica, intEspectaculo, intCiencia });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Persona> obtenerPersonasEnListaPersona() {
		List<Persona> lista = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = cx.conectar().prepareStatement("SELECT * FROM " + tablaPersona);
			rs = ps.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt(colPersona_id);
				String nombre = rs.getString(colPersona_Nombre);
				Integer intDeporte = rs.getInt(colPersona_InteresDeporte);
				Integer intMusica = rs.getInt(colPersona_InteresMusica);
				Integer intEspectaculo = rs.getInt(colPersona_InteresEspectaculo);
				Integer intCiencia = rs.getInt(colPersona_InteresCiencia);
				
				lista.add(new Persona(id, nombre, intDeporte, intMusica, intEspectaculo, intCiencia));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public boolean eliminarPersona(int Id) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("DELETE FROM " + tablaPersona + " WHERE id=?");
			ps.setInt(1, Id);
			ps.executeUpdate();
			cx.desconectar();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean actualizarPersona(Persona personaPorActualizar) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("UPDATE " + tablaPersona + " SET " + 
					colPersona_Nombre + "=?," + 
					colPersona_InteresDeporte + "=?," + 
					colPersona_InteresMusica  + "=?," + 
					colPersona_InteresEspectaculo + "=?," + 
					colPersona_InteresCiencia  + "=? WHERE id=?");
			ps.setString(1, personaPorActualizar.nombre());
			ps.setInt(2, personaPorActualizar.interesDeporte());
			ps.setInt(3, personaPorActualizar.interesMusica());
			ps.setInt(4, personaPorActualizar.interesEspectaculo());
			ps.setInt(5, personaPorActualizar.interesCiencia());
			ps.setInt(6, personaPorActualizar.id());
			ps.executeUpdate();
			cx.desconectar();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}