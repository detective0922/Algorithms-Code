package Part1_Fundamentals.Chapter1_3_BagsQueuesStacks;

import java.util.Iterator;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Test1_3_5 {
	
	public static void main(String args[]) {
		toBinaryString(666);
		//output: 1010011010
	}
	
	public static void toBinaryString(int N) {
		Stack<Integer> stack = new Stack<Integer>();
		while (N > 0) {
			stack.push(N % 2);
			N = N / 2;
		}
		for (int d : stack) {
			StdOut.print(d);
		}
		StdOut.println();
	}
}