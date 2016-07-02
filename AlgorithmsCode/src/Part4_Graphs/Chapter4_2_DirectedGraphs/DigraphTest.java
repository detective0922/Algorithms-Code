package Part4_Graphs.Chapter4_2_DirectedGraphs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class DigraphTest {
	public static void main(String[] args) {

		File tFile = new File("algs4-data//tinyDG.txt");
				
		In in = new In(tFile);
		Digraph g = new Digraph(in);
		System.out.println(g);
		System.out.println(g.reverse());
	}
}

class Digraph{
	private int E;
	private int V;
	private Bag<Integer>[] adj;
	
	public Digraph(int V) {
		this.V = V;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new Bag<Integer>();
		}
	}
	
	public Digraph(In in) {
		this(in.readInt());
		int edge = in.readInt();
		try {
			for (int i = 0; i < edge; i++) {
				if (in.hasNextLine()) {
					int v = in.readInt();
					int w = in.readInt();
					addEdge(v, w);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addEdge(int v, int w) {
		adj[v].add(w);
		//adj[w].add(v);
		E++;
	}
	
	public int V(){
		return V;
	}
	
	public int E(){
		return E;
	}
	
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
	
	public Digraph reverse() {
		Digraph rDigraph = new Digraph(V);
		for (int v = 0; v < V; v++) {
			for (int w : adj[v]) {
				rDigraph.addEdge(w, v);
			}
		}
		return rDigraph;
	}
	
	public String toString() {
		String s = V + " vertices, " + E + " edges\n";
		for (int v = 0; v < V; v++) {
			s += v + ": ";
			for (int w : adj[v]) {
				s += w + " ";
			}
			s += "\n";
		}
		return s;
	}
}
