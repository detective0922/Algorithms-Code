package Part5_Strings.Chapter5_3_SubstringSearch;

import java.math.BigInteger;

import java.util.Random;

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
		this.Q = longRandomPrime();
		
		RM = 1;
		for (int i = 1; i < M; i++) {
			RM = (R * RM) % Q;
		}
		patHash = hash(pat, M);

	}
	
	private long hash(String key, int M) {
		long h = 0;
		for (int i = 0; i < M; i++) {
			h = (R * h + key.charAt(i)) % Q;
		}
		return h;
	}
	
	private boolean check(int i) {
        return true;
    }
	
	public int search(String txt) {
		int N = txt.length();
		
		long txtHash = hash(txt, M);
		
		if (txtHash == patHash && check(0)) {
			return 0;
		}
		
		for (int i = M; i < N; i++) {
			txtHash = (txtHash + Q - RM * txt.charAt(i - M) % Q) % Q;
			txtHash = (txtHash * R + txt.charAt(i)) % Q;
			
			int offset = i - M + 1;
			if (txtHash == patHash && check(offset)) {
				return offset;
			}
		}
		
		return N;
	}
	
	private long longRandomPrime() {
		BigInteger prime = BigInteger.probablePrime(31, new Random());
		return prime.longValue();
	}
	
	
}