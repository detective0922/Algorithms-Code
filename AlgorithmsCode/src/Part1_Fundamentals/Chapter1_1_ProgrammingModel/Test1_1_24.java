package Part1_Fundamentals.Chapter1_1_ProgrammingModel;

import edu.princeton.cs.algs4.StdOut;

public class Test1_1_24 {

	public static void main(String args[]) {
		StdOut.println(gcd(105, 24));
		//output: 
		/*p: 105, q: 24
		p: 24, q: 9
		p: 9, q: 6
		p: 6, q: 3
		p: 3, q: 0
		3*/
		StdOut.println(gcd(1111111, 1234567));
		//output:
		/*p: 1111111, q: 1234567
		p: 1234567, q: 1111111
		p: 1111111, q: 123456
		p: 123456, q: 7
		p: 7, q: 4
		p: 4, q: 3
		p: 3, q: 1
		p: 1, q: 0
		1*/
	}

	public static int gcd(int p, int q){
		StdOut.println("p: " + p + ", q: " + q);
		if(q == 0) return p; 
		int r = p % q;
		return gcd(q, r);
	}

}
