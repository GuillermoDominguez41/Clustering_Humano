package modelo.objetos;

public class Persona {
	
	private String nombre;
	private Integer InteresDeporte;
	private Integer InteresMusica;
	private Integer InteresEspectaculo;
	private Integer InteresCiencia;
	private Integer rangoMinimo;
	private Integer rangoMaximo;

	public Persona(String Nombre, Integer ID, Integer IM, Integer IE, Integer IC) {
		rangoMinimo = 1;
		rangoMaximo = 5;
		
		if(Nombre==null || ID==null || IM==null || IE==null || IC==null)
			errorParametroNulo();
		else {
			this.nombre = Nombre;
			if (interesValido(ID, IM, IE, IC)) {
				this.InteresMusica = IM;
				this.InteresDeporte = ID;
				this.InteresEspectaculo = IE;
				this.InteresCiencia = IC;
			} else
				errorParametroFueraDeRango();
		}
	}

	// ---------- Validar intereses -------------------------------------------------------
	private boolean interesValido(Integer ID, Integer IM, Integer IE, Integer IC) {
		return rangoInteresValido(ID) && rangoInteresValido(IM) && rangoInteresValido(IE) && rangoInteresValido(IC);
	}

	private boolean rangoInteresValido(Integer interes) {
		return interes >= this.rangoMinimo && interes <= this.rangoMaximo;
	}
	
	// ---------- Calcular indice de similitud --------------------------------------------
    public Integer calcularIndiceSimilitud(Persona otraPersona) {
    	int iDepor = calcularDiferenciaInteres(InteresDeporte, otraPersona.interesDeporte());
    	int iMusic = calcularDiferenciaInteres(InteresMusica, otraPersona.interesMusica());
    	int iEspec = calcularDiferenciaInteres(InteresEspectaculo, otraPersona.interesEspectaculo());
    	int iCienc = calcularDiferenciaInteres(InteresCiencia, otraPersona.interesCiencia());
    	return iDepor+iMusic+iEspec+iCienc;
    }
    
    private Integer calcularDiferenciaInteres(Integer interesP1, Integer interesP2) {
    	return Math.abs(interesP1-interesP2);
    }

	// ---------- Getters y Setters --------------------------------------------------------
	public String nombre() {
		return nombre;
	}

	public Integer interesDeporte() {
		return InteresDeporte;
	}

	public Integer interesMusica() {
		return InteresMusica;
	}

	public Integer interesEspectaculo() {
		return InteresEspectaculo;
	}

	public Integer interesCiencia() {
		return InteresCiencia;
	}
	
	public Integer rangoMinimo() {
		return rangoMinimo;
	}
	
	public Integer rangoMaximo() {
		return rangoMaximo;
	}
	
	
	// ---------- Definir metodos propios para este Objeto ---------------------------------
		
	@Override
	public boolean equals(Object otroObjeto) {
		if (otroObjeto != null || (otroObjeto instanceof Persona)) {
			if (this.hashCode() == otroObjeto.hashCode())
				return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Nombre:"+nombre+" - ID:"+InteresDeporte+" - IM:"+InteresMusica+" - IE:"+InteresEspectaculo+" - IC:"+InteresCiencia; 
	}
	
	// ------------ Errores ----------------------------------------------------------
	private void errorParametroNulo() {
		throw new IllegalArgumentException("Los parametros ingresados no pueden ser nulo o vacio");
	}
	
	private void errorParametroFueraDeRango() {
		throw new IllegalArgumentException("El parametro ingresado se encuentra fuera del rango definido");
	}
	
}
