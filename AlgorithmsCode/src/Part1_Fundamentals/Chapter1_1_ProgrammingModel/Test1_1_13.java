package Part1_Fundamentals.Chapter1_1_ProgrammingModel;

import edu.princeton.cs.algs4.StdOut;

public class Test1_1_13 {

	public static void main(String args[]) {
		int[][] testArray = {
				{1,2,3},
				{4,5,6},
				{7,8,9},
				{10,11,12}
		};
		testArray = transposeArray(testArray);
		
		for (int i = 0; i < testArray.length; i++) {
			for (int j = 0; j < testArray[0].length; j++) {
				StdOut.print(testArray[i][j] + ",");
			}
			StdOut.println();
		}
		//output: 
		//1,4,7,10,
		//2,5,8,11,
		//3,6,9,12,
	}
	

	//1.1.13
	public static int[][] transposeArray(int[][] intArray) {
		int yLen = intArray.length;
		int xLen = intArray[0].length;
		int[][] newArray = new int[xLen][yLen];
		for (int i = 0; i < yLen; i++) {
			for (int j = 0; j < xLen; j++) {
				newArray[j][i] = intArray[i][j];
			}
		}
		return newArray;
	}

}
