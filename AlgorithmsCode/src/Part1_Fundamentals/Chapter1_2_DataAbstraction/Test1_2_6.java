package Part1_Fundamentals.Chapter1_2_DataAbstraction;

import edu.princeton.cs.algs4.StdOut;

public class Test1_2_6 {
	
	public static void main(String args[]) {
		String s = "ACTGACG";
		String t = "TGACGAC";
		String s2 = "abba";
		String t2 = "abab";
		StdOut.println(isPalindrome(s, t));
		StdOut.println(isPalindrome(s2, t2));
		//output:
		//true
		//false

	}
	
	public static boolean isPalindrome(String s, String t){
		if(s.length() == t.length()){
			String ss = s + s;
			String tt = t + t;		
			if(ss.indexOf(t)>=0 && tt.indexOf(s)>=0)
				return true;
		}
		
		return false;
	}

}
