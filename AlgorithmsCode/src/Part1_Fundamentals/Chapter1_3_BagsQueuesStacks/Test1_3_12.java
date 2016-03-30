package Part1_Fundamentals.Chapter1_3_BagsQueuesStacks;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;

public class Test1_3_12 {
	
public static void main(String[] args) {
		
		
		
		String infix = "( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )";
		StdOut.println(infix);
		String[] strs = infix.split(" ");
		Stack<String> stack = new Stack<String>();
		for (String item : strs) {
			stack.push(item);
		}
		
		for (String str : copyForeach(stack)) {
			StdOut.print(str);
		}
		StdOut.println();
		for (String str : copyItr(stack)) {
			StdOut.print(str);
		}
		//output:
		//( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )
		//((1+2)*((3-4)*(5-6)))
		//((1+2)*((3-4)*(5-6)))
    }
	
	public static Stack<String> copyForeach(Stack<String> stack){
		Stack<String> copy = new Stack<String>();
		for (String str : stack) {
			copy.push(str);
		}
		return copy;
	}
	
	public static Stack<String> copyItr(Stack<String> stack){
		Stack<String> copy = new Stack<String>();
		Iterator<String> itr = stack.iterator();
		while (itr.hasNext()) {
			copy.push((String) itr.next());
		}
		return copy;
	}

}
