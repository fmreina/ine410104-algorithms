package ine410104.ordenacaotopologica;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PreExecucao {

	public static void main(String[] args) {
		List<String> languages = getLinguagens();
		Map<String, List<String>> adjacencyMap = getAdjacencyOf(languages);
		buildAdjacencyMatrix(adjacencyMap);
	}

	private static List<String> getLinguagens() {
		List<String> lang = new LinkedList();
		readFile("languages").forEach(l -> {
			String[] t = l.split(":::");
			lang.add(t[0]);
		});
		// lang.forEach(System.out::println);
		// lang.forEach(l -> System.out.print(l + "\t"));
		// System.out.print("{");
		// lang.forEach(l -> System.out.print("\"" + l + "\","));
		// System.out.print("}");
		return lang;
	}

	private static Map<String, List<String>> getAdjacencyOf(List<String> nodeList) {
		Map<String, List<String>> adjacencyMap = new LinkedHashMap<>();
		nodeList.forEach(n -> adjacencyMap.put(n, new LinkedList<>()));
		readFile("relationship").forEach(r -> {
			String[] t = r.split(":::");
			List<String> oldList = adjacencyMap.get(t[0]);
			List<String> newList = new ArrayList<>();
			newList.addAll(oldList);
			newList.add(t[1]);
			adjacencyMap.put(t[0], newList);
		});
		// map.keySet().forEach(k -> {
		// System.out.print(k + " -> ");
		// map.get(k).forEach(l -> System.out.print(l + ", "));
		// System.out.println();
		// });
		return adjacencyMap;
	}

	private static String[][] buildAdjacencyMatrix(Map<String, List<String>> adjacencyMap) {
		int size = adjacencyMap.keySet().size();
		String[] keys = new String[size];
		adjacencyMap.keySet().toArray(keys);

		String[][] matrix = new String[size + 1][size + 1]; // size + 1 because of the border (vertices)
		for (int j = 0; j < size + 1; j++) {
			for (int i = 0; i < size + 1; i++) {
				if (i == j) {
					matrix[i][j] = "0";
				} else {
					if (i >= 1 && j == 0) {
						matrix[i][j] = keys[i - 1];
					} else if (i == 0 && j >= 1) {
						matrix[i][j] = keys[j - 1];
					} else {
						// search on map if vertices of the line contains the vertices of the column in the adjacency list
						if (adjacencyMap.get(matrix[i][0]).contains(keys[j - 1])) {
							matrix[i][j] = "1";
						} else {
							matrix[i][j] = "I"; // infinity
						}
					}
				}
			}
		}
		// // print matrix with border
		// for (int i = 0; i < size + 1; i++) {
		// for (int j = 0; j < size + 1; j++) {
		// System.out.print(matrix[i][j] + "\t");
		// }
		// System.out.println();
		// }

		// print matrix with no border
		for (int i = 1; i < size + 1; i++) {
			for (int j = 1; j < size + 1; j++) {
				System.out.print(matrix[i][j] + "\t");
			}
			System.out.println();
		}

		// // print matrix for the file
		// for (int i = 1; i < size + 1; i++) {
		// System.out.print("{");
		// for (int j = 1; j < size + 1; j++) {
		// System.out.print(matrix[i][j]);
		// if (j != size) {
		// System.out.print(",");
		// }
		// }
		// System.out.print("}");
		// if (i != size) {
		// System.out.print(",");
		// }
		// System.out.println(" // " + matrix[i][0]);
		// }
		return matrix;
	}

	private static List<String> readFile(String fileName) {
		List<String> lines = new ArrayList<>();
		try {
			Path path = Paths.get("./" + fileName);
			lines = Files.readAllLines(path);
		} catch (IOException e) {
			System.err.println("Oops! Something wrong is not right here! "
					+ "You got an error trying to read the file \"" + fileName + "\".");
		}
		// lines.forEach(System.out::println);
		return lines;
	}
}
