package Part1_Fundamentals.Chapter1_1_ProgrammingModel;

import edu.princeton.cs.algs4.StdOut;

public class Test1_1_15 {

	public static void main(String args[]) {
		int[] testArray = {
				1,2,3,
				1,5,6,
				1,8,9,
				1,11,12
		};
		int[] array = histogram(testArray, 14);
		int[] tmpArray = betterHistogram(testArray, 14);
		for (int i = 0; i < array.length; i++) {
			StdOut.print(array[i] + " ");
		}
		StdOut.println();
		for (int i = 0; i < tmpArray.length; i++) {
			StdOut.print(tmpArray[i] + " ");
		}
		//output: 0 4 1 1 0 1 1 0 1 1 0 1 1 0 
	}
	
	public static int[] histogram(int[] a, int m){
		int[] array = new int[m];
		for (int i = 0; i < m; i++) {
			int count = 0;
			for (int j = 0; j < a.length; j++) {
				if (a[j] == i)
					count++;
			}
			array[i] = count;
		}
		return array;		
	}
	
	public static int[] betterHistogram(int[] a, int m){
		int[] array = new int[m];
		int len = a.length;
		for (int i = 0; i < len; i++) {
			if (a[i] < m)
				array[a[i]]++;
		}
		
		return array;		
	}

}
