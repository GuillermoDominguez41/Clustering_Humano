package test;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;
import modelo.objetos.Persona;

class PersonaTest {
	Persona persona = new Persona("PersonaPrueba", 4, 5, 1, 1);

	@Test
	public void equalsMismaPersona() {
		assertTrue(persona.equals(persona));
	}
	
	@Test
	public void equalsObjetoVacio() {
		Object otroObjeto = null;
		assertFalse(persona.equals(otroObjeto));
	}
	
	@Test
	public void equalsOtroObjeto() {
		Object otroObjeto = new Object();
		assertFalse(persona.equals(otroObjeto));
	}
	
	@Test
	public void equalsOtraPersonaDistinta() {
		Persona otraPersona = new Persona("PersonaPrueba", 4, 5, 1, 1);
		assertFalse(persona.equals(otraPersona));
		
	}	
}
