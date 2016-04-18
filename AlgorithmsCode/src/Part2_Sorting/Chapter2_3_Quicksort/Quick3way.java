package Part2_Sorting.Chapter2_3_Quicksort;

import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Quick3way {
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
		if (hi <= lo)
			return;
		int lt = lo, i = lo, gt = hi;
		Comparable v = a[lo];
		while (gt >= i) {
			if (a[i].compareTo(v) < 0) {
				exch(a, i, lt);
				lt++;
				i++;
			}
			if (a[i].compareTo(v) > 0) {
				exch(a, i, gt);
				gt--;
			}
			if (a[i].compareTo(v) == 0) {
				i++;
			}
		}
		sort(a, lo, lt - 1);
		sort(a, gt + 1, hi);
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
