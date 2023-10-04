package modelo.objetos;

public class Persona {

	private Integer id;
	private String nombre;
	private Integer InteresDeporte;
	private Integer InteresMusica;
	private Integer InteresEspectaculo;
	private Integer InteresCiencia;
	private static Integer rangoMinimo = 1;
	private static Integer rangoMaximo = 5;

	public Persona(String nombre, Integer intDeporte, Integer intMusica, Integer intEspectaculo, Integer intCiencia) {

		if (nombre == null || intDeporte == null || intMusica == null || intEspectaculo == null || intCiencia == null)
			errorParametroNulo();
		else {
			this.nombre = nombre;
			if (interesValido(intDeporte, intMusica, intEspectaculo, intCiencia)) {
				this.InteresDeporte = intDeporte;
				this.InteresMusica = intMusica;
				this.InteresEspectaculo = intEspectaculo;
				this.InteresCiencia = intCiencia;
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
				this.InteresDeporte = intDeporte;
				this.InteresMusica = intMusica;
				this.InteresEspectaculo = intEspectaculo;
				this.InteresCiencia = intCiencia;
			} else
				errorParametroFueraDeRango();
		}
	}

	// ---------- Validar intereses
	// -------------------------------------------------------
	private boolean interesValido(Integer ID, Integer IM, Integer IE, Integer IC) {
		return rangoInteresValido(ID) && rangoInteresValido(IM) && rangoInteresValido(IE) && rangoInteresValido(IC);
	}

	private boolean rangoInteresValido(Integer interes) {
		return interes >= rangoMinimo && interes <= rangoMaximo;
	}

	// ---------- Calcular indice de similitud
	// --------------------------------------------
	public Integer calcularIndiceSimilitud(Persona otraPersona) {
		int iDepor = calcularDiferenciaInteres(InteresDeporte, otraPersona.interesDeporte());
		int iMusic = calcularDiferenciaInteres(InteresMusica, otraPersona.interesMusica());
		int iEspec = calcularDiferenciaInteres(InteresEspectaculo, otraPersona.interesEspectaculo());
		int iCienc = calcularDiferenciaInteres(InteresCiencia, otraPersona.interesCiencia());
		return iDepor + iMusic + iEspec + iCienc;
	}

	private Integer calcularDiferenciaInteres(Integer interesP1, Integer interesP2) {
		return Math.abs(interesP1 - interesP2);
	}

	// ---------- Getters y Setters
	// --------------------------------------------------------
	public Integer id() {
		return id;
	}
	
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

	public static Integer rangoMinimo() {
		return rangoMinimo;
	}

	public static Integer rangoMaximo() {
		return rangoMaximo;
	}

	// ---------- Definir metodos propios para este Objeto
	// ---------------------------------

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
		return "Nombre:" + nombre + " - ID:" + InteresDeporte + " - IM:" + InteresMusica + " - IE:" + InteresEspectaculo
				+ " - IC:" + InteresCiencia;
	}

	// ------------ Errores
	// ----------------------------------------------------------
	private void errorParametroNulo() {
		throw new IllegalArgumentException("Los parametros ingresados no pueden ser nulo o vacio");
	}

	private void errorParametroFueraDeRango() {
		throw new IllegalArgumentException("El parametro ingresado se encuentra fuera del rango definido");
	}

}
