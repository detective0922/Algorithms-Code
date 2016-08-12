package Part5_Strings.Chapter5_1_StringSorts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Part2_Sorting.Chapter2_1_ElementarySorts.Insertion;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class MSDTest {
	public static void main(String[] args){
		File tFile = new File("algs4-data//shells.txt");

		try {
			System.setIn(new FileInputStream(tFile.getAbsolutePath()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] test = StdIn.readAllStrings();
		
		MSD.sort(test);

		for (int i = 0; i < test.length; i++) {
			StdOut.println(test[i]);
		}
	}

}

class MSD{
	
	private static int R = 256;
	private static int M = 0;
	private static String[] aux;
	
	public static void sort(String[] a) {
		int len = a.length;
		aux = new String[len];
		sort(a, 0, len - 1, 0);
	}

	public static void sort(String[] a, int lo, int hi, int d) {
		if (hi <= lo + M) {
			//Insertion.sort(a);
			return;
		}
				
		int[] count = new int[R + 2];
		for (int i = lo; i <= hi; i++) {
			int c = charAt(a[i], d);
			count[c + 2]++;
		}

		for (int i = 0; i < R + 1; i++) {
			count[i + 1] += count[i];
		}

		for (int i = lo; i <= hi; i++) {
			int c = charAt(a[i], d);
			aux[count[c + 1]++] = a[i];
		}

		for (int i = lo; i <= hi; i++) {
			a[i] = aux[i - lo];
		}
		
		for (int i = 0; i < R; i++) {
			sort(a, lo + count[i], lo + count[i+1] - 1, d + 1);
		}

	}
	
	private static int charAt(String s, int d) {
		if (d < s.length()) {
			return s.charAt(d);
		} else {
			return -1;
		}
    }
	
}
