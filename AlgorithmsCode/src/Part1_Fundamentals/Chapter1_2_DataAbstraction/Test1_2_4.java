package Part1_Fundamentals.Chapter1_2_DataAbstraction;

import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.StdOut;

public class Test1_2_4 {
	
	public static void main(String args[]) {
		String string1 = "hello";
		String string2 = string1;
		string1 = "world";
		StdOut.println(string1);
		StdOut.println(string2);
		//output:
		//world
		//hello

	}

}
