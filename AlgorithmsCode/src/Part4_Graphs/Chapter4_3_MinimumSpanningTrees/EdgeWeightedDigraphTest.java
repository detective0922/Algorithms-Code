package Part4_Graphs.Chapter4_3_MinimumSpanningTrees;

import java.io.File;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

public class EdgeWeightedDigraphTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File tFile = new File("algs4-data//tinyEWG.txt");		
		In in = new In(tFile);
		EdgeWeightedGraph g = new EdgeWeightedGraph(in);
		System.out.println(g);
	}

}

class EdgeWeightedDigraph{
	public EdgeWeightedDigraph(Digraph g){
		
	}
}
