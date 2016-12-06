package Part5_Strings.Chapter5_4_RegularExpressions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Part4_Graphs.Chapter4_2_DirectedGraphs.DigraphTest;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class GrepTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String regexp = "(A*B|AC)D";
		File tFile = new File("algs4-data//tinyL.txt");		
		In in = new In(tFile);
		/*try {
			System.setIn(new FileInputStream(tFile.getAbsolutePath()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		NFA nfa = new NFA(regexp);
		while(in.hasNextLine()){
			String txt = in.readLine();
			if(nfa.recognizes(txt)){
				StdOut.println(txt);
			}
		}

	}
}

class NFA {
	private char[] re;
	private Digraph G;
	private int M;
	
	public NFA(String regexp){
		re = regexp.toCharArray();
		M = re.length;
		G = new Digraph(M + 1);

		Stack<Integer> ops = new Stack<Integer>();
		
		for (int i = 0; i < M; i++) {
			
			if (re[i] == '(' || re[i] == '|') {
				ops.push(i);
			} else if (re[i] == ')') {
				
			}
			
		}
		
	}
	
	public boolean recognizes(String txt){
		
	}
	
	
	
}
