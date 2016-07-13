package Part4_Graphs.Chapter4_4_ShortestPaths;

import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class DijkstraSPTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File tFile = new File("algs4-data//tinyEWD.txt");		
		In in = new In(tFile);
		EdgeWeightedDigraph g = new EdgeWeightedDigraph(in);
		
		int s = 0;
		DijkstraSP sp = new DijkstraSP(g, s);
		for (int v = 0; v < g.V(); v++) {
			StdOut.print(s + " to " + v);
			StdOut.printf(" (%4.2f): ", sp.distTo(v));
			if (sp.hasPathTo(v)) {
				for (DirectedEdge e : sp.pathTo(v)) {
					StdOut.print(e + " ");
				}
			}
			StdOut.println();
		}
	}
}

class DijkstraSP{
	
	private double[] distTo;
    private DirectedEdge[] edgeTo;
    private IndexMinPQ<Double> pq;

	public DijkstraSP(EdgeWeightedDigraph g, int s) {
		// TODO Auto-generated constructor stub
		distTo = new double[g.V()];
		edgeTo = new DirectedEdge[g.V()];
		pq = new IndexMinPQ<Double>(g.V());
		for (int v = 0; v < g.V(); v++) {
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0.0;
		
		pq.insert(s, 0.0);
		while (!pq.isEmpty()) {
			relax(g, pq.delMin());
		}
	}
	
	private void relax(EdgeWeightedDigraph g, int v) {
		for (DirectedEdge e : g.adj(v)) {
			int w = e.to();
			if (distTo[w] > distTo[v] + e.getWeight()) {
				distTo[w] = distTo[v] + e.getWeight();
				edgeTo[w] = e;
			}
		}
	}
	
	public boolean hasPathTo(int v) {
		return distTo[v] < Double.POSITIVE_INFINITY;
	}
	
	public double distTo(int v) {
		return distTo[v];
	}
	
	public Iterable<DirectedEdge> pathTo(int v) {
		Stack<DirectedEdge> stack = new Stack<DirectedEdge>();
		if (hasPathTo(v)) {
			for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
				stack.push(e);
			}
		}
		return stack;
	}
	
}
