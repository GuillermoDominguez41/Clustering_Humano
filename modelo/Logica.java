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

	@SuppressWarnings("unused")
	private Coordinador coordinador;
	private GrafoPersona grafo;

	public Logica(Coordinador coord) {
		grafo = new GrafoPersona();
		coordinador = coord;
	}

	public void agregarPersona(String nombre, int interesDeporte, int interesMusica, int interesEspectaculo,
			int interesCiencia) {
		grafo.agregarPersona(new Persona(nombre, interesDeporte, interesMusica, interesEspectaculo, interesCiencia));
	}

	public List<Object[]> obtenerPersonas() {
		List<Object[]> lista = new ArrayList<>();
		List<Persona> lp = grafo.nodos();

		for (int ind = 0; ind < lp.size(); ind++) {
			lista.add(new Object[] { ind, lp.get(ind).nombre(), lp.get(ind).interesDeporte(),
					lp.get(ind).interesMusica(), lp.get(ind).interesEspectaculo(), lp.get(ind).interesCiencia() });
		}

		return lista;
	}

	public List<String> obtenerGrupos() {
		grafo.calcularCaminoMinimo();

		List<Arista> agm = grafo.caminoMinimo();
		int indiceMayorPeso = grafo.aristaMayorPeso();
		int cantPersonas = cantPersonas();

		List<String> grupos = new ArrayList<>();
		Set<String> grupo1 = new HashSet<String>();
		Set<String> grupo2 = new HashSet<String>();

		// 1° Se añaden las personas que componen la arista con mayor peso en dos grupos
		// distintos y eliminamos la arista 'x'
		grupo1.add(agm.get(indiceMayorPeso).persona1().nombre());
		grupo2.add(agm.get(indiceMayorPeso).persona2().nombre());

		agm.remove(indiceMayorPeso);

		// 2° Comenzamos a recorrer la lista de aristas "AGM"
		while ((grupo1.size() + grupo2.size()) < cantPersonas) {
			removerArista(agm, grupo1, grupo2);

		}

		grupos.add(listaToString(grupo1));
		grupos.add(listaToString(grupo2));

		return grupos;
	}

	private void removerArista(List<Arista> agm, Set<String> g1, Set<String> g2) {

		for (int a = 0; a < agm.size(); a++) {

			/*
			 * 3° Verificamos si alguna de las dos personas de la arista se encuentra en
			 * algun grupo. Al encontrar relacion de una de las personas, agregamos la
			 * "otra persona" al mismo grupo
			 */

			if (g1.contains(agm.get(a).persona1().nombre())) {
				g1.add(agm.get(a).persona2().nombre());
				agm.remove(a);
				return;
			}

			if (g2.contains(agm.get(a).persona1().nombre())) {
				g2.add(agm.get(a).persona2().nombre());
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

	public Integer rangoMinimo() {
		Persona p = new Persona("", 1, 1, 1, 1);
		return p.rangoMinimo();
	}

	public Integer rangoMaximo() {
		Persona p = new Persona("", 1, 1, 1, 1);
		return p.rangoMaximo();
	}

	public Integer cantPersonas() {
		return grafo.nodos().size();
	}

	public Integer promInteresDeporte() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer promInteresMusica() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer promInteresEspectaculo() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer promInteresCiencia() {
		// TODO Auto-generated method stub
		return null;
	}

}
