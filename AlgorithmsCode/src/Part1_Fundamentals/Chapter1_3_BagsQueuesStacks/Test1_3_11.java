package Part1_Fundamentals.Chapter1_3_BagsQueuesStacks;

import java.util.HashMap;
import java.util.Map;

import edu.princeton.cs.algs4.StdOut;

public class Test1_3_11 {
	
	public static void main(String[] args) {
		
		
		
		String infix = "( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )";
		StdOut.println(infix);
		String postfixStr = Test1_3_10.infixToPostfix(infix);
		StdOut.println(postfixStr);
		char[] chars = postfixStr.toCharArray();
		String[] postfix = new String[chars.length];
		for (int i = 0; i < chars.length; i++) {
			postfix[i] = String.valueOf(chars[i]);
		}
		StdOut.println(evaluatePostfix(postfix));      		
		//output:
		//( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )
		//12+34-56-**
		//3.0
    }
	
	public static double evaluatePostfix(String[] postfix){
		
		double val1 = 0;
		double val2 = 0;
        Stack<Double> vals = new Stack<Double>();
        
		for (String s : postfix) {
			if(s.equals("+")) {
				val1 =  vals.pop();
				val2 = vals.pop();
				vals.push(val2 + val1);
			} else if (s.equals("-")) {
				val1 =  vals.pop();
				val2 = vals.pop();
				vals.push(val2 - val1);
			} else if (s.equals("*")) {
				val1 =  vals.pop();
				val2 = vals.pop();
				vals.push(val2 * val1);
			} else if (s.equals("/")) {
				val1 =  vals.pop();
				val2 = vals.pop();
				vals.push(val2 / val1);
			} else {
				vals.push(Double.valueOf(s));
			}
		}
		return vals.pop();
	
	}

}
