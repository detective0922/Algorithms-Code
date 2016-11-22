package Part5_Strings.Chapter5_3_SubstringSearch;

import edu.princeton.cs.algs4.StdOut;

public class BMTest {
	public static void main(String[] args) {
		String txt = "AABRAACADABRAACAADABRA";
		String pat = "AACAA";
		KMP kmp = new KMP(pat);
		int offset = kmp.search(txt);
		StdOut.println("text:     " + txt);
		StdOut.print("pattern:  ");
		for (int i = 0; i < offset; i++) {
			StdOut.print(" ");
		}
		StdOut.println(pat);
		StdOut.println(offset);
	}

}

class BoyerMoore{
	
}