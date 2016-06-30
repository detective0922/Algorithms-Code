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
		pre = new Queue<Integer>();
		post = new Queue<Integer>();
		reversePost = new Stack<Integer>();
		for (int v = 0; v < g.V(); v++) {
			if (!marked[v]) {
				dfs(g, v);
			}
		}
	}
	
	private void dfs(Digraph g, int s) {

		pre.enqueue(s);
		marked[s] = true;
		Iterable<Integer> sAdj = g.adj(s);
		for (int w : sAdj) {
			if (!marked[w]) {
				dfs(g, w);
			}
		}
		post.enqueue(s);
		reversePost.push(s);
	}
	
	public Iterable<Integer> pre(){
		return pre;
	}

}
