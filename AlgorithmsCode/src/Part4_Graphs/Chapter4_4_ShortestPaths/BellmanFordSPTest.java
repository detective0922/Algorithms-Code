package Part4_Graphs.Chapter4_4_ShortestPaths;

import java.io.File;

import edu.princeton.cs.algs4.EdgeWeightedDirectedCycle;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class BellmanFordSPTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File tFile = new File("algs4-data//tinyEWDnc.txt");		
		In in = new In(tFile);
		
		int s = 0;
		
		EdgeWeightedDigraph g = new EdgeWeightedDigraph(in);
		BellmanFordSP sp = new BellmanFordSP(g, s);
		if (sp.hasNegativeCycle()) {
			for (DirectedEdge e : sp.negativeCycle())
				StdOut.println(e);
		} else {
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
		
		/*edu.princeton.cs.algs4.EdgeWeightedDigraph g = new edu.princeton.cs.algs4.EdgeWeightedDigraph(
				in);
		edu.princeton.cs.algs4.BellmanFordSP sp = new edu.princeton.cs.algs4.BellmanFordSP(g, s);
		if (sp.hasNegativeCycle()) {
			for (DirectedEdge e : sp.negativeCycle())
				StdOut.println(e);
		} else {
			for (int v = 0; v < g.V(); v++) {
				if (sp.hasPathTo(v)) {
					StdOut.printf("%d to %d (%5.2f)  ", s, v, sp.distTo(v));
					for (DirectedEdge e : sp.pathTo(v)) {
						StdOut.print(e + "   ");
					}
					StdOut.println();
				} else {
					StdOut.printf("%d to %d           no path\n", s, v);
				}
			}
		}*/
		
	}

}

class BellmanFordSP{
	
	private double[] distTo;
    private DirectedEdge[] edgeTo;
    private boolean[] onQ;
    private Queue<Integer> queue;
    private int cost;
    private Iterable<DirectedEdge> cycle;
	
	public BellmanFordSP(EdgeWeightedDigraph g, int source) {
		distTo = new double[g.V()];
		edgeTo = new DirectedEdge[g.V()];
		onQ = new boolean[g.V()];
		queue = new Queue<Integer>();
		cost = 0;
		for (int v = 0; v < g.V(); v++) {
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		distTo[source] = 0.0;
		
		queue.enqueue(source);
		onQ[source] = true;
		
		while (!queue.isEmpty() && !this.hasNegativeCycle()) {
			int w = queue.dequeue();
			onQ[w] = false;
			relax(g, w);
		}
		
	}
	
	private void relax(EdgeWeightedDigraph g, int v) {
		for (DirectedEdge e : g.adj(v)) {
			int w = e.to();
			if (distTo[w] > distTo[v] + e.weight()) {
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
				if (!onQ[w]) {
					queue.enqueue(w);
					onQ[w] = true;
				}
			}
			cost++;
			if (cost % g.V() == 0) {
				findNegativeCycle();
			}
		}
	}
	
	private void findNegativeCycle() {
		int v = edgeTo.length;
		edu.princeton.cs.algs4.EdgeWeightedDigraph ewd = new edu.princeton.cs.algs4.EdgeWeightedDigraph(v);
		for (int i = 0; i < distTo.length; i++) {
			if (edgeTo[i] != null) {
				ewd.addEdge(edgeTo[i]);
			}
		}
		EdgeWeightedDirectedCycle cf = new EdgeWeightedDirectedCycle(ewd);
		cycle = cf.cycle();
	}
	
	public boolean hasNegativeCycle() {
		return cycle != null;
	}
	
	public Iterable<DirectedEdge> negativeCycle(){
		return cycle;
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
