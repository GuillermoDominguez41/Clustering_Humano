package vista;

import modelo.objetos.GrafoPersona;
import modelo.objetos.Persona;

public class ConsolaPrueba {

	public static void main(String[] args) {
		GrafoPersona grafo = new GrafoPersona();

		Persona persona1 = new Persona("Matias", 4, 5, 4, 1);
		Persona persona2 = new Persona("Carlos", 5, 1, 3, 5);
		Persona persona3 = new Persona("Lionel", 5, 2, 3, 4);
		Persona persona4 = new Persona("Andres", 4, 4, 3, 4);
		Persona persona5 = new Persona("Daniel", 2, 1, 4, 1);
		Persona persona6 = new Persona("Manuel", 1, 5, 1, 2);
		
		grafo.agregarPersona(persona1);
		grafo.agregarPersona(persona2);
		grafo.agregarPersona(persona3);
		grafo.agregarPersona(persona4);
		grafo.agregarPersona(persona5);
		grafo.agregarPersona(persona6);
	
		grafo.calcularCaminoMinimo();
	}
}