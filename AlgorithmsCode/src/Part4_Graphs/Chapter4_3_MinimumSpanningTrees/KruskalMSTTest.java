package Part4_Graphs.Chapter4_3_MinimumSpanningTrees;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

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
		while (mst.size() < (g.V() - 1)) {
			Edge edge = pq.delMin();
			int either = edge.either();
			int other = edge.other(either);
			while (marked[either] && marked[other]) {
				edge = pq.delMin();
				either = edge.either();
				other = edge.other(either);
			}
			if(!marked[either]){
				visit(g, either);
			} else if(!marked[other]){
				visit(g, other);
			}
			mst.enqueue(edge);
		}
	}
	
	private void visit(EdgeWeightedGraph g, int v){
		marked[v] = true;
		for (Edge e : g.adj(v)) {
			int other = e.other(v);
			if (!marked[other]) {
				if (e.getWeight() < distTo[other]) {
					edgeTo[other] = e;
					distTo[other] = e.getWeight();
					if (pq.contains(other)) {
						pq.changeKey(other, e.getWeight());
					} else {
						pq.insert(other, e.getWeight());
					}
				}
			}
		}
	}
	
	public Iterable<Edge> edges(){
		List<Edge> list = new ArrayList<Edge>();
		for (Edge edge : edgeTo) {
			if (edge != null) {
				list.add(edge);
			}
		}
		return list;
		
		//return Arrays.asList(edgeTo);
	}
	
	public double weight() {
		double weight = 0.0;
		for (Edge e : edges()) {
			weight += e.getWeight();
		}
		return weight;
	}
}
