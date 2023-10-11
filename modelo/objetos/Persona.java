package modelo.objetos;

public class Persona {

	private static Integer rangoMinimo = 1;
	private static Integer rangoMaximo = 5;
	private String nombre;
	private Integer id, interesDeporte, interesMusica, interesEspectaculo, interesCiencia;

	public Persona(String nombre, Integer intDeporte, Integer intMusica, Integer intEspectaculo, Integer intCiencia) 
	{
		if (nombre == null || intDeporte == null || intMusica == null || intEspectaculo == null || intCiencia == null)
			errorParametroNulo();
		else {
			this.nombre = nombre;
			if (interesValido(intDeporte, intMusica, intEspectaculo, intCiencia)) {
				this.interesDeporte = intDeporte;
				this.interesMusica = intMusica;
				this.interesEspectaculo = intEspectaculo;
				this.interesCiencia = intCiencia;
			} else
				errorParametroFueraDeRango();
		}
	}
	
	public Persona(Integer id, String nombre, Integer intDeporte, Integer intMusica, Integer intEspectaculo, Integer intCiencia) {

		if (id == null || nombre == null || intDeporte == null || intMusica == null || intEspectaculo == null || intCiencia == null)
			errorParametroNulo();
		else {
			this.id = id;
			this.nombre = nombre;
			if (interesValido(intDeporte, intMusica, intEspectaculo, intCiencia)) {
				this.interesDeporte = intDeporte;
				this.interesMusica = intMusica;
				this.interesEspectaculo = intEspectaculo;
				this.interesCiencia = intCiencia;
			} else
				errorParametroFueraDeRango();
		}
	}

	private boolean interesValido(Integer ID, Integer IM, Integer IE, Integer IC) {
		return rangoInteresValido(ID) && rangoInteresValido(IM) && rangoInteresValido(IE) && rangoInteresValido(IC);
	}

	private boolean rangoInteresValido(Integer interes) {
		return interes >= rangoMinimo && interes <= rangoMaximo;
	}
	
	private void errorParametroNulo() {
		throw new IllegalArgumentException("Los parametros ingresados no pueden ser nulo o vacio");
	}

	private void errorParametroFueraDeRango() {
		throw new IllegalArgumentException("El parametro ingresado se encuentra fuera del rango definido");
	}

	public Integer calcularIndiceSimilitud(Persona otraPersona) {
		int iDepor = calcularDiferenciaInteres(interesDeporte, otraPersona.interesDeporte());
		int iMusic = calcularDiferenciaInteres(interesMusica, otraPersona.interesMusica());
		int iEspec = calcularDiferenciaInteres(interesEspectaculo, otraPersona.interesEspectaculo());
		int iCienc = calcularDiferenciaInteres(interesCiencia, otraPersona.interesCiencia());
		return iDepor + iMusic + iEspec + iCienc;
	}

	private Integer calcularDiferenciaInteres(Integer interesP1, Integer interesP2) {
		return Math.abs(interesP1 - interesP2);
	}

	public Integer id() {
		return id;
	}
	
	public String nombre() {
		return nombre;
	}

	public Integer interesDeporte() {
		return interesDeporte;
	}

	public Integer interesMusica() {
		return interesMusica;
	}

	public Integer interesEspectaculo() {
		return interesEspectaculo;
	}

	public Integer interesCiencia() {
		return interesCiencia;
	}

	public static Integer rangoMinimo() {
		return rangoMinimo;
	}

	public static Integer rangoMaximo() {
		return rangoMaximo;
	}

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
		return "[Nombre:" + nombre + " - ID:" + interesDeporte + " - IM:" + interesMusica + " - IE:" + interesEspectaculo
				+ " - IC:" + interesCiencia+"]";
	}

}
