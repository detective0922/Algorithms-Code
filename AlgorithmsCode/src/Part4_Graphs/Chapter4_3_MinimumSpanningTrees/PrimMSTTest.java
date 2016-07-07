package Part4_Graphs.Chapter4_3_MinimumSpanningTrees;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class PrimMSTTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File tFile = new File("algs4-data//tinyEWG.txt");		
		In in = new In(tFile);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        Stopwatch timer = new Stopwatch();
        PrimMST mst = new PrimMST(G);
        StdOut.println(timer.elapsedTime());
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.println(mst.weight());

	}

}

class PrimMST{
	private Edge[] edgeTo;
	private double[] distTo;
	private boolean[] marked;
	private IndexMinPQ<Double> pq;
	
	public PrimMST(EdgeWeightedGraph g){
		edgeTo = new Edge[g.V()];
		distTo = new double[g.V()];
		marked = new boolean[g.V()];
		for (int v = 0; v < g.V(); v++) {
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		pq = new IndexMinPQ<Double>(g.V());
		
		distTo[0] = 0.0;
		pq.insert(0, distTo[0]);
		while(!pq.isEmpty()){
			int v = pq.delMin();
			visit(g, v);		
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
			list.add(edge);
		}
		return list;
		
		//return Arrays.asList(edgeTo);
	}
	
	public double weight(){
		double weight = 0;
		for (double d : distTo) {
			weight += d;
		}
		return weight;
	}
}
