package Part1_Fundamentals.Chapter1_4_AnalysisofAlgorithms;

import java.io.File;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Test1_4_18 {
	
	public static void main(String[] args) {
		File tFile = new File("algs4-data//1Mints.txt");
		int[] inList = new In(tFile).readAllInts();
		//int[] inList = { -4, -3, -2, -1, 4, 3, 1, 2 };
		int min = findLocalMin(inList);
		if (min != -1) {			
			if (min - 1 >= 0) {
				StdOut.println(inList[min - 1]);
			}
			StdOut.println(inList[min]);
			if (min + 1 <= inList.length - 1) {
				StdOut.println(inList[min + 1]);
			}
		}else {
			StdOut.println("not found");
		}
	}
	
	public static int findLocalMin(int[] a) {
		int N = a.length;
		if (N == 1)
			return 0;
		if (a[0] < a[1]) {
			return 0;
		}
		if (a[N - 1] > a[N - 2]) {
			return N - 1;
		}
		int lo = 0;
		int hi = N - 1;	
		while (lo < hi) {
			int mid = lo + (hi - lo) / 2;
			if (a[mid] >= a[mid - 1]){
				hi = mid - 1 ;
			} else if (a[mid] >= a[mid + 1]){
				lo = mid + 1;
			} else {
				return mid;
			}
		}
		return -1;
	}

}
