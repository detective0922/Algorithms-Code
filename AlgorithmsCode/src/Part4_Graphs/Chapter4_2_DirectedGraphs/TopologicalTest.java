package Part4_Graphs.Chapter4_2_DirectedGraphs;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.SymbolDigraph;

public class TopologicalTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String filename  = "algs4-data//jobs.txt";
        String delimiter = "/";
        SymbolDigraph sg = new SymbolDigraph(filename, delimiter);
        Topological topological = new Topological(sg.G());
        for (int v : topological.order()) {
            StdOut.println(sg.name(v));
        }
	}

}

class Topological{
	private Iterable<Integer> order;
	
	public Topological(Digraph g){
		DirectedCycle cycle = new DirectedCycle(g);
		if(!cycle.hasCycle()){
			DepthFirstOrder dfsOrder = new DepthFirstOrder(g);
			order = dfsOrder.reversePost();
		}
	}
	
	public Iterable<Integer> order(){
		return order;
	}
	
	public boolean isDAG(){
		return order != null;
	}
}
