package test;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;
import modelo.objetos.Persona;

class PersonaTest {
	Persona personaTest_Lucas = new Persona("Lucas", 4, 5, 1, 1);
	Persona personaTest_Marcos = new Persona("Marcos", 4, 5, 1, 1);
	Persona personaTest_Pedro = new Persona("Pedro", 4, 5, 2, 1);
	Integer valorIndiceSimilaridad0 = 0;

	@Test
	public void personaIgualASiMisma() {
		assertTrue(personaTest_Lucas.equals(personaTest_Lucas));
	}

	@Test
	public void personaDistintaDeOtra() {
		assertFalse(personaTest_Lucas.equals(personaTest_Marcos));
	}

	@Test
	public void personaDistintaDeObjetoVacio() {
		Object otroObjeto = null;
		assertFalse(personaTest_Lucas.equals(otroObjeto));
	}

	@Test
	public void personaDistintaDeOtroObjeto() {
		Object otroObjeto = new Object();
		assertFalse(personaTest_Lucas.equals(otroObjeto));
	}

	@Test
	public void indiceSimilitudIguales() {

		assertEquals(personaTest_Lucas.calcularIndiceSimilitud(personaTest_Marcos),
				personaTest_Marcos.calcularIndiceSimilitud(personaTest_Lucas));
	}

	@Test
	public void indiceSimilitudIgualA0() {

		assertEquals(valorIndiceSimilaridad0, personaTest_Lucas.calcularIndiceSimilitud(personaTest_Marcos));

	}

	@Test
	public void indiceSimilitudDistintos() {
		assertNotEquals(personaTest_Lucas.calcularIndiceSimilitud(personaTest_Pedro), valorIndiceSimilaridad0);

	}

	@Test
	public void crearPersonaParametroNulo() {

		try {
			@SuppressWarnings("unused")
			Persona test_personaNula = new Persona(null, 4, 5, 1, 1);

		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Los parametros ingresados no pueden ser nulo o vacio");
		}
	}

	@Test
	public void crearPersonaInteresFueraRangoMaximo() {
		try {
			@SuppressWarnings("unused")
			Persona test_personaInteresFueraRango = new Persona("Pepito", 4, 5, Persona.rangoMaximo() + 1, 1);

		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "El parametro ingresado se encuentra fuera del rango definido");
		}
	}

	@Test
	public void crearPersonaInteresFueraRangoMinimo() {
		try {
			@SuppressWarnings("unused")
			Persona test = new Persona("Pepito", 4, 5, Persona.rangoMinimo() - 1, 1);

		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "El parametro ingresado se encuentra fuera del rango definido");
		}
	}

}
