package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.Test;
import controlador.Coordinador;
import org.junit.jupiter.api.Test;

import modelo.Logica;
import modelo.objetos.Persona;

class LogicaTest {

	Coordinador coordinador = new Coordinador();

	Logica logica = new Logica();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	List<Object[]> grupos = new ArrayList();

	void agregarPersonaGrafo() {
		logica.agregarPersonaEnGrafo(new Persona("Matias", 4, 5, 4, 1));
		logica.agregarPersonaEnGrafo(new Persona("Lucas", 2, 2, 1, 4));
		logica.agregarPersonaEnGrafo(new Persona("Juan", 2, 2, 1, 4));
		logica.agregarPersonaEnGrafo(new Persona("Eduardo", 2, 2, 1, 4));
		grupos = logica.obtenerGrupos();

	}

	@Test
	void obtenerGrupos() {
		agregarPersonaGrafo();
		assertTrue((grupos.get(0)[1].equals("Eduardo - Matias - Juan")) && grupos.get(1)[1].equals("Lucas"));
	}

	@Test
	void logicaRangoMinimoInteresPersona() {
		assertEquals(logica.rangoMinimo(), Persona.rangoMinimo());
	}

	@Test
	void logicaRangoMaximoInteresPersona() {
		assertEquals(logica.rangoMaximo(), Persona.rangoMaximo());
	}

}