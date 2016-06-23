package Part4_Graphs.Chapter4_1_UndirectedGraphs;

import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class SearchTest {
	public static void main(String[] args){
		
		File tFile = new File("algs4-data//tinyG.txt");
		In in = new In(tFile);
		Graph g = new Graph(in);
		int s = 0;
		DepthFirstSearch search = new DepthFirstSearch(g, s);
		
		for(int v = 0; v<g.V();v++){
			if(search.marked(v)){
				StdOut.print(v+" ");
			}
		}
		StdOut.println();
		
		if(search.count()!=g.V()){
			StdOut.println("Not connected");
		}			
	}
}

class DepthFirstSearch {
	private boolean[] marked;
	private int count;
	
	public DepthFirstSearch(Graph g, int s) {
		marked = new boolean[g.V()];
		count = 0;
		dfs(g, s);
	}
	
	
	
	public boolean marked(int v) {
		return marked[v];
	}
	
	public int count(){
		return count;
	}

}
