package Part1_Fundamentals.Chapter1_1_ProgrammingModel;

import edu.princeton.cs.algs4.StdOut;

public class Test1_1_30 {

	public static void main(String args[]) {
		int[][] intArray = createIntArray(10);
		boolean[][] booleanArray = createBooleanArray(10);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				StdOut.println(i + "," + j + ": " + intArray[i][j]);
				StdOut.println(i + "," + j + ": " + booleanArray[i][j]);
			}
		}
	}
	
	public static boolean[][] createBooleanArray(int N) {
		int[][] intArray = createIntArray(N);
		boolean[][] booleanArray = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (intArray[i][j] == 1) {
					booleanArray[i][j] = true;
				} else {
					booleanArray[i][j] = false;
				}
			}
		}
		return booleanArray;
	}
	
	public static int[][] createIntArray(int N) {
		int[][] intArray = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				intArray[i][j] = gcd(i, j);
			}
		}
		return intArray;
	}

	public static int gcd(int p, int q){
		if(q == 0) return p; 
		int r = p % q;
		return gcd(q, r);
	}

}
