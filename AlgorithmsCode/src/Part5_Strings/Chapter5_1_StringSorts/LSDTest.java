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
		int[] count = new int[R + 1];
		int len = a.length;
		//implement first character sort first
		for (int i = 0; i < len; i++) {
			char c = a[i].charAt(0);
			count[c]++;
		}
		//TODO
		for (int i = 0; i < R; i++) {
			count[i + 1] += count[i];
		}
		//TODO
		//TODO
		
	}
}