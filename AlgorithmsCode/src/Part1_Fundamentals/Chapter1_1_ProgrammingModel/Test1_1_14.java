package Part1_Fundamentals.Chapter1_1_ProgrammingModel;

import edu.princeton.cs.algs4.StdOut;

public class Test1_1_14 {

	public static void main(String args[]) {
		StdOut.println(lg(5));
		//output: 2
	}

	public static int lg(int n){
		int i = 0;
		int m = 2;
		while (m <= n) {
			m = m*2;
			i++;
		}
		return i;
	}

}
