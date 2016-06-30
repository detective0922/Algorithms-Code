package Part4_Graphs.Chapter4_2_DirectedGraphs;

import java.io.File;

import edu.princeton.cs.algs4.DepthFirstOrder;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class DFSOrderTest {
	public static void main(String[] args){
		
		File tFile = new File("algs4-data//tinyCG.txt");
		In in = new In(tFile);
		Digraph G = new Digraph(in);

		DepthFirstOrder dfs = new DepthFirstOrder(G);
		StdOut.println("   v  pre post");
		StdOut.println("--------------");
		for (int v = 0; v < G.V(); v++) {
			StdOut.printf("%4d %4d %4d\n", v, dfs.pre(v), dfs.post(v));
		}

		StdOut.print("Preorder:  ");
		for (int v : dfs.pre()) {
			StdOut.print(v + " ");
		}
		StdOut.println();

		StdOut.print("Postorder: ");
		for (int v : dfs.post()) {
			StdOut.print(v + " ");
		}
		StdOut.println();

		StdOut.print("Reverse postorder: ");
		for (int v : dfs.reversePost()) {
			StdOut.print(v + " ");
		}
		StdOut.println();

	}
}

class DepthFirstOrder {
	private boolean[] marked;
	private Queue<Integer> pre;
	private Queue<Integer> post;
	private Stack<Integer> reversePost;
	
	public DepthFirstOrder(Digraph g) {
		marked = new boolean[g.V()];
		edgeTo = new int[g.V()];
		this.s = s;
		dfs(g, s);
	}
	
	private void dfs(Graph g, int s) {

		marked[s] = true;
		Iterable<Integer> sAdj = g.adj(s);
		for (int w : sAdj) {
			if (!marked[w]) {
				edgeTo[w] = s;
				dfs(g, w);
			}
		}
	}
	
	public boolean hasPathTo(int v) {
		return marked[v];
	}
	
	public Iterable<Integer> pathTo(int v){
		if(!hasPathTo(v)){
			return null;
		}
		Stack<Integer> stack = new Stack<Integer>();
		/*stack.push(v);
		while(edgeTo[v]!=s){
			stack.push(edgeTo[v]);
			v = edgeTo[v];
		}*/
		for(int x = v; x!=s;x = edgeTo[x]){
			stack.push(x);
		}
		stack.push(s);
		return stack;
	}

}
