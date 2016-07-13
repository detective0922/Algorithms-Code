package Part4_Graphs.Chapter4_4_ShortestPaths;

import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
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
    private IndexMinPQ<DirectedEdge> pq;

	public DijkstraSP(EdgeWeightedDigraph g, int s) {
		// TODO Auto-generated constructor stub
	}
	
}
