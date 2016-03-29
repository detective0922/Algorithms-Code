package Part1_Fundamentals.Chapter1_3_BagsQueuesStacks;

import java.util.HashMap;
import java.util.Map;

import edu.princeton.cs.algs4.StdOut;

public class Test1_3_10 {
	
	public static void main(String[] args) {
		
		String inputStr = "( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )";
		StdOut.println(inputStr);	
		StdOut.println(infixToPostfix(inputStr));
		//output:
		//( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) ) 
		//12+34-56-**
    }
	
	public static String infixToPostfix(String infix){
		Map<String, Integer> priorityMap = new HashMap<String, Integer>();
		priorityMap.put("+", 0);
		priorityMap.put("-", 0);
		priorityMap.put("*", 1);
		priorityMap.put("/", 1);
		priorityMap.put("(", -1);
		
		String[] strs = infix.split(" ");
		
        Stack<String> ops  = new Stack<String>();
        StringBuffer newExpression = new StringBuffer();

		for (String s : strs) {
			
			if (s.equals("(")) {
				ops.push(s);
			} else if (s.equals("+") || s.equals("-")||s.equals("*")||s.equals("/")) {
				String opInStack = ops.peek();
				int priority = priorityMap.get(s);
				int priorityInStack = priorityMap.get(opInStack);
				if (priority > priorityInStack) {
					ops.push(s);
				}
				while (!ops.isEmpty() && priority <= priorityInStack) {
					newExpression.append(ops.pop());
					opInStack = ops.peek();
					priorityInStack = priorityMap.get(opInStack);
				}
			} else if (s.equals(")")) {
				while (!ops.isEmpty() && !ops.peek().equals("(")) {
					newExpression.append(ops.pop());
				}
				if (ops.peek().equals("("))
					ops.pop();
			} else {
				newExpression.append(s);
			}
        }
		while (!ops.isEmpty()) {
			String op = ops.pop();
			if (!op.equals("("))
				newExpression.append(op);
		}
		return newExpression.toString();	
	}

}
