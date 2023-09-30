package modelo.objetos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GrafoPersona {
	private List<Persona> nodos;
	private Set<Arista> aristas;
	private List<Arista> caminoMinimo;

	private int[][] matrizAdyacencia;
	private int indiceMayorPeso;

	public GrafoPersona() {
		nodos = new ArrayList<>();
		aristas = new HashSet<>();
		caminoMinimo = new ArrayList<>();
		indiceMayorPeso = Integer.MIN_VALUE;
	}

	public void agregarPersona(Persona persona) {
		nodos.add(persona);
		int n = nodos.size();
		int[][] nuevaMatriz = new int[n][n];

		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - 1; j++) {
				nuevaMatriz[i][j] = matrizAdyacencia[i][j];
			}
		}
		matrizAdyacencia = nuevaMatriz;
		calcularAristas();
	}

	private void calcularAristas() {
		int n = nodos.size();
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				int similitud = calcularSimilitud(nodos.get(i), nodos.get(j));
				matrizAdyacencia[i][j] = similitud;
				matrizAdyacencia[j][i] = similitud;
				aristas.add(new Arista(nodos.get(i), nodos.get(j), similitud));
			}
		}
	}

	private int calcularSimilitud(Persona p1, Persona p2) {
		return p1.calcularIndiceSimilitud(p2);
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
//				System.out.println(nodos.get(i).nombre()+"-"+nodos.get(v).nombre() +" - "+ matrizAdyacencia[i][v] );
				if (matrizAdyacencia[u][v] != 0 && !visitados[v] && matrizAdyacencia[u][v] < costoMinimo[v]) {
					padre[v] = u;
					costoMinimo[v] = matrizAdyacencia[u][v];
				}
			}
		}

		// Imprime el camino mínimo
		System.out.println("Camino minimo:");
		for (int i = 1; i < n; i++) {
			Persona padreNombre = nodos.get(padre[i]);
			Persona hijoNombre = nodos.get(i);
			int similaridad = matrizAdyacencia[i][padre[i]];
			Arista aristaActual = new Arista(nodos.get(padre[i]), nodos.get(i), similaridad);
			caminoMinimo.add(aristaActual);
			System.out.println(padreNombre.nombre() + " - " + hijoNombre.nombre() + ": " + similaridad);
			if (similaridad > indiceMayorPeso) {
				indiceMayorPeso = i-1;
				
				
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

	public List<Persona> nodos() {
		return this.nodos;
	}

	public Set<Arista> aristas() {
		return this.aristas;
	}

	public List<Arista> caminoMinimo() {
		return this.caminoMinimo;
	}
	
	
	public Integer aristaMayorPeso() {
		return this.indiceMayorPeso;
	}
	
	

}