package Part4_Graphs.Chapter4_2_DirectedGraphs;

import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class DirectedCycleTest {

	public static void main(String[] args) {

		File tFile = new File("algs4-data//tinyDG.txt");
		In in = new In(tFile);
		Digraph g = new Digraph(in);
		DirectedCycle c = new DirectedCycle(g);

		StdOut.println(c.hasCycle());
		if (c.hasCycle()) {
            for (int v : c.cycle()) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
	}
}

class DirectedCycle{
	private boolean[] marked;
	private int[] edgeTo;
	private Stack<Integer> cycle;
	private boolean hasCycle;
	
	public DirectedCycle(Digraph g) {
		marked = new boolean[g.V()];
		edgeTo = new int[g.V()];
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
				edgeTo[w] = currentV;
				dfs(g, w, currentV);
			} else if( w != lastV){
				hasCycle = true;
				cycle = new Stack<Integer>();
				for (int x = currentV; x != w; x = edgeTo[x]) {
					cycle.push(x);
				}
				cycle.push(w);
				cycle.push(currentV);
			}
		}
	}
	
	public boolean marked(int v) {
		return marked[v];
	}
	
	public boolean hasCycle(){
		return hasCycle;
	}
	
	public Iterable<Integer> cycle() {
		return cycle;
	}
	
	
}
