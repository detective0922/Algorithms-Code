package Part5_Strings.Chapter5_3_SubstringSearch;

import edu.princeton.cs.algs4.StdOut;

public class BMTest {
	public static void main(String[] args) {
		String txt = "AABRAACADABRAACAADABRA";
		String pat = "AACAA";
		BoyerMoore bm = new BoyerMoore(pat);
		int offset = bm.search(txt);
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
	private int[] right;
	private String pat;
	
	public BoyerMoore(String pat){
		this.pat = pat;
		int R = 256;
		int M = pat.length();
		
		right = new int[R];
		for (char c = 0; c < R; c++) {
			right[c] = -1;
		}

		for (int i = 0; i < M; i++) {
			right[pat.charAt(i)] = i;
		}
	}
	
	public int search(String txt) {
		int M = pat.length();
		int N = txt.length();
		int i = 0;
		int j = M-1;
		for (; i < N && j < M; i++) {
			if (txt.charAt(i + j) == pat.charAt(j)) {
				j--;
			} else if (right[txt.charAt(i+j)] == -1){
				i += M;
			} else {
				if(j-right[txt.charAt(i+j)]>=0){
					i += j-right[txt.charAt(i+j)];
				} else {
					i++;
				}
			}
		}
		
		if(j == 0) {
			return i;
		} else {
			return N;
		}
		
	}
}