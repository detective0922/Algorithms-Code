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
		
		for (int i = 0, skip = 0; i < N - M; i += skip) {
			int j;
			for (j = M - 1; j >= 0; j--) {
				/*if (txt.charAt(i + j) == pat.charAt(j)) {
					if (j == 0) {
						return i;
					}
					continue;
				} else if (right[txt.charAt(i + j)] == -1) {
					skip = j + 1;
					break;
				} else {
					if (j - right[txt.charAt(i + j)] > 0) {
						skip = j - right[txt.charAt(i + j)];
					} else {
						skip = 1;
					}
					break;
				}*/
				if(j==0){
					return i;
				}
				if (txt.charAt(i + j) != pat.charAt(j)) {
					skip = j - right[txt.charAt(i + j)];
					if (skip <= 0) {
						skip = 1;
					}
					break;
				}
			}
			

		}
		
		return N;
		
	}
}