package Part5_Strings.Chapter5_1_StringSorts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import edu.princeton.cs.algs4.Count;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LSDTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File tFile = new File("algs4-data//words3.txt");

		try {
			System.setIn(new FileInputStream(tFile.getAbsolutePath()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] test = StdIn.readAllStrings();
		
		int w = test[0].length();
		LSD.sort(test, w);

		for (int i = 0; i < test.length; i++) {
			StdOut.println(test[i]);
		}
		
	}

}

class LSD {

	public static void sort(String[] a, int W) {
		int R = 256;	
		int len = a.length;
		int[] count = new int[R + 1];
		String[] aux = new String[len];
		// implement last character sort first
		// then implement the whole string sort
		for (int j = W - 1; j >= 0; j--) {		
			for (int i = 0; i < len; i++) {
				char c = a[i].charAt(j);
				count[c + 1]++;
			}
			// TODO
			for (int i = 0; i < R; i++) {
				count[i + 1] += count[i];
			}
			// TODO
			for (int i = 0; i < len; i++) {
				char c = a[i].charAt(j);
				aux[count[c]++] = a[i];
			}
			// TODO
			for (int i = 0; i < len; i++) {
				a[i] = aux[i];
			}
		}
	}
}