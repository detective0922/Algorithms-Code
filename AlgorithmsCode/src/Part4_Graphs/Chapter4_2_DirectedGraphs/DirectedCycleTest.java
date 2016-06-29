package Part4_Graphs.Chapter4_2_DirectedGraphs;

import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class DirectedCycleTest {

	public static void main(String[] args) {

		File tFile = new File("algs4-data//tinyG.txt");
		In in = new In(tFile);
		Digraph g = new Digraph(in);
		DirectedCycle c = new DirectedCycle(g);

		StdOut.println(c.hasCycle());
	}
}

class DirectedCycle{
	private boolean[] marked;
	private boolean hasCycle;
	
	public DirectedCycle(Digraph g) {
		marked = new boolean[g.V()];
		hasCycle = false;
		for (int v = 0; v < g.V(); v++) {
			if (!marked[v]) {
				dfs(g, v, v);
			}
		}
	}
	
	private void dfs(Digraph g, int currentV, int lastV) {

		marked[currentV] = true;
		Iterable<Integer> sAdj = g.adj(currentV);
		for (int w : sAdj) {
			if (!marked[w]) {
				dfs(g, w, lastV);
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
