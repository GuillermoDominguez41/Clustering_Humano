package modelo.objetos;

// Clase para representar una arista entre personas
public class Arista {
    private Persona persona1, persona2;
    private int indiceSimilaridad;

    public Arista(Persona p1, Persona p2) {
        this.persona1 = p1;
        this.persona2 = p2;
        this.indiceSimilaridad = p1.calcularIndiceSimilitud(p2);
    }
    
    public Arista(Persona p1, Persona p2, int similaridad) {
        this.persona1 = p1;
        this.persona2 = p2;
        this.indiceSimilaridad = similaridad;
    }
    
	// ------------ Getters ---------------------------------------------------------- 
    public int indiceSimilaridad() {
    	return this.indiceSimilaridad;
    }
    
    public Persona persona1() {
    	return this.persona1;
    }
    
    public Persona persona2() {
    	return this.persona2;
    }
    
	// ---------- Definir metodos propios para este Objeto ---------------------------------
	@Override
	public String toString() {
		return persona1.nombre()+" - "+persona2.nombre()+" - "+indiceSimilaridad;
	}
	
	@Override
    public boolean equals(Object otroObjeto) {
        // Verificar si ambos objetos son la misma instancia
        if (this == otroObjeto) {
            return true;
        }
        
        // Verificar si el otro objeto es nulo y si son de la misma clase
        if (otroObjeto == null || getClass() != otroObjeto.getClass()) {
            return false;
        }
        
        // Hacer un casting del otro objeto a la clase actual
        Arista otraArista = (Arista) otroObjeto;
        
		// Comparar los atributos que definen la igualdad
        return ( persona1().equals(otraArista.persona1) && persona2().equals(otraArista.persona2) ) || ( persona1().equals(otraArista.persona2) && persona2().equals(otraArista.persona1) );
    }
}
