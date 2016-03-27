package Part1_Fundamentals.Chapter1_2_DataAbstraction;

import edu.princeton.cs.algs4.StdOut;

public class Test1_2_7 {
	
	public static void main(String args[]) {
		String s = "ACTGACG";
		StdOut.println(mystery(s));
		//output:
		//ACTGACG

	}
	
	public static String mystery(String s){
		int N = s.length();
		if(N<=1) return s;
		String a = s.substring(0, N/2);
		String b = s.substring(N/2, N);	
		return mystery(a) + mystery(b);
	}


}
