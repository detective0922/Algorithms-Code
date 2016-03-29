package Part1_Fundamentals.Chapter1_3_BagsQueuesStacks;

import edu.princeton.cs.algs4.StdOut;

public class Test1_3_9 {
	
	public static void main(String[] args) { 
		
		String inputStr = "1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )";
		String[] strs = inputStr.split(" ");
		StdOut.println(inputStr);
		
        Stack<String> ops  = new Stack<String>();
        Stack<String> vals = new Stack<String>();

		for (String s : strs) {
           
            if (s.equals("+"))         ops.push(s);
            else if (s.equals("-"))    ops.push(s);
            else if (s.equals("*"))    ops.push(s);
            else if (s.equals("/"))    ops.push(s);
            else if (s.equals("sqrt")) ops.push(s);
            else if (s.equals(")")) {
				String op = ops.pop();
				String v = vals.pop();
				v = "( " + vals.pop() + " " + op + " " + v + " )";
				vals.push(v);
            }
            else vals.push(s);
        }
		while (!vals.isEmpty()) {
			StdOut.print(vals.pop() + " ");
		}
		//output:
		//( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) ) 
    }


}
