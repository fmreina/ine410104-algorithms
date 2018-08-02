package ine410104.ordenacaotopologica;

public class MainOrdenacaoTopologica {

	final static int WHITE = 0;// Not visited
	final static int GREY = 1; // Discovered
	final static int BLACK = 2; // Finished

	static int color[];
	static int discovery[];
	static int finishing[];
	static int time;

	public static void main(String[] args) {
		int G[][] = getGraphMatrix();

		initializeVariables(G.length);
		DFS(G);

		// System.out.println("Topological Order:");
		// for (int i = 0; i < G.length; i++) {
		// System.out.println(i + 1 + " -> " + getLanguage(i) + " : d(x)= " + discovery[i] + "|f(x)= " + finishing[i]);
		// }

		for (int i = 0; i < G.length; i++) {
			System.out.print("[" + (i + 1) + "]" + getLanguage(i) + " -> ");
		}
	}

	private static void initializeVariables(int numberOfVertices) {
		color = new int[numberOfVertices];
		discovery = new int[numberOfVertices];
		finishing = new int[numberOfVertices];

		for (int vertex = 0; vertex < numberOfVertices; vertex++) {
			color[vertex] = WHITE;
			discovery[vertex] = -1;
		}
		time = 0;
	}

	public static void DFS(int[][] G) {
		for (int vertex = 0; vertex < G.length; vertex++) {
			if (color[vertex] == WHITE) {
				visit(vertex, G);
			}
		}
	}

	private static void visit(int vertex, int[][] G) {
		color[vertex] = GREY;
		discovery[vertex] = time++;
		for (int adjacent = 0; adjacent < G.length; adjacent++) {
			if (G[vertex][adjacent] != 0 && color[adjacent] == WHITE) {
				visit(adjacent, G);
			}
		}
		color[vertex] = BLACK;
		finishing[vertex] = time++;
	}

