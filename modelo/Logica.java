package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	
	public void agregarPersona(String nombre, int interesDeporte, int interesMusica, int interesEspectaculo, int interesCiencia) {
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
	
	public List<Object[]> obtenerRelaciones() {
		List<Object[]> lista = new ArrayList<>();
		Set<Arista> sa = grafo.aristas();
		List<Arista> la = new ArrayList<>(sa);
		
		Collections.sort(la, Comparator.comparingInt(a -> a.indiceSimilaridad()));
		
		for (Arista a : la) {
			lista.add(new Object[] { a.persona1().nombre(), a.persona2().nombre(), a.indiceSimilaridad() });
		}
			
		return lista;
	}
	
	public void generarGrupos() {
		grafo.calcularCaminoMinimo();
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
	
	public Integer cantRelaciones() {
		return grafo.aristas().size();
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
