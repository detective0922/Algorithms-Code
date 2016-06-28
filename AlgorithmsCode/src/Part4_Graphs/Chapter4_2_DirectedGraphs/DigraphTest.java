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
		Diraph g = new Diraph(in);
		System.out.println(g);
	}
}

class Diraph{
	private int E;
	private int V;
	private Bag<Integer>[] adj;
	
	public Diraph(int V) {
		this.V = V;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new Bag<Integer>();
		}
	}
	
	public Diraph(In in) {
		this(in.readInt());
		this.E = in.readInt();
		try {
			for (int i = 0; i < E; i++) {
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
	
	public int degree(Graph G, int v){
		int degree = 0;
		for (int w : G.adj[v]) {
			degree++;
		}
		return degree;
	}
	
	public int maxDegree(Graph G){
		int max = 0;
		for (int v = 0; v < G.V(); v++) {
			int degree = degree(G, v);
			if (degree > max) {
				max = degree;
			}
		}
		return max;
	}
	
	public double avgDegree(Graph G) {
		return 2 * G.E() / G.V();
	}
	
	public int numberOfSelfLoops(Graph G){
		int count = 0;
		for(int v = 0;v<G.V();v++){
			for(int w: G.adj[v]){
				if(v == w){
					count++;
				}
			}
		}
		return count / 2;
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
