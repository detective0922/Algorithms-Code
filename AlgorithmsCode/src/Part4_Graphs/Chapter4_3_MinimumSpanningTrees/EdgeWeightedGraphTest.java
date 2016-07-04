package Part4_Graphs.Chapter4_3_MinimumSpanningTrees;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class EdgeWeightedGraphTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class Edge implements Comparable<Edge>{
	private int v;
	private int w;
	private double weight;
	
	public Edge(int v, int w, double weight) {
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
        else return -1;
    }

	public String toString() {
		return String.format("%d-%d %.5f", v, w, weight);
	}

	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		if (weight < o.getWeight())
			return -1;
		else if (weight > o.getWeight())
			return 1;
		else
			return 0;
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
					double weight = in.readDouble();
					Edge e = new Edge(v, w, weight);
					addEdge(e);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addEdge(Edge e) {
		int v = e.either();
		int w = e.other(v);
		adj[v].add(e);
		adj[w].add(e);
		E++;
	}
	
	public int V(){
		return V;
	}
	
	public int E(){
		return E;
	}
	
	public Iterable<Edge> adj(int v){
		return adj[v];
	}
	
	public String toString() {
		String s = V + " vertices, " + E + " edges\n";
		for (int v = 0; v < V; v++) {
			s += v + ": ";
			for (Edge e : adj[v]) {
				s += e + " ";
			}
			s += "\n";
		}
		return s;
	}
}
