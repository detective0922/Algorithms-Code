package Part1_Fundamentals.Chapter1_1_ProgrammingModel;

import java.io.File;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Test1_1_22 {

	public static void main(String args[]) {
		File wFile = new File("algs4-data//tinyW.txt");
		File tFile = new File("algs4-data//tinyT.txt");
		int[] whitelist = new In(wFile).readAllInts();
		int[] inList = new In(tFile).readAllInts();
		Arrays.sort(whitelist);
		for (int i = 0; i < inList.length; i++) {
			int key = inList[i];
			if (rank1(key, whitelist) < 0) {
				StdOut.println("key:" + key);
			} else {
				StdOut.println("key in white list");
			}
		}
	}

	public static int rank1(int key, int[] a) {
		return rank(key, a, 0, a.length - 1, 0);
	}
	
	public static int rank(int key, int[] a, int lo, int hi, int depth) {
		String outStr = "lo: " + lo + ",hi: " + hi;
		String format = "%" + (depth + outStr.length()) + "s\n";
		StdOut.printf(format, outStr);
		depth++;
		if (lo > hi)
			return -1;
		int mid = lo + (hi - lo) / 2;		
		if (key < a[mid])
			return rank(key, a, lo, mid - 1, depth);
		else if (key > a[mid])
			return rank(key, a, mid + 1, hi, depth);
		else
			return mid;
	}

}
