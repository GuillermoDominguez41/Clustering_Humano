package modelo.objetos;

public class Arista {
	private Persona persona1, persona2;
	private int indiceSimilaridad;

	public Arista(Persona persona1, Persona persona2) {
		this.persona1 = persona1;
		this.persona2 = persona2;
		this.indiceSimilaridad = persona1.calcularIndiceSimilitud(persona2);
	}

	public Arista(Persona persona1, Persona persona2, int similaridad) {
		this.persona1 = persona1;
		this.persona2 = persona2;
		this.indiceSimilaridad = similaridad;
	}

	public int indiceSimilaridad() {
		return this.indiceSimilaridad;
	}

	public Persona persona1() {
		return this.persona1;
	}

	public Persona persona2() {
		return this.persona2;
	}

	@Override
	public String toString() {
		return "["+ persona1.nombre() + " - " + persona2.nombre() + " - " + indiceSimilaridad + "]";
	}

	@Override
	public boolean equals(Object otroObjeto) {
		if (otroObjeto != null ) {
			if(otroObjeto instanceof Arista) {
				Arista otraArista = (Arista) otroObjeto;
				
				// Comparamos si son iguales las personas que componen la arista actual y la otra arista
				return (persona1().equals(otraArista.persona1) && persona2().equals(otraArista.persona2))
						|| (persona1().equals(otraArista.persona2) && persona2().equals(otraArista.persona1));
			}
		}
		return false;
	}
}
