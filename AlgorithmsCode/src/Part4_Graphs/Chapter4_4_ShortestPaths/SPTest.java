package Part4_Graphs.Chapter4_4_ShortestPaths;

import java.io.File;

import edu.princeton.cs.algs4.In;
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
		}
	}

}

class SP{
	
	private double[] distTo;
    private DirectedEdge[] edgeTo;
	
	public SP(EdgeWeightedDigraph g, int v) {

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
