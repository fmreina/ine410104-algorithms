package ine410104.arvoregrradoraminima.kruskal;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class mainKurskal {

	public static int father[];
	public static int rank[];

	public static void main(String[] args) {
		int g[][] = {
				// a,b, c, d, e, f
				{ 0, 4, 0, 0, 0, 3 },
				{ 4, 0, 5, 0, 0, 8 },
				{ 0, 5, 0, 2, 0, 0 },
				{ 0, 0, 2, 0, 6, 2 },
				{ 0, 0, 0, 6, 0, 1 },
				{ 3, 8, 0, 2, 1, 0 }
		};

		int[][] T = kruskal(g);

		int custo = 0;
		for (int i = 0; i < g.length; i++) {
			System.out.println(T[i][0] + " - " + T[i][1] + " custo: " + T[i][2]);
			custo = custo + T[i][2];
		}
		System.out.println("Custo Total:" + custo);
	}

	private static int[][] kruskal(int[][] g) {
		List<int[]> edges = convertMatrizToList(g);
		int size = edges.size();

		int[][] A = new int[size][];
		father = new int[size];
		rank = new int[size];

		for (int vertice = 0; vertice < size; vertice++) {
			makeSet(vertice); // initilize the tree
		}

		Collections.sort(edges, (a, b) -> edgeSorter(a, b));

		int treeHight = 0;
		for (int e = 0; e < size; e++) {
			int[] edge = edges.get(e);
			int p = find(edge[0]);
			int q = find(edge[1]);
			if (p == q) {
				continue;
			}
			A[treeHight] = edge;
			treeHight++;
			unionLink(p, q);
		}
		return A;
	}

	public static void makeSet(int vertice) {
		father[vertice] = vertice;
		rank[vertice] = 0;
	}

	public static int find(int p) {
		if (father[p] == p) {
			return p;
		}
		return father[p] = find(father[p]); // path compression
	}

	// union by rank
	private static void unionLink(int p, int q) {
		if (rank[p] > rank[q]) {
			rank[q] = p;
		} else {
			father[p] = q;
			if (rank[q] == rank[p]) {
				rank[q] = rank[q] + 1;
			}
		}
	}

	public static List<int[]> convertMatrizToList(int[][] g) {
		int order = g.length;
		List<int[]> edges = new LinkedList<>();
		for (int line = 0; line < order; line++) {
			for (int column = 0; column < order; column++) {
				if (line < column && g[line][column] != 0) { // it gets only the upper triangle bc it's a undirected matrix
					// [0]=u(origin), [1]=v(destiny), [2]=w(weigh)
					edges.add(new int[] { line, column, g[line][column] });
				}
			}
		}
		return edges;
	}

	private static int edgeSorter(int[] a, int[] b) {
		if (a[2] < b[2]) {
			return -1;
		}
		if (a[2] > b[2]) {
			return 1;
		}
		return 0;
	}

}
