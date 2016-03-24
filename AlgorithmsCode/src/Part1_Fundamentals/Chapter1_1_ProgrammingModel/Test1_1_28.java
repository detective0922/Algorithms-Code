package Part1_Fundamentals.Chapter1_1_ProgrammingModel;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Test1_1_28 {

	public static void main(String args[]) {
		File wFile = new File("algs4-data//largeW.txt");
		int[] whitelist = new In(wFile).readAllInts();
		Arrays.sort(whitelist);
		int[] count = countElement(whitelist,
				whitelist[whitelist.length - 1]);
		List<Integer> newWhiteList = new ArrayList<Integer>();
		int repCount = 0;
		for (int i = 0; i < count.length; i++) {
			//just print reduplicate elements
			if (count[i] > 1) {
				StdOut.println(i + " appear " + count[i] + " times");
				repCount = repCount + count[i] - 1;
			}
			
			//remove reduplicate elements
			if (count[i] > 0) {
				newWhiteList.add(i);
			}
		}
		StdOut.println("remove " + repCount + " elements");
		StdOut.println("Old white list size: " + whitelist.length);
		StdOut.println("New white list size: " + newWhiteList.size());
		//output:
		//remove 367531 elements
		//Old white list size: 1000000
		//New white list size: 632469
		
	}

	public static int[] countElement(int[] a, int m){
		int[] array = new int[m + 1];
		int len = a.length;
		for (int i = 0; i < len; i++) {
			if (a[i] <= m)
				array[a[i]]++;
		}
		
		return array;		
	}

}
