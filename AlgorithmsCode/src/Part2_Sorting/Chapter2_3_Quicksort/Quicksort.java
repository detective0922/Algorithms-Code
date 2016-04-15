package Part2_Sorting.Chapter2_3_Quicksort;

import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Quicksort {
	public static void main(String[] args) {
		File tFile = new File("algs4-data//tiny.txt");
		String[] inList = new In(tFile).readAllStrings();
		show(inList);
		sort(inList);
		assert isSorted(inList);
		show(inList);
	}
	
	public static void sort(Comparable[] a){
		//StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
	}
	
	private static void sort(Comparable[] a, int lo, int hi) {
		if(hi<=lo)
			return;
		int j = myPartition(a, lo, hi);
		//int j = partitionOnBook(a, lo, hi);
		StdOut.println(j + "," + a[j]);
		sort(a, lo, j - 1);
		sort(a, j + 1, hi);
	}
	
	private static int myPartition(Comparable[] a, int lo, int hi){
		int i = lo + 1;
		int j = hi;
		Comparable v = a[lo];
		while (i <= j) {
			if (less(v, a[i]) && less(a[j], v)) {
				exch(a, i, j);
				i++;
				j--;
			}
			if (!less(v, a[i])) {
				i++;
			}
			if (!less(a[j], v)) {
				j--;
			}

		}
		exch(a, lo, j);
		return j;
	}
	
	private static int partitionOnBook(Comparable[] a, int lo, int hi){

		int i = lo;
		int j = hi + 1;
		Comparable v = a[lo];
		while (true) {
			while (less(a[++i], v)) {
				if (i == hi)
					break;
			}
			while (less(v, a[--j])) {
				if (j == lo)
					break;
			}
			if (i >= j)
				break;
			exch(a, i, j);
		}
		exch(a, lo, j);
		return j;
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
