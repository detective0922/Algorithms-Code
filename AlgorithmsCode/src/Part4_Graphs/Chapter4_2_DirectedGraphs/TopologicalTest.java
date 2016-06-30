package Part4_Graphs.Chapter4_2_DirectedGraphs;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.SymbolDigraph;
import edu.princeton.cs.algs4.Topological;

public class TopologicalTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String filename  = args[0];
        String delimiter = args[1];
        SymbolDigraph sg = new SymbolDigraph(filename, delimiter);
        Topological topological = new Topological(sg.G());
        for (int v : topological.order()) {
            StdOut.println(sg.name(v));
        }
	}

}

class Topological{
	
}
