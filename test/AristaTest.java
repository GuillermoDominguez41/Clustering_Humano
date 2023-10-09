package test;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

import modelo.objetos.*;

class AristaTest {

	Persona matias = new Persona("Matias", 4, 5, 4, 1);
	Persona lucas = new Persona("Lucas", 2, 2, 1, 4);
	Persona juan = new Persona("Juan", 2, 2, 1, 4);
	Persona eduardo = new Persona("Eduardo", 2, 2, 1, 4);

	Arista arista_Matias_Lucas = new Arista(matias, lucas);
	Arista arista_Lucas_Matias = new Arista(lucas, matias);
	Arista arista_Juan_Eduardo = new Arista(juan, eduardo);

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
		assertFalse(arista_Matias_Lucas.equals(matias));
	}

	@Test
	public void mismoIndiceSimilaridad() {
		Persona pedro = new Persona("Pedro", 4, 5, 4, 1);
		Persona pablo = new Persona("Pablo", 4, 5, 4, 1);
		Arista arista_Pedro_Pablo = new Arista(pedro, pablo);
		assertEquals(0, arista_Pedro_Pablo.indiceSimilaridad());
	}

	@Test
	public void distintoIndiceSimilaridad() {
		Persona pedro = new Persona("Pedro", 4, 5, 4, 1);
		Persona pablo = new Persona("Pablo", 4, 1, 4, 1);
		Arista arista_Pedro_Pablo = new Arista(pedro, pablo);
		assertNotEquals(0, arista_Pedro_Pablo.indiceSimilaridad());
	}

}
