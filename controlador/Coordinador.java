package controlador;

import java.util.List;
import modelo.Logica;
import vista.VentanaAgregarPersona;
import vista.VentanaPrincipal;

public class Coordinador {

	private Logica logica;
	private VentanaPrincipal vPrincipal;
	private VentanaAgregarPersona vAgregarPersona;
	private boolean hayDosVentanasAbiertas;

	public Coordinador() {
		hayDosVentanasAbiertas = false;
	}

	public void setLogica(Logica l) {
		logica = l;
	}

	public void setVentanaPrincipal(VentanaPrincipal vP) {
		vPrincipal = vP;
	}

	public void setVentanaAgregarPersona(VentanaAgregarPersona vAP) {
		vAgregarPersona = vAP;
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

	public Integer obtenerRangoMinimo() {
		return logica.rangoMinimo();
	}

	public Integer obtenerRangoMaximo() {
		return logica.rangoMaximo();
	}

	public void agregarPersona(String nombre, int interesDeporte, int interesMusica, int interesEspectaculo,
			int interesCiencia) {
		logica.agregarPersona(nombre, interesDeporte, interesMusica, interesEspectaculo, interesCiencia);
	}

	public List<Object[]> obtenerPersonas() {
		return logica.obtenerPersonas();
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

//	public void generarAristasEntreTodos() {
//		logica.generarAristasEntreTodos();
//	}
//
//	public List<String> generarGrupo() {
//		return logica.generarGrupo();
//	}
}
