package Part5_Strings.Chapter5_1_StringSorts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Quick3StringTest {
	
	public static void main(String[] args){
		File tFile = new File("algs4-data//shells.txt");

		try {
			System.setIn(new FileInputStream(tFile.getAbsolutePath()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] test = StdIn.readAllStrings();
		
		Quick3String.sort(test);

		for (int i = 0; i < test.length; i++) {
			StdOut.println(test[i]);
		}
	}

}

class Quick3String{
	public static void sort(String[] a) {

    }
	
	private static void sort(String[] a, int lo, int hi, int d) {

	}
	
	private static void exch(String[] a, int i, int j) {
		String s = a[i];
		a[i] = a[j];
		a[j] = s;
	}
	
	private static int charAt(String s, int d) {
		if (d < s.length()) {
			return s.charAt(d);
		} else {
			return -1;
		}
    }
}
