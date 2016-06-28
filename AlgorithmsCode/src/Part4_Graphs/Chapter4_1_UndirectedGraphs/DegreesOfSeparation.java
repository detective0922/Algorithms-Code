package Part4_Graphs.Chapter4_1_UndirectedGraphs;

import edu.princeton.cs.algs4.StdOut;


public class DegreesOfSeparation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*String filename = "algs4-data//routes.txt";
		String delimiter = " ";*/
		String filename = "algs4-data//movies.txt";
		String delimiter = "/";
		SymbolGraph sg = new SymbolGraph(filename, delimiter);
		Graph G = sg.G();
		
		//String source = "JFK";
		String source = "Bacon, Kevin";
		//String source = "Animal House (1978)";
		BreadthFirstPath bfs = null;		
		if (sg.contains(source)) {
			int s = sg.index(source);
			bfs = new BreadthFirstPath(G, s);
		} else {
			StdOut.println("input not contain '" + source + "'");
		}	
		
		//String sink = "LAS";
		//String sink = "DFW";
		String sink = "Kidman, Nicole";
		//String sink = "Grant, Cary";
		//String sink = "Titanic (1997)";
		//String sink = "To Catch a Thief (1955)";
		StdOut.println(sink + ":");
		if (sg.contains(sink)) {
			int t = sg.index(sink);
			if (bfs.hasPathTo(t)) {
				for (int v : bfs.pathTo(t)) {
					StdOut.println("   " + sg.name(v));
				}
			} else {
				StdOut.println("no path to " + source);
			}
		} else {
			StdOut.println("input not contain '" + sink + "'");
		}	
		
	}

}
