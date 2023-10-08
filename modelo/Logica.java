package modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import controlador.Coordinador;
import modelo.objetos.Arista;
import modelo.objetos.GrafoPersona;
import modelo.objetos.Persona;

public class Logica {

	private Coordinador coordinador;
	private GrafoPersona grafo;
	private Integer acumInteresDeporte, acumInteresMusica, acumInteresEspectaculos, acumInteresCiencia;

	public Logica(Coordinador coord) {
		grafo = new GrafoPersona();
		coordinador = coord;
		iniciarAcumuladoresIntereses();
		sincronizarPersonasConBD(coordinador.obtenerPersonasEnListaPersona());
	}

	public Logica() {
		grafo = new GrafoPersona();
	}

	private void iniciarAcumuladoresIntereses() {
		acumInteresDeporte = 0;
		acumInteresMusica = 0;
		acumInteresEspectaculos = 0;
		acumInteresCiencia = 0;
	}
	
	public void sincronizarPersonasConBD(List<Persona> listaPersonas) {
		grafo.eliminarPersonas();
		iniciarAcumuladoresIntereses();
		
		for (Persona p : listaPersonas) {
			agregarPersonaEnGrafo(p);
		}
	}
	
	public void agregarPersonaEnGrafo(Persona nuevaPersona) {
		grafo.agregarPersona(nuevaPersona);
		actualizarAcumuladores(nuevaPersona);
	}
	
	private void actualizarAcumuladores(Persona nuevaPersona) {
		acumInteresDeporte += nuevaPersona.interesDeporte();
		acumInteresMusica += nuevaPersona.interesMusica();
		acumInteresEspectaculos += nuevaPersona.interesEspectaculo();
		acumInteresCiencia += nuevaPersona.interesCiencia();
	}

	public Integer rangoMinimo() {
		return Persona.rangoMinimo();
	}

	public Integer rangoMaximo() {
		return Persona.rangoMaximo();
	}
	
	public Integer cantPersonas() {
		return grafo.nodos().size();
	}
	
	public Integer promInteresDeportes() {
		return promedioInteresValido(acumInteresDeporte);
	}

	public Integer promInteresMusica() {
		return promedioInteresValido(acumInteresMusica);
	}

	public Integer promInteresEspectaculo() {
		return promedioInteresValido(acumInteresEspectaculos);
	}

	public Integer promInteresCiencia() {
		return promedioInteresValido(acumInteresCiencia);
	}

	private Integer promedioInteresValido(Integer acumuladorInteres) {		
		if (acumuladorInteres < cantPersonas())
			return 0;
		return acumuladorInteres/cantPersonas();
	}
	
	public List<Object[]> obtenerGrupos() {
		grafo.calcularCaminoMinimo();

		List<Arista> agm = grafo.caminoMinimo();
		int indiceMayorPeso = grafo.aristaMayorPeso();
		int cantPersonas = cantPersonas();

		List<Object[]> grupos = new ArrayList<>();
		Set<String> grupo1 = new HashSet<String>();
		Set<String> grupo2 = new HashSet<String>();

		// 1° Se añaden las personas que componen la arista con mayor peso en dos grupos
		// distintos y eliminamos la arista 'x'
		grupo1.add(agm.get(indiceMayorPeso).persona1().nombre());
		grupo2.add(agm.get(indiceMayorPeso).persona2().nombre());

		// ELIMINAR ESTOOOOOOO
		System.out.println("Arista Mayor Peso:" + agm.get(indiceMayorPeso).toString());

		agm.remove(indiceMayorPeso);

		// 2° Comenzamos a recorrer la lista de aristas "AGM"
		while ((grupo1.size() + grupo2.size()) < cantPersonas) {
			removerArista(agm, grupo1, grupo2);
		}

		grupos.add(new Object[] { "Grupo 1", listaToString(grupo1) });
		grupos.add(new Object[] { "Grupo 2", listaToString(grupo2) });

		return grupos;
	}

	private void removerArista(List<Arista> agm, Set<String> g1, Set<String> g2) {

		for (int a = 0; a < agm.size(); a++) {

			if (g1.contains(agm.get(a).persona1().nombre())) {
				g1.add(agm.get(a).persona2().nombre());
				agm.remove(a);
				return;
			}

			if (g1.contains(agm.get(a).persona2().nombre())) {
				g1.add(agm.get(a).persona1().nombre());
				agm.remove(a);
				return;
			}

			if (g2.contains(agm.get(a).persona1().nombre())) {
				g2.add(agm.get(a).persona2().nombre());
				agm.remove(a);
				return;
			}

			if (g2.contains(agm.get(a).persona2().nombre())) {
				g2.add(agm.get(a).persona1().nombre());
				agm.remove(a);
				return;
			}
		}
	}

	private String listaToString(Set<String> lista) {
		StringBuilder sb = new StringBuilder();
		String separador = " - ";
		for (String s : lista) {
			sb.append(s + separador);
		}
		return sb.toString().substring(0, sb.length() - separador.length());
	}
	
}
