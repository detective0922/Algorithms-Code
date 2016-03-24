package Part1_Fundamentals.Chapter1_1_ProgrammingModel;

import edu.princeton.cs.algs4.StdOut;

public class Test1_1_18 {

	public static void main(String args[]) {
		StdOut.println(mystery(2, 25));
		StdOut.println(mystery(3, 11));
		//output: 50 33
		StdOut.println(mysteryPlus(2, 25));
		StdOut.println(mysteryPlus(3, 11));
		//output: 33554432 177147
	}

	public static int mystery(int a, int b) {
		if (b == 0)
			return 0;
		if (b % 2 == 0)
			return mystery(a + a, b / 2);
		return mystery(a + a, b / 2) + a;

	}
	
	public static int mysteryPlus(int a, int b) {
		if (b == 0)
			return 1;
		if (b % 2 == 0)
			return mysteryPlus(a * a, b / 2);
		return mysteryPlus(a * a, b / 2) * a;

	}

}
