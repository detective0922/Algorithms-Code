package Part1_Fundamentals.Chapter1_2_DataAbstraction;

import java.io.File;
import java.util.Arrays;

import edu.princeton.cs.algs4.Counter;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Test1_2_9 {
	
	public static void main(String args[]) {
		File wFile = new File("algs4-data//largeW.txt");
		File tFile = new File("algs4-data//largeT.txt");
		int[] whitelist = new In(wFile).readAllInts();
		int[] inList = new In(tFile).readAllInts();
		Arrays.sort(whitelist);
		Counter counter = new Counter("times");
		for (int i = 0; i < inList.length; i++) {
			int key = inList[i];
			if (rank(key, whitelist,counter) < 0) {
				StdOut.println("key:" + key);
			}
		}
		StdOut.println(counter);
		//output:
		//19003811 times
	}
	
	public static int rank(int key, int[] a, Counter counter) {
		int lo = 0;
		int hi = a.length - 1;
		while (lo <= hi) {
			counter.increment();
			int mid = lo + (hi - lo) / 2;
			if (key < a[mid])
				hi = mid - 1;
			else if (key > a[mid])
				lo = mid + 1;
			else
				return mid;
		}
		return -1;
	}

}
