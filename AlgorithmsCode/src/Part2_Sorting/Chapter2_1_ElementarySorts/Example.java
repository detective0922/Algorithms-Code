package Part2_Sorting.Chapter2_1_ElementarySorts;


import java.io.File;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Example {
	
	public static void main(String[] args){
		File tFile = new File("algs4-data//8Kints.txt");
		int[] inList = new In(tFile).readAllInts();
		Integer[] a = new Integer[inList.length];
		for(int i=0;i<inList.length;i++){
			a[i] = inList[i];		
		}
		sort(a);
		assert isSorted(a);
		show(a);
	}

	public static void sort(Comparable[] a) {

	}

	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	private static void exch(Comparable[] a, int i, int j) {
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	private static void show(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			StdOut.print(a[i] + " ");
		}
		StdOut.println();
	}
	
	public static boolean isSorted(Comparable[] a) {
		for (int i = 1; i < a.length; i++) {
			if (less(a[i], a[i - 1])) {
				return false;
			}
		}
		return true;
	}

}
