package Part5_Strings.Chapter5_3_SubstringSearch;

import edu.princeton.cs.algs4.StdOut;
import sun.tools.jar.resources.jar;

public class KMPTest {
	
	public static void main(String[] args) {
		
		String txt = "AABRAACADABRAACAADABRA";
		String pat = "AACAA";
		KMP kmp = new KMP(pat);
		int offset = kmp.search(txt);
		StdOut.println("text:     " + txt);
		StdOut.print("pattern:  ");
		for(int i = 0;i<offset;i++){
			StdOut.print(" ");
		}
		StdOut.println(pat);	
		StdOut.println(offset);
	}
	
}


class KMP{
	
	private String pat;
	private int[][] dfa;
	
	public KMP(String pat) {
		this.pat = pat;
		int R = 256;
		int M = pat.length();
		
		dfa = new int[R][M];
		dfa[pat.charAt(0)][0] = 1;
		int X = 0;
		for (int j = 1; j < M; j++) {
			for (char c = 0; c < R; c++) {
				dfa[c][j] = dfa[c][X];
			}
			dfa[pat.charAt(j)][j] = j + 1;
			X = dfa[pat.charAt(j)][X];
		}
		
	}
	
	public int search(String txt){
		int M = pat.length();
		int N = txt.length();
		int j = 0;
		int i = 0;
		for (; i < N & j < M; i++) {
			j = dfa[pat.charAt(i)][j];
		}

		if (j == M) {
			return i - M;
		} else {
			return N;
		}
	}

}

