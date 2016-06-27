package Part4_Graphs.Chapter4_1_UndirectedGraphs;

import edu.princeton.cs.algs4.StdOut;

public class SymbolGraphTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filename = args[0];
		String delimiter = args[1];
		SymbolGraph sg = new SymbolGraph(filename, delimiter);
		Graph G = sg.G();

		String source = "";
		if (sg.contains(source)) {
			int s = sg.index(source);
			for (int v : G.adj(s)) {
				StdOut.println("   " + sg.name(v));
			}
		} else {
			StdOut.println("input not contain '" + source + "'");
		}

	}
}

class SymbolGraph{
	
}
