package Part4_Graphs.Chapter4_2_DirectedGraphs;

import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class DirectedDFSTest {
	public static void main(String[] args){
		
		File tFile = new File("algs4-data//tinyG.txt");
		In in = new In(tFile);
		Digraph g = new Digraph(in);
		int s = 9;
		DirectedDFS search = new DirectedDFS(g, s);
		
		for(int v = 0; v<g.V();v++){
			if(search.marked(v)){
				StdOut.print(v+" ");
			}
		}
		StdOut.println();
		
		if(search.count()!=g.V()){
			StdOut.println("Not connected");
		}
		
		//output:
		//s=0:0 1 2 3 4 5 6 
		//Not connected
		//s-9:9 10 11 12 
		//Not connected
	}
}

class DirectedDFS {
	private boolean[] marked;
	private int count;
	
	public DirectedDFS(Digraph g, int s) {
		marked = new boolean[g.V()];
		count = 0;
		dfs(g, s);
	}
	
	public DirectedDFS(Digraph g, Iterable<Integer> sources) {
		marked = new boolean[g.V()];
		count = 0;
		for(int s: sources){
			dfs(g, s);
		}
	}
	
	private void dfs(Digraph g, int s) {

		marked[s] = true;
		count++;
		Iterable<Integer> sAdj = g.adj(s);
		for (int w : sAdj) {
			if (!marked[w]) {
				dfs(g, w);
			}
		}
	}
	
	public boolean marked(int v) {
		return marked[v];
	}
	
	public int count(){
		return count;
	}

}
