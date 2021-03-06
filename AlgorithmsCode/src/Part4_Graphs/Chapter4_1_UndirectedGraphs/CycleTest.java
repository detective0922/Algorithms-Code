package Part4_Graphs.Chapter4_1_UndirectedGraphs;

import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class CycleTest {

	public static void main(String[] args) {

		File tFile = new File("algs4-data//tinyG.txt");
		In in = new In(tFile);
		Graph g = new Graph(in);
		Cycle c = new Cycle(g);

		StdOut.println(c.hasCycle());
	}
}

class Cycle{
	private boolean[] marked;
	private boolean hasCycle;
	
	public Cycle(Graph g) {
		marked = new boolean[g.V()];
		hasCycle = false;
		for (int v = 0; v < g.V(); v++) {
			if (!marked[v]) {
				dfs(g, v, v);
			}
		}
	}
	
	private void dfs(Graph g, int currentV, int lastV) {

		marked[currentV] = true;
		Iterable<Integer> sAdj = g.adj(currentV);
		for (int w : sAdj) {
			if (!marked[w]) {
				dfs(g, w, currentV);
			} else if( w != lastV){
				hasCycle = true;
			}
		}
	}
	
	public boolean marked(int v) {
		return marked[v];
	}
	
	public boolean hasCycle(){
		return hasCycle;
	}
	
	
}
