package Part1_Fundamentals.Chapter1_3_BagsQueuesStacks;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Test1_3_6 {
	
	public static void main(String args[]) {
		String inputStr = "It was the best of times it was the worst of times";
		String[] strs = inputStr.split(" ");
		Queue<String> q = new Queue<String>();
		for (String item : strs) {
			q.enqueue(item);
		}
		for (String item : q) {
			StdOut.print(item + " ");
		}
		StdOut.println();
		reverseQueue(q);
		for (String item : q) {
			StdOut.print(item + " ");
		}
		//output:
		//It was the best of times it was the worst of times 
		//times of worst the was it times of best the was It 
	}
	
	public static void reverseQueue(Queue<String> q){
		Stack<String> stack = new Stack<String>();
		while(!q.isEmpty()){
			stack.push(q.dequeue());
		}
		while(!stack.isEmpty()){
			q.enqueue(stack.pop());
		}
	}

}
