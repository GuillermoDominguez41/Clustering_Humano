package test;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

import modelo.objetos.*;

class AristaTest {

	Persona personaTest_Matias = new Persona("Matias", 4, 5, 4, 1);
	Persona personaTest_Lucas = new Persona("Lucas", 2, 2, 1, 4);
	Persona personaTest_Juan = new Persona("Juan", 2, 2, 1, 4);
	Persona personaTest_Eduardo = new Persona("Eduardo", 2, 2, 1, 4);

	Arista arista_Matias_Lucas = new Arista(personaTest_Matias, personaTest_Lucas);
	Arista arista_Lucas_Matias = new Arista(personaTest_Lucas, personaTest_Matias);
	Arista arista_Juan_Eduardo = new Arista(personaTest_Juan, personaTest_Eduardo);

	@Test
	public void AristasIguales() {

		assertTrue(arista_Matias_Lucas.equals(arista_Lucas_Matias));

	}

	@Test
	public void AristasDistintas() {
		assertFalse(arista_Matias_Lucas.equals(arista_Juan_Eduardo));
	}

	@Test
	public void aristaIgualANull() {
		assertFalse(arista_Matias_Lucas.equals(null));
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void aristaOtroObjetoNoArista() {
		assertFalse(arista_Matias_Lucas.equals(personaTest_Matias));
	}

	@Test
	public void mismoIndiceSimilaridad() {

		Persona pedro = new Persona("Pedro", 4, 5, 4, 1);
		Persona pablo = new Persona("Pablo", 4, 5, 4, 1);
		Arista d = new Arista(pedro, pablo);
		assertEquals(0, d.indiceSimilaridad());

	}

	@Test
	public void distintoIndiceSimilaridad() {

		Persona pedro = new Persona("Pedro", 4, 5, 4, 1);
		Persona pablo = new Persona("Pablo", 4, 1, 4, 1);
		Arista d = new Arista(pedro, pablo);
		assertNotEquals(0, d.indiceSimilaridad());

	}

}
