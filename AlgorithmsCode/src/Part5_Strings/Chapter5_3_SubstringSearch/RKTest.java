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
	
	public RabinKarp(String pat){
		this.pat = pat;
		
		
	}
	
	private long hash(String key, int M) {
		long h = 0;
		for (int i = 0; i < M; i++) {
			h = (R * h + key.charAt(i)) % Q;
		}
		return h;
	}
	
	
}