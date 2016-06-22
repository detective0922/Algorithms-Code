package Part4_Graphs.Chapter4_1_UndirectedGraphs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class GraphTest {
	public static void main(String[] args) {

		File tFile = new File("algs4-data//leipzig1M.txt");
				
		try {
			System.setIn(new FileInputStream(tFile.getAbsolutePath()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Stopwatch timer = new Stopwatch();
		while(!StdIn.isEmpty()){

		}
	}
}

class Graph{
	private int E;
	private int V;
	private Bag<Integer>[] adj;
	
	public Graph(int V) {
		this.V = V;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new Bag<Integer>();
		}
	}
	
	public Graph(In in) {
		this(in.readInt());
		this.E = in.readInt();
		for (int i = 0; i < E; i++) {
			int v = StdIn.readInt();
			int w = StdIn.readInt();
			addEdge(v, w);
		}
	}
	
	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}
	
	public int V(){
		return V;
	}
	
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
	
	public int degree(Graph G, int v){
		int degree = 0;
		for(int w:G.adj[v]){
			degree++;
		}
		return degree;
	}
	
	public int maxDegree(Graph G){
		int max = 0;
		for (int w = 0; w < G.V(); w++) {
			int degree = degree(G, w);
			if (degree > max) {
				max = degree;
			}
		}
		return max;
	}
	
	public double avgDegree(Graph G){
		
	}
	
	public int numberOfSelfLoops(Graph G){
		
	}
	
	public String toString(){
		
	}
}