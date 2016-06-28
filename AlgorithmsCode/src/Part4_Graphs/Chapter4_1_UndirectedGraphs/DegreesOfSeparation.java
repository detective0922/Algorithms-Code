package Part4_Graphs.Chapter4_1_UndirectedGraphs;

import edu.princeton.cs.algs4.StdOut;

public class DegreesOfSeparation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filename = "algs4-data//movies.txt";
		String delimiter = "/";
		SymbolGraph sg = new SymbolGraph(filename, delimiter);
		Graph G = sg.G();
		
		String source = "Bacon, Kevin";
		StdOut.println(source + ":");
		if (sg.contains(source)) {
			int s = sg.index(source);
			BreadthFirstPath bfs = new BreadthFirstPath(G, s);
			if (bfs.hasPathTo(s)) {
				for (int v : bfs.pathTo(s)) {
					StdOut.println("   " + sg.name(v));
				}
			} else {
				StdOut.println("no path to " + source);
			}
		} else {
			StdOut.println("input not contain '" + source + "'");
		}
		
		
	}

}
