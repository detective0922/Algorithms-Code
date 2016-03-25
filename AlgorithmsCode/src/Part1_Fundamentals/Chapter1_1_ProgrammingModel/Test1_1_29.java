package Part1_Fundamentals.Chapter1_1_ProgrammingModel;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Test1_1_29 {

	public static void main(String args[]) {
		File wFile = new File("algs4-data//largeW.txt");
		int[] whitelist = new In(wFile).readAllInts();
		Arrays.sort(whitelist);
		StdOut.println("rank: " + rank(whitelist, 987273));
		StdOut.println("count: " + count(whitelist, 987273));
		//output:
		//rank: 3949092
		//count: 4
		
	}
	
	public static int rank(int[] a, int key){
		int[] countArray = countElement(a, key);
		int total = 0;
		for (int i = 0; i < key; i++) {
			total = total + countArray[key];
		}
		return total;
	}
	
	public static int count(int[] a, int key){
		int[] countArray = countElement(a, key);
		return countArray[key];
	}

	public static int[] countElement(int[] a, int m){
		int[] array = new int[m + 1];
		int len = a.length;
		for (int i = 0; i < len; i++) {
			if (a[i] <= m)
				array[a[i]]++;
			if (a[i] > m)
				break;
		}
		
		return array;		
	}

}