	/**
	 * matrix of the graph given in:
	 * https://www.inf.ufsc.br/~alexandre.goncalves.silva/courses/18s1/ine410104/exercicios/lista2/linguagens.pdf
	 * https://www.inf.ufsc.br/~alexandre.goncalves.silva/courses/18s1/ine410104/exercicios/lista2/linguagens.py
	 * https://www.inf.ufsc.br/~alexandre.goncalves.silva/courses/18s1/ine410104/exercicios/lista2/linguagens.pl
	 */
	private static int[][] getGraphMatrix() {
		int I = Integer.MAX_VALUE;
		int g[][] = {
				{ 0, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Flow-matic
				{ I, 0, 1, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Fortran I
				{ I, I, 0, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Fortran II
				{ I, I, I, 0, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Algol 58
				{ I, I, I, I, 0, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Lisp
				{ I, I, I, I, I, 0, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // COBOL
				{ I, I, I, I, I, I, 0, 1, I, 1, 1, I, 1, I, 1, I, I, I, I, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Algol 60
				{ I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // CPL
				{ I, I, I, I, I, I, I, I, 0, I, 1, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Fortran IV
				{ I, I, I, I, I, I, I, I, I, 0, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Simula I
				{ I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // PL/I
				{ I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Basic
				{ I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Algol W
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, 1, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 1, I, I, I, 1, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Simula 67
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 1, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Algol 68
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // BCPL
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Smalltalk
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // B
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 1, I, I, I, I, 1, 1, I,
						I, I, 1, I, I, I, I, I, I, I, I, 1, I, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Prolog
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, 1, I, I, 1, I, I, I, I, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						1, I, I, I, I, I, I }, // Pascal
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						1, I, I, I, I, I, I }, // C
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Smalltalk 72
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, 1, I, I }, // CLU
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Smalltalk 74
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Scheme
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Modula
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Smalltalk 76
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // S
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Modula-2
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, I, I, 1, I, I, 1, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // ML
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Fortran 77
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Smalltalk 78
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, 1, 1, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Scheme MIT
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						1, I, I, I, I, I, I }, // CSP
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, I, 1, I, I, 1, I, I, I, I, I,
						I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // C (K&R)
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Ada
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // ABC
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, I, 1, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, 1, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I,
						1, I, I, I, I, I, I }, // Smalltalk 80
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, I, 1, I, I, I, I,
						I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Ada 83
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Pascal AFNOR
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Lazy ML
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // SML
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, 1, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Scheme 84
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, 1, I, 1,
						I, I, I, I, I, I, I, I, I, I, I, I, I, 1, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Common Lisp
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I,
						I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Miranda
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I,
						I, 1, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 1, I, I, I,
						I, 1, I, I, I, I, I }, // C++
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // CLIPS
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, 1, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Scheme R3RS
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, 1, I, I }, // Objective-C
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Eiffel
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Scheme R4RS
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, 1, I, I, I, I, I }, // Erlang
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Life
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // CLOS
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						0, I, I, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						1, I, I, I, I, I, I }, // Modula-3
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, 0, I, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // ANSI C
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, 0, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Lambda Prolog
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, 0, I, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // C++ (ARM)
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, 0, I, I, 1, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Haskell
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, 0, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // SML 90
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Fortran 90
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Gofer
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, I, I, I, I, 1, I, I, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Python
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, 0, I, I, I, I, 1, I, 1, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Perl 4
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Oak
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Mercury
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // AppleScript
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // R
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 1, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Lua
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Common Lisp (ANSI)
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Perl 5
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // C 95
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Prolog ISO
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Ada 95
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // PHP
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 1, I, I, I,
						I, I, I, I, I, I, I }, // Java
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, 1, I, I }, // Ruby
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Squeak
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 1, I, I, I, I, I, I, I, I,
						I, 1, I, I, I, I, I }, // OCaml
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Scheme R5RS
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // C++ (ISO)
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, 1, I, I, I, I, I, I, I, 1, 1, I, I, I, 1, I, I, I, I,
						I, I, I, I, I, I, I }, // Java 2
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, 1, I, I, I, I,
						I, 1, I, I, 1, I, I }, // Haskell 98
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, I, I, I, I, 1, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // .NET
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Python 1.5.2
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 1, I, I, I,
						I, I, I, I, I, I, I }, // C 99
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Lua 4.0
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, 1, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						1, I, I, I, 1, I, I }, // Python 2.0
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, 1, I, I, I, 1, I, I, I,
						I, I, I, I, I, I, I }, // C#
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // GHC 5.00
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, 1, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Python 2.2
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Lua 5.0
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Io
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, I, 1, I,
						I, I, I, I, I, I, I }, // Fortran 2003
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, I, I, I, I, I,
						I, I, 1, I, I, I, I }, // GHC 6.0
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, 1, I, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Python 2.4
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, 1, I, I, I, I, I,
						I, I, I, I, I, I, I }, // Java 5
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, I, 1, I,
						I, I, I, I, I, I, I }, // Scala
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, 1, I, I,
						I, 1, I, I, 1, I, I }, // C# 2.0
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I, 1,
						I, I, I, I, I, I, I }, // Python 2.5
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I, I,
						I, I, I, 1, I, I, I }, // Java 6
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I, I,
						I, I, I, I, I, I, I }, // CAL (Open Quark)
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I, I,
						I, I, I, I, 1, I, I }, // D 1.0
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I, I,
						I, I, I, I, I, I, I }, // C# 3.0
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0, I,
						I, I, I, I, I, I, I }, // Fortress 1.0beta
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, 0,
						I, I, I, I, I, I, 1 }, // Python 3.0
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						0, I, I, I, I, I, I }, // Go
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, 0, I, I, 1, I, I }, // Rust
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, 0, I, I, I, I }, // Haskell 2010
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, 0, I, 1, I }, // Java 7
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, 0, I, I }, // Swift
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, 0, I }, // Java 8
				{ I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I,
						I, I, I, I, I, I, 0 } // Python 3.6
		};
		return g;
	}

	private static String getLanguage(int i) {
		String[] languages = { "Flow-matic", "Fortran I", "Fortran II", "Algol 58", "Lisp", "COBOL", "Algol 60", "CPL", "Fortran IV", "Simula I", "PL/I", "Basic", "Algol W",
				"Simula 67", "Algol 68", "BCPL", "Smalltalk", "B", "Prolog", "Pascal", "C", "Smalltalk 72", "CLU", "Smalltalk 74", "Scheme", "Modula", "Smalltalk 76", "S",
				"Modula-2", "ML", "Fortran 77", "Smalltalk 78", "Scheme MIT", "CSP", "C (K&R)", "Ada", "ABC", "Smalltalk 80", "Ada 83", "Pascal AFNOR", "Lazy ML", "SML",
				"Scheme 84", "Common Lisp", "Miranda", "C++", "CLIPS", "Scheme R3RS", "Objective-C", "Eiffel", "Scheme R4RS", "Erlang", "Life", "CLOS", "Modula-3", "ANSI C",
				"Lambda Prolog", "C++ (ARM)", "Haskell", "SML 90", "Fortran 90", "Gofer", "Python", "Perl 4", "Oak", "Mercury", "AppleScript", "R", "Lua", "Common Lisp (ANSI)",
				"Perl 5", "C 95", "Prolog ISO", "Ada 95", "PHP", "Java", "Ruby", "Squeak", "OCaml", "Scheme R5RS", "C++ (ISO)", "Java 2", "Haskell 98", ".NET", "Python 1.5.2",
				"C 99", "Lua 4.0", "Python 2.0", "C#", "GHC 5.00", "Python 2.2", "Lua 5.0", "Io", "Fortran 2003", "GHC 6.0", "Python 2.4", "Java 5", "Scala", "C# 2.0",
				"Python 2.5", "Java 6", "CAL (Open Quark)", "D 1.0", "C# 3.0", "Fortress 1.0beta", "Python 3.0", "Go", "Rust", "Haskell 2010", "Java 7", "Swift", "Java 8",
				"Python 3.6" };
		return languages[i];
	}
}
