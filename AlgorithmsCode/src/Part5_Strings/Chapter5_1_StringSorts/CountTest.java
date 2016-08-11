package Part5_Strings.Chapter5_1_StringSorts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class CountTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		File tFile = new File("algs4-data//abra.txt");

		try {
			System.setIn(new FileInputStream(tFile.getAbsolutePath()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String s = "ABCDR";
		Alphabet alpha = new Alphabet(s);
		int R = alpha.R();
		int[] count = new int[R];
		
		String test = StdIn.readAll();
		int N = test.length();
		for (int i = 0; i < N; i++) {
			char c = test.charAt(i);
			if (alpha.contains(c)) {
				count[alpha.toIndex(c)]++;
			}
		}

		for (int j = 0; j < R; j++) {
			StdOut.println(alpha.toChar(j) + " " + count[j]);
		}
		
	}

}

class Alphabet{
	private char[] alphabet;
	private int[] index;
	private int R;
	
	public Alphabet(String s) {
		alphabet = s.toCharArray();
		R = s.length();
		index = new int[R];
		for(int i = 0;i<R;i++){
			index[i] = -1;
		}
		
		for(int i = 0;i<R;i++){
			index[alphabet[i]] = i;
		}
	}

	public int toIndex(char c) {

	}

	public char toChar(int index) {

	}

	public boolean contains(char c) {

	}

	public int R() {

	}

	public int lgR() {

	}

	public int[] toIndices(String s) {

	}

	public String toChars(int indices) {

	}
	
	
}