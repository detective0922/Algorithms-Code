package Part4_Graphs.Chapter4_1_UndirectedGraphs;

import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class TwoColorTest {
	public static void main(String[] args) {

		File tFile = new File("algs4-data//tinyG.txt");
		In in = new In(tFile);
		Graph g = new Graph(in);
		TwoColor c = new TwoColor(g);

		StdOut.println(c.isTwoColor());
	}
}

class TwoColor{
	private boolean[] marked;
	private boolean[] colors;
	private boolean isTwoColor;
	
	public TwoColor(Graph g) {
		marked = new boolean[g.V()];
		colors = new boolean[g.V()];
		isTwoColor = true;
		for (int v = 0; v < g.V(); v++) {
			if (!marked[v]) {
				dfs(g, v);
			}
		}
	}
	
	private void dfs(Graph g, int currentV) {

		marked[currentV] = true;
		Iterable<Integer> sAdj = g.adj(currentV);
		for (int w : sAdj) {
			if (!marked[w]) {
				colors[w] = !colors[currentV];
			} else if (colors[w] == colors[currentV]) {
				isTwoColor = false;
			}
		}
	}
	
	public boolean marked(int v) {
		return marked[v];
	}
	
	public boolean isTwoColor(){
		return isTwoColor;
	}
}
