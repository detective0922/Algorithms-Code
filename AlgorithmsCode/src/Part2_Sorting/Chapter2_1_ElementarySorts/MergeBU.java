package Part2_Sorting.Chapter2_1_ElementarySorts;

import java.io.File;
import java.util.Iterator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class MergeBU {
	
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
		for (int sz = 1; sz < a.length; sz = sz + sz) {
			for (int lo = 0; lo < a.length; lo = lo + sz) {
				int hi = lo + sz;
				int mid = (lo + hi) / 2;
				merge(a, lo, mid, hi);
			}
		}
	}
	
	private static void merge(Comparable[] a, int lo, int mid, int hi) {
		int left = lo;
		int right = mid +1;
		int N = a.length;
		for (int i = lo; i <= hi; i++) { //not 0-N !
			aux[i] = a[i];
		}
		/*int i = 0;
		while(left<=mid && right>=N){
			if(less(aux[left], aux[right])){ //aux[left]<aux[right]
				a[i] = aux[left++];
			} else if(less(aux[right], aux[left])){
				a[i] = aux[right++];
			}
			i++;
		}*/
		for(int i = lo; i<=hi;i++){
			if (left > mid) {
				a[i] = aux[right++];
			} else if (right > hi) {
				a[i] = aux[left++];
			} else if (less(aux[left], aux[right])) {
				a[i] = aux[left++];
			} else {
				a[i] = aux[right++];
			}
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
