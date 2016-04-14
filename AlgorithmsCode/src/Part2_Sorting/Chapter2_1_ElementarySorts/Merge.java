package Part2_Sorting.Chapter2_1_ElementarySorts;

import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Merge {
	
	public static void main(String[] args) {
		File tFile = new File("algs4-data//tiny.txt");
		String[] inList = new In(tFile).readAllStrings();
		show(inList);
		sort(inList);
		assert isSorted(inList);
		show(inList);
	}
	
	private static Comparable[] aux;

	public static void sort(Comparable[] a) {
		aux = new Comparable[a.length];
		merge(a, 0, a.length / 2, a.length);
	}
	
	private static void merge(Comparable[] a, int lo, int mid, int hi) {
		int left = lo;
		int right = mid +1;
		int N = a.length;
		for (int i = 0; i < N; i++) {
			aux[i] = a[i];
		}
		int i = 0;
		while(left<=mid && right>=N){
			if(less(aux[left], aux[right])){ //aux[left]<aux[right]
				a[i] = aux[left++];
			} else if(less(aux[right], aux[left])){
				a[i] = aux[right++];
			}
			i++;
		}

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
