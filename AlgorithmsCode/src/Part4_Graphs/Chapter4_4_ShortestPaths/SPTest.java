package Part4_Graphs.Chapter4_4_ShortestPaths;

import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class SPTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File tFile = new File("algs4-data//tinyEWD.txt");		
		In in = new In(tFile);
		EdgeWeightedDigraph g = new EdgeWeightedDigraph(in);
		
		int s = 0;
		SP sp = new SP(g, s);
		
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

class SP{
	
	private double[] distTo;
    private DirectedEdge[] edgeTo;
    private MinPQ<DirectedEdge> pq;
    private boolean[] marked;
	
	public SP(EdgeWeightedDigraph g, int source) {
		distTo = new double[g.V()];
		edgeTo = new DirectedEdge[g.V()];
		marked = new boolean[g.V()];
		for (int v = 0; v < g.V(); v++) {
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		distTo[source] = 0.0;
		
		ShortPath(g, source);
	}
	
	private void ShortPath(EdgeWeightedDigraph g, int source) {
		relax(g, source);
		marked[source] = true;
		for (DirectedEdge e : g.adj(source)) {
			int w = e.to();
			if (!marked[w]) {
				ShortPath(g, w);
			}
		}
	}
	
	private void relax(DirectedEdge e) {
		int v = e.from(), w = e.to();
		if (distTo[w] > distTo[v] + e.getWeight()) {
			distTo[w] = distTo[v] + e.getWeight();
			edgeTo[w] = e;
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
