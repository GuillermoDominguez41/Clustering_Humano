package vista;

import modelo.objetos.Arista;
import modelo.objetos.GrafoPersona;
import modelo.objetos.Persona;

public class ConsolaPrueba {

	public static void main(String[] args) {
		GrafoPersona grafo = new GrafoPersona();

		Persona persona1 = new Persona("Matias", 1, 1, 1, 1);
		Persona persona2 = new Persona("Carlos", 1, 1, 1, 1);
		Persona persona3 = new Persona("Lionel", 5, 2, 3, 4);
		Persona persona4 = new Persona("Andres", 4, 4, 3, 4);
		Persona persona5 = new Persona("Manuel", 1, 5, 1, 2);
		Persona persona6 = new Persona("Manuel", 1, 5, 1, 2);

		System.out.println(persona5.equals(persona6));
		System.out.println(persona5.hashCode());
		System.out.println(persona6.hashCode());

		grafo.agregarPersona(persona1);
		grafo.agregarPersona(persona2);
		grafo.agregarPersona(persona3);
		grafo.agregarPersona(persona4);
		grafo.agregarPersona(persona5);
		grafo.agregarPersona(persona6);

		Arista a = new Arista(persona1, persona2);
		
		System.out.println(a.indiceSimilaridad());

		grafo.calcularCaminoMinimo();
	}
}