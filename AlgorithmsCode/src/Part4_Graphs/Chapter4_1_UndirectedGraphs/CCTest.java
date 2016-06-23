package Part4_Graphs.Chapter4_1_UndirectedGraphs;

import java.io.File;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class CCTest {
	public static void main(String[] args){
		
		File tFile = new File("algs4-data//tinyG.txt");
		In in = new In(tFile);
		Graph g = new Graph(in);
		CC c = new CC(g);
		
		int M = c.count();
		StdOut.println(M + " components");
		
		Bag<Integer>[] components = (Bag<Integer>[])new Bag[M]; 
		for (int i = 0; i < M; i++) {
			components[i] = new Bag<Integer>();
		}

		for (int v = 0; v < g.V(); v++) {
			components[c.id(v)].add(v);
		}

		for (int i = 0; i < M; i++) {
			for (int v : components[i]) {
				StdOut.print(v + " ");
			}
			StdOut.println();
		}
	}
}

class CC {
	private boolean[] marked;
	private int[] id;
	private int count;
	
	public CC(Graph g) {
		marked = new boolean[g.V()];
		id = new int[g.V()];
		count = 0;
		for (int s = 0; s < g.V(); s++) {
			if (!marked[s]) {
				dfs(g, s);
				count++;
			}
		}	
	}
	
	private void dfs(Graph g, int s) {

		marked[s] = true;
		id[s] = count;
		Iterable<Integer> sAdj = g.adj(s);
		for (int w : sAdj) {
			if (!marked[w]) {
				//id[w] = count;
				dfs(g, w);
			}
		}
	}

	public int id(int v) {
		return id[v];
	}

	public int count() {
		return count;
	}

}
