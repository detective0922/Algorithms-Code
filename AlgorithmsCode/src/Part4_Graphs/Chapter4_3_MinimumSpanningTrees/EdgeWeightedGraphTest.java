package Part4_Graphs.Chapter4_3_MinimumSpanningTrees;

import Part4_Graphs.Chapter4_1_UndirectedGraphs.Graph;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class EdgeWeightedGraphTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class Edge{
	private int v;
	private int w;
	private double weight;
	
	public Edge(int v, int w, int weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	public int getV() {
		return v;
	}

	public int getW() {
		return w;
	}

	public double getWeight() {
		return weight;
	}
	
	public int either() {
        return v;
    }
	
	public int other(int vertex) {
        if      (vertex == v) return w;
        else if (vertex == w) return v;
    }

	public String toString() {
		return String.format("%d-%d %.5f", v, w, weight);
	}
	
}

class EdgeWeightedGraph{
	private int E;
	private int V;
	private Bag<Edge>[] adj;
	
	public EdgeWeightedGraph(int V) {
		this.V = V;
		this.E = 0;
		adj = (Bag<Edge>[]) new Bag[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new Bag<Edge>();
		}
	}
	
	public EdgeWeightedGraph(In in) {
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
	
	public void addEdge(Edge e) {
		adj[v].add(w);
		adj[w].add(v);
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
