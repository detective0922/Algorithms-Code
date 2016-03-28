package Part1_Fundamentals.Chapter1_3_BagsQueuesStacks;

import edu.princeton.cs.algs4.StdOut;

public class Test1_3_4 {
	
	public static void main(String args[]){
		String inputStr1 = "[ ( a b c ) d e f ] { g } h { i [ j ( k ) ( l ) m ] n ( o ) p }";
		String inputStr2 = "[ ( a  b c ) d e f ] { g } h { i [ j ( k ) ( l m ] n ( o ) p";
		StdOut.println(parentheses(inputStr1));
		StdOut.println(parentheses(inputStr2));
		//output:
		//true
		//false
	}
	
	public static boolean parentheses(String inputStr){
		String[] strs = inputStr.split(" ");
		Stack<String> s = new Stack<String>();
		
		for (String item : strs) {
			if (item.equals("{") || item.equals("[") || item.equals("(")) {
				s.push(item);
			} else if (item.equals("}")) {
				if (s.isEmpty() || !s.pop().equals("{")) {
					return false;
				}
			} else if (item.equals("]")) {
				if (s.isEmpty() || !s.pop().equals("[")) {
					return false;
				}
			} else if (item.equals(")")) {
				if (s.isEmpty() || !s.pop().equals("(")) {
					return false;
				}
			}
		}
		return s.isEmpty();
	}

}
