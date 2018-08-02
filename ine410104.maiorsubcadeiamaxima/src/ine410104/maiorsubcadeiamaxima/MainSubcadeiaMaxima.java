package ine410104.maiorsubcadeiamaxima;

public class MainSubcadeiaMaxima {

	private static final int UP = 1;
	private static final int LEFT = 2;
	private static final int DIAGONAL = 3;

	public static void main(String[] args) {
		String x = "abcb";
		String y = "bdcab";

		String scm = getSCM(x, y);
		System.out.println("A subcadeica máxima é \"" + scm + "\" de tamanho " + scm.length());
	}

	private static String getSCM(String x, String y) {
		char[] X = x.toCharArray();
		char[] Y = y.toCharArray();
		int[][] C = new int[x.length() + 1][y.length() + 1];
		int[][] B = new int[x.length() + 1][y.length() + 1];

		for (int i = 0; i <= x.length(); i++) {
			C[i][0] = 0;
			B[i][0] = 0;
		}

		for (int i = 0; i <= y.length(); i++) {
			C[0][i] = 0;
			B[0][i] = 0;
		}

		for (int i = 1; i <= x.length(); i++) {
			for (int j = 1; j <= y.length(); j++) {
				if (X[i - 1] == Y[j - 1]) {
					C[i][j] = C[i - 1][j - 1] + 1;
					B[i][j] = DIAGONAL;
				} else {
					C[i][j] = C[i - 1][j - 1] + 0;
					B[i][j] = 0;
				}
				if (C[i - 1][j] > C[i][j]) {
					C[i][j] = C[i - 1][j];
					B[i][j] = UP;
				}
				if (C[i][j - 1] > C[i][j]) {
					C[i][j] = C[i][j - 1];
					B[i][j] = LEFT;
				}
			}
		}

		int i = x.length();
		int j = y.length();
		int lengh = C[i][j];
		int pos = lengh - 1;
		char[] scm = new char[pos + 1];

		while (i > 0 && j > 0) {
			if (B[i][j] == UP) {
				i--;
			} else if (B[i][j] == LEFT) {
				j--;
			} else if (B[i][j] == DIAGONAL) {
				i--;
				j--;
				scm[pos--] = x.charAt(i);
			}
		}

		return new String(scm);
	}
}
