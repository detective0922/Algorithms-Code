package Part4_Graphs.Chapter4_3_MinimumSpanningTrees;

import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class MSTTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File tFile = new File("algs4-data//tinyEWG.txt");		
		In in = new In(tFile);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        MST mst = new MST(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.println(mst.weight());
	}

}

class MST{
	public MST(EdgeWeightedGraph g){
		
	}
	
	public Iterable<Edge> edges(){
		return null;
	}
	
	public double weight(){
		return 0;
	}
	
}
