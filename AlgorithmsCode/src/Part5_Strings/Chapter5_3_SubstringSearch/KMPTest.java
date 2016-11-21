package Part5_Strings.Chapter5_3_SubstringSearch;

import edu.princeton.cs.algs4.StdOut;

public class KMPTest {
	
	public static void main(String[] args) {
		
		String txt = "";
		String pat = "";
		KMP kmp = new KMP(pat);
		int offset = kmp.search(txt);
		StdOut.println("text:     " + txt);
		StdOut.println("pattern:  " + txt);
		for(int i = 0;i<offset;i++){
			StdOut.print(" ");
		}
		StdOut.println(pat);	
	}
	
}


class KMP{
	
	public KMP(String pat) {

	}
	
	public int search(String txt){
		
	}

}

