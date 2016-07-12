package Part4_Graphs.Chapter4_3_MinimumSpanningTrees;

import java.io.File;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class EdgeWeightedDigraphTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File tFile = new File("algs4-data//tinyEWG.txt");		
		In in = new In(tFile);
		EdgeWeightedGraph g = new EdgeWeightedGraph(in);
		System.out.println(g);
	}

}

class EdgeWeightedDigraph{
	private int E;
	private int V;
	private Bag<DirectedEdge>[] adj;
	
	public EdgeWeightedDigraph(int V) {
		this.V = V;
		this.E = 0;
		adj = (Bag<DirectedEdge>[]) new Bag[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new Bag<DirectedEdge>();
		}
	}
	
	public EdgeWeightedDigraph(In in) {
		this(in.readInt());
		int edge = in.readInt();
		try {
			for (int i = 0; i < edge; i++) {
				if (in.hasNextLine()) {
					int v = in.readInt();
					int w = in.readInt();
					double weight = in.readDouble();
					DirectedEdge e = new DirectedEdge(v, w, weight);
					addEdge(e);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addEdge(DirectedEdge e) {
		int v = e.from();
		adj[v].add(e);
		E++;
	}
	
	public int V(){
		return V;
	}
	
	public int E(){
		return E;
	}
	
	public Iterable<DirectedEdge> adj(int v){
		return adj[v];
	}
	
	public String toString() {
		String s = V + " vertices, " + E + " edges\n";
		for (int v = 0; v < V; v++) {
			s += v + ": ";
			for (DirectedEdge e : adj[v]) {
				s += e + " ";
			}
			s += "\n";
		}
		return s;
	}
}
