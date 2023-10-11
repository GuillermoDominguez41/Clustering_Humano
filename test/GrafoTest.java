package test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import modelo.objetos.Arista;
import modelo.objetos.GrafoPersona;
import modelo.objetos.Persona;

class GrafoTest {

	GrafoPersona grafoPrueba = new GrafoPersona();

	// Metodos de prueba.

	@Before
	void agregarPersonaGrafo() {
		grafoPrueba.agregarPersona(new Persona("Matias", 4, 5, 4, 1));
		grafoPrueba.agregarPersona(new Persona("Lucas", 2, 2, 1, 4));
		grafoPrueba.agregarPersona(new Persona("Juan", 2, 2, 1, 4));
		grafoPrueba.agregarPersona(new Persona("Eduardo", 2, 2, 1, 4));
	}

	@Test
	void cantidadPersonasCaminoMinimo() {

		agregarPersonaGrafo();
		grafoPrueba.calcularCaminoMinimo();
		List<Arista> agm = grafoPrueba.caminoMinimo();

		// La respuesta es 3 porque esperamos el mayor indice de la lista.
		assertEquals(3, agm.size());
	}

}
