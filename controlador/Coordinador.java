package controlador;

import java.util.List;
import bd.AdministradorBD;
import modelo.Logica;
import modelo.objetos.Persona;
import vista.VentanaAgregarPersona;
import vista.VentanaEditarPersona;
import vista.VentanaPrincipal;

public class Coordinador {

	private Logica logica;
	private AdministradorBD administradorBD;
	private VentanaPrincipal vPrincipal;
	private VentanaAgregarPersona vAgregarPersona;
	private VentanaEditarPersona vEditarPersona;
	private boolean hayDosVentanasAbiertas;

	public Coordinador() {
		hayDosVentanasAbiertas = false;
	}

	public void setLogica(Logica l) {
		logica = l;
	}
	
	public void setAdministradorBD(AdministradorBD admBD) {
		administradorBD = admBD; 
	}

	public void setVentanaPrincipal(VentanaPrincipal vP) {
		vPrincipal = vP;
	}

	public void setVentanaAgregarPersona(VentanaAgregarPersona vAP) {
		vAgregarPersona = vAP;
	}
	
	public void setVentanaEditarPersona(VentanaEditarPersona vEP) {
		vEditarPersona = vEP;
	}
	
	public void mostrarVentanaAgregarPersona() {
		if (hayDosVentanasAbiertas == false) {
			vAgregarPersona.mostrarVentana();
			hayDosVentanasAbiertas = true;
		}
	}

	public void cerrarVentanaAgregarPersona() {
		vAgregarPersona.cerrarVentana();
		hayDosVentanasAbiertas = false;
	}
	
	public void mostrarVentanaEditarPersona(String id, String nombre, String intDeporte, String intMusica, String intEspectaculo, String intCiencia) {
		if (hayDosVentanasAbiertas == false) {
			vEditarPersona.mostrarVentana(id, nombre, intDeporte, intMusica, intEspectaculo, intCiencia);
			hayDosVentanasAbiertas = true;
		}
	}

	public void cerrarVentanaEditarPersona() {
		vEditarPersona.cerrarVentana();
		hayDosVentanasAbiertas = false;
	}

	public Integer obtenerRangoMinimo() {
		return logica.rangoMinimo();
	}

	public Integer obtenerRangoMaximo() {
		return logica.rangoMaximo();
	}

	public void agregarPersona(String nombre, int interesDeporte, int interesMusica, int interesEspectaculo, int interesCiencia) {
		Persona nuevaPersona = new Persona(nombre, interesDeporte, interesMusica, interesEspectaculo, interesCiencia);
		logica.agregarPersonaEnGrafo(nuevaPersona);
		administradorBD.insertarPersona(nuevaPersona);
	}

	public List<Object[]> obtenerPersonas() {
		return administradorBD.consultaPersonas();
	}

	public List<String> obtenerGrupos() {
		return logica.obtenerGrupos();
	}

	public Integer cantPersonas() {
		return logica.cantPersonas();
	}

	public void actualizarTodo() {
		vPrincipal.actualizarTodo();
	}

	public boolean actualizarPersona(Integer id, String nombre, Integer intDeporte, Integer intMusica, Integer intEspectaculo, Integer intCiencia) {
		return administradorBD.actualizarUsuario(new Persona(id, nombre, intDeporte, intMusica, intEspectaculo, intCiencia));
	}

//	public void generarAristasEntreTodos() {
//		logica.generarAristasEntreTodos();
//	}
//
//	public List<String> generarGrupo() {
//		return logica.generarGrupo();
//	}
}
