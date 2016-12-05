package Part5_Strings.Chapter5_3_SubstringSearch;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;

public class RKTest {

	public static void main(String[] args) {
		String txt = "AABRAACADABRAACAADABRA";
		String pat = "AACAA";
		RabinKarp rk = new RabinKarp(pat);
		int offset = rk.search(txt);
		StdOut.println("text:     " + txt);
		StdOut.print("pattern:  ");
		for(int i = 0;i<offset;i++){
			StdOut.print(" ");
		}
		StdOut.println(pat);	
		StdOut.println(offset);

	}

}

class RabinKarp{
	private String pat;
	private long patHash;
	private int R = 256;
	private long Q;
	private long RM;
	private int M;
	
	public RabinKarp(String pat) {
		this.pat = pat;
		this.M = pat.length();

		for (int i = 0; i < M; i++) {
			RM = (R * RM) % Q;
		}
		patHash = hash(pat, M - 1);

	}
	
	private long hash(String key, int M) {
		long h = 0;
		for (int i = 0; i < M; i++) {
			h = (R * h + key.charAt(i)) % Q;
		}
		return h;
	}
	
	private boolean check(String txt, int i) {
		for (int j = 0; j < M; j++)
			if (pat.charAt(j) != txt.charAt(i + j))
				return false;
		return true;
	}
	
	public int search(String txt) {
		long txtHash = hash(txt, M);
		
		if (txtHash == patHash && check(txt, 0)) {
			return 0;
		}
	}
	
	
}