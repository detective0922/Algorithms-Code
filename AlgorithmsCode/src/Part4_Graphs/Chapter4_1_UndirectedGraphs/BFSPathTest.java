package Part4_Graphs.Chapter4_1_UndirectedGraphs;

import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class BFSPathTest {
	public static void main(String[] args){
		
		File tFile = new File("algs4-data//tinyCG.txt");
		In in = new In(tFile);
		Graph g = new Graph(in);
		int s = 0;
		BreadthFirstPath search = new BreadthFirstPath(g, s);
		
		for (int v = 0; v < g.V(); v++) {
			StdOut.print(s + " to " + v + ": ");
			if (search.hasPathTo(v)) {
				StdOut.print(s);
				Iterable<Integer> path = search.pathTo(v);
				for (int w : path) {
					if (w == s) {
						continue;
					} else {
						StdOut.print("-" + w);
					}
				}
			}
			StdOut.println();
		}	
	}
}

class BreadthFirstPath {
	private boolean[] marked;
	private int[] edgeTo;
	private final int s;
	
	public BreadthFirstPath(Graph g, int s) {
		marked = new boolean[g.V()];
		edgeTo = new int[g.V()];
		this.s = 0;
		bfs(g, s);
	}
	
	private void bfs(Graph g, int s) {

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
