package Part4_Graphs.Chapter4_3_MinimumSpanningTrees;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.UF;

public class KruskalMSTTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File tFile = new File("algs4-data//mediumEWG.txt");		
		In in = new In(tFile);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        Stopwatch timer = new Stopwatch();
        KruskalMST mst = new KruskalMST(G);
        StdOut.println(timer.elapsedTime());
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        
		/*edu.princeton.cs.algs4.EdgeWeightedGraph G = new edu.princeton.cs.algs4.EdgeWeightedGraph(
				in);
		edu.princeton.cs.algs4.PrimMST mst = new edu.princeton.cs.algs4.PrimMST(
				G);
		for (edu.princeton.cs.algs4.Edge e : mst.edges()) {
			StdOut.println(e);
		}*/
        StdOut.println(mst.weight());

	}

}

class KruskalMST{
	private Queue<Edge> mst;
	private MinPQ<Edge> pq;
	
	public KruskalMST(EdgeWeightedGraph g){
		mst = new Queue<Edge>();
		pq = new MinPQ<Edge>();
		UF uf = new UF(g.V());
		
		boolean[] marked = new boolean[g.V()];
		for (int v = 0; v < g.V(); v++) {
			if (!marked[v]) {
				marked[v] = true;
				for (Edge e : g.adj(v)) {
					int other = e.other(v);
					if (!marked[other]) {
						pq.insert(e);
					}
				}
			}
		}
		
		marked = new boolean[g.V()];
		while (!pq.isEmpty() && mst.size() < (g.V() - 1)) {
			Edge edge = pq.delMin();
			int either = edge.either();
			int other = edge.other(either);
			if(!uf.connected(either, other)){
				uf.union(either, other);
				mst.enqueue(edge);
			}
		}
	}
	
	public Iterable<Edge> edges(){
		return mst;
	}
	
	public double weight() {
		double weight = 0.0;
		for (Edge e : edges()) {
			weight += e.getWeight();
		}
		return weight;
	}
}
