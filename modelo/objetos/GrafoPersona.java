package modelo.objetos;

import java.util.ArrayList;

import java.util.List;

public class GrafoPersona {
	private List<Persona> nodos;
	private List<Arista> caminoMinimo;
	private int[][] matrizAdyacencia;
	private int indiceMayorPeso;

	public GrafoPersona() {
		nodos = new ArrayList<>();
		caminoMinimo = new ArrayList<>();
		indiceMayorPeso = 0;
	}

	public void agregarPersona(Persona persona) {
		agregarPersonaEnNodo(persona);
		actualizarMatrizAdyacencia();
	}
	
	private void agregarPersonaEnNodo(Persona persona) {
		nodos.add(persona);
	}
	
	private void actualizarMatrizAdyacencia() {
		int cantPersonas = nodos.size();
		int[][] nuevaMatriz = new int[cantPersonas][cantPersonas];
		for (int i = 0; i < cantPersonas; i++) {
			for (int j = i + 1; j < cantPersonas; j++) {
				Persona persona1 = nodos.get(i);
				Persona persona2 = nodos.get(j);
				int similitud = persona1.calcularIndiceSimilitud(persona2);
				
				nuevaMatriz[i][j] = similitud;
				nuevaMatriz[j][i] = similitud;
			}
		}
		matrizAdyacencia = nuevaMatriz;
	}

	public void eliminarPersonas() {
		nodos.clear();
	}

	// Implementa el algoritmo de Prim para calcular el árbol de expansión mínima.
	public void calcularCaminoMinimo() {
		int n = nodos.size();
		boolean[] visitados = new boolean[n];
		int[] padre = new int[n];
		int[] costoMinimo = new int[n];

		for (int i = 0; i < n; i++) {
			costoMinimo[i] = Integer.MAX_VALUE;
		}

		costoMinimo[0] = 0;

		for (int i = 0; i < n - 1; i++) {
			int u = obtenerMinimo(costoMinimo, visitados);
			visitados[u] = true;

			for (int v = 0; v < n; v++) {
				if (matrizAdyacencia[u][v] != 0 && !visitados[v] && matrizAdyacencia[u][v] < costoMinimo[v]) {
					padre[v] = u;
					costoMinimo[v] = matrizAdyacencia[u][v];
				}
			}
		}

		Integer mayorValorSimilaridad = Integer.MIN_VALUE;

		for (int i = 1; i < n; i++) {
			Integer similaridad = matrizAdyacencia[i][padre[i]];
			caminoMinimo.add(new Arista(nodos.get(padre[i]), nodos.get(i), similaridad));

			if (similaridad > mayorValorSimilaridad) {
				mayorValorSimilaridad = similaridad;
				indiceMayorPeso = caminoMinimo.size() - 1;
			}

		}

	}

	private int obtenerMinimo(int[] costoMinimo, boolean[] visitados) {
		int n = nodos.size();
		int minimo = Integer.MAX_VALUE;
		int minimoIndice = -1;

		for (int v = 0; v < n; v++) {
			if (!visitados[v] && costoMinimo[v] < minimo) {
				minimo = costoMinimo[v];
				minimoIndice = v;
			}
		}

		return minimoIndice;
	}

	public List<Arista> caminoMinimo() {
		return this.caminoMinimo;
	}

	public Integer aristaMayorPeso() {
		return this.indiceMayorPeso;
	}

}