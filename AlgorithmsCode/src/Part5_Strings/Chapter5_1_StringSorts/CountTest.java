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
		
		StdOut.println(alpha.lgR(10));
		StdOut.println(alpha.lgR2(10));
		
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
		return index[c];
	}

	public char toChar(int index) {
		return alphabet[index];
	}

	public boolean contains(char c) {
		return index[c] != -1;
	}

	public int R() {
		return R;
	}

	public int lgR(int R) {
		int lgR = 0;
		while (R>>1 >0) {
			lgR++;
		}
		return lgR;
	}
	
	public int lgR2(int R) {
		 int lgR = 0;
	        for (int t = R-1; t >= 1; t /= 2)
	            lgR++;
	        return lgR;
	}

	public int[] toIndices(String s) {
		char[] chars = s.toCharArray();
		int[] indices = new int[chars.length];
		for (int i = 0; i < chars.length; i++) {
			indices[i] = index[chars[i]];
		}
		return indices;
	}

	public String toChars(int[] indices) {
		char[] chars = new char[indices.length];
		for(int i = 0;i<indices.length;i++){
			chars[i] = alphabet[indices[i]];
		}
		return new String(chars);
	}
	
	
}