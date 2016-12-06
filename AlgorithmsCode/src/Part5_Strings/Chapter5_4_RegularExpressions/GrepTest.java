package Part5_Strings.Chapter5_4_RegularExpressions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Part4_Graphs.Chapter4_2_DirectedGraphs.DigraphTest;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedDFS;
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
			
			int lp = i;
			if (re[i] == '(' || re[i] == '|') {
				ops.push(i);
			} else if (re[i] == ')') {
				int or = ops.pop();
				if (re[or] == '|') {
					lp = ops.pop();
					G.addEdge(lp, or + 1);
					G.addEdge(or, i);
				} else {
					lp = or;
				}
			}
			
			if (i < M - 1 && re[i + 1] == '*') {
				G.addEdge(lp, i + 1);
				G.addEdge(i + 1, lp);
			}
			
			if (re[i] == '(' || re[i] == '*' || re[i] == ')') {
				G.addEdge(i, i + 1);
			}
			
		}
		
	}
	
	public boolean recognizes(String txt) {
		
		Bag<Integer> pc = new Bag<Integer>();
		DirectedDFS dfs = new DirectedDFS(G, 0);
		for (int v = 0; v < G.V(); v++) {
			if (dfs.marked(v)) {
				pc.add(v);
			}
		}
		
		for (int i = 0; i < txt.length(); i++) {
			Bag<Integer> match = new Bag<Integer>();
			for (int v : pc) {
				if (v < M) {
					if (re[v] == txt.charAt(i) || re[v] == '.') {
						match.add(v + 1);
					}
				}
			}
		}

	}
	
	
	
}
