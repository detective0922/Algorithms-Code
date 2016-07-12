package Part4_Graphs.Chapter4_4_ShortestPaths;

import java.io.File;

import edu.princeton.cs.algs4.In;
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
			StdOut.printf(" (%4.2f): ", sp.distTo());
			if (sp.hasPathTo()) {
				for (DirectedEdge e : sp.pathTo()) {
					StdOut.print(e + " ");
				}
			}
		}
		

	}

}

class SP{
	public boolean hasPathTo() {
		
	}
	
	public double distTo() {
		
	}
	
	public Iterable<DirectedEdge> pathTo() {
		
	}
}
