package Part4_Graphs.Chapter4_2_DirectedGraphs;

import java.io.File;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class KosarajuCCTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File tFile = new File("algs4-data//tinyDG.txt");
		In in = new In(tFile);
		Digraph g = new Digraph(in);
		KosarajuCC c = new KosarajuCC(g);
		
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

class KosarajuCC{
	private boolean[] marked;
	private int[] id;
	private int count;
	
	public KosarajuCC(Digraph g) {
		marked = new boolean[g.V()];
		id = new int[g.V()];
		count = 0;
		DepthFirstOrder dfsOrder = new DepthFirstOrder(g.reverse());
		Iterable<Integer> reversePost = dfsOrder.reversePost();
		for (int s : reversePost) {
			if (!marked[s]) {
				dfs(g, s);
				count++;
			}
		}
	}
	
	private void dfs(Digraph g, int s) {

		marked[s] = true;
		id[s] = count;
		Iterable<Integer> sAdj = g.adj(s);
		for (int w : sAdj) {
			if (!marked[w]) {
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
	
	public boolean stronglyConnected(int v, int w) {
		return id[v] == id[w];
	}
}
