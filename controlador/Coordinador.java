package controlador;

import java.util.List;
import bd.AdmBaseDatos;
import modelo.Logica;
import modelo.objetos.Persona;
import vista.VentanaAgregarPersona;
import vista.VentanaEditarPersona;
import vista.VentanaPrincipal;

public class Coordinador {

	private Logica logica;
	private AdmBaseDatos administradorBD;
	private VentanaPrincipal vPrincipal;
	private VentanaAgregarPersona vAgregarPersona;
	private VentanaEditarPersona vEditarPersona;
	private boolean hayDosVentanasAbiertas;

	public Coordinador() {
		this.hayDosVentanasAbiertas = false;
	}

	public void setLogica(Logica logica) {
		this.logica = logica;
	}
	
	public void setAdministradorBD(AdmBaseDatos administradorBaseDatos) {
		this.administradorBD = administradorBaseDatos; 
	}

	public void setVentanaPrincipal(VentanaPrincipal ventanaPrincipal) {
		this.vPrincipal = ventanaPrincipal;
	}

	public void setVentanaAgregarPersona(VentanaAgregarPersona ventanaAgreagarPersona) {
		this.vAgregarPersona = ventanaAgreagarPersona;
	}
	
	public void setVentanaEditarPersona(VentanaEditarPersona ventanaEditarPersona) {
		this.vEditarPersona = ventanaEditarPersona;
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
	
	public boolean actualizarPersona(Integer id, String nombre, Integer intDeporte, Integer intMusica, Integer intEspectaculo, Integer intCiencia) {
		boolean actualizadoEnBD = administradorBD.actualizarPersona(new Persona(id, nombre, intDeporte, intMusica, intEspectaculo, intCiencia));
		if(actualizadoEnBD) {
			logica.sincronizarPersonasConBD(administradorBD.obtenerPersonasEnListaPersona());
		}
		return actualizadoEnBD;
	}

	public boolean eliminarPersona(Integer idPersona) {
		boolean eliminadoEnBD =  administradorBD.eliminarPersona(idPersona);
		if(eliminadoEnBD) {
			logica.sincronizarPersonasConBD(administradorBD.obtenerPersonasEnListaPersona());
		}
		return eliminadoEnBD;
	}

	public List<Object[]> obtenerPersonasEnListaObject() {
		return administradorBD.obtenerPersonasEnListaObject();
	}
	
	public List<Persona> obtenerPersonasEnListaPersona() {
		return administradorBD.obtenerPersonasEnListaPersona();
	}
	
	public List<Object[]> obtenerGrupos() {
		return logica.obtenerGrupos();
	}

	public void actualizarDatosInterfaz() {		
		vPrincipal.actualizarDatosInterfaz();
	}
	
	public String cantPersonas() {
		return logica.cantPersonas().toString();
	}
	
	public String promInteresDeportes() {
		return logica.promInteresDeportes().toString();
	}

	public String promInteresMusica() {
		return logica.promInteresMusica().toString();
	}

	public String promInteresEspectaculo() {
		return logica.promInteresEspectaculo().toString();
	}

	public String promInteresCiencia() {
		return logica.promInteresCiencia().toString();
	}
	
}
