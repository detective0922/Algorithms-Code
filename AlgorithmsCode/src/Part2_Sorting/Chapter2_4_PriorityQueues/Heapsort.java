package Part2_Sorting.Chapter2_4_PriorityQueues;

import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Heapsort {
	
	public static void main(String[] args) {
		File tFile = new File("algs4-data//tiny.txt");
		String[] inList = new In(tFile).readAllStrings();
		show(inList);
		sort(inList);
		assert isSorted(inList);
		show(inList);
	}

	public static void sort(Comparable[] a) {
		int N = a.length;
		for(int i = N/2;i>0;i--){
			sink(a,i,N);
		}
		while(N>1){
			exch(a, 1, N--);
			sink(a,1,N);
		}
	}
	
	private static void sink(Comparable[] a,int k,int N) {
		int sun = 0;
		
		while (2 * k <= N) {
			if (2 * k < N && less(a, 2 * k, 2 * k + 1)) {
				sun = 2 * k + 1;
			} else {
				sun = 2 * k;
			}
			
			if(!less(a, k,sun))
				break;
			exch(a,k, sun);
			k = sun;
		}
		
	}

	private static boolean less(Comparable[] a, int i, int j) {
		return a[i-1].compareTo(a[j-1]) < 0;
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
			if (less(a,i,i-1)) {
				return false;
			}
		}
		return true;
	}

}
