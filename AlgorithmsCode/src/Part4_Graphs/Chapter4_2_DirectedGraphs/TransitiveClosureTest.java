package Part4_Graphs.Chapter4_2_DirectedGraphs;

import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class TransitiveClosureTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File tFile = new File("algs4-data//tinyDG.txt");
		
		In in = new In(tFile);
		Digraph g = new Digraph(in);

        TransitiveClosure tc = new TransitiveClosure(g);

        // print header
        StdOut.print("     ");
        for (int v = 0; v < g.V(); v++)
            StdOut.printf("%3d", v);
        StdOut.println();
        StdOut.println("--------------------------------------------");

        // print transitive closure
        for (int v = 0; v < g.V(); v++) {
            StdOut.printf("%3d: ", v);
            for (int w = 0; w < g.V(); w++) {
                if (tc.reachable(v, w)) StdOut.printf("  T");
                else                    StdOut.printf("   ");
            }
            StdOut.println();
        }
	}
}

class TransitiveClosure{
	private DirectedDFS[] all;

	public TransitiveClosure(Digraph g) {
		all = new DirectedDFS[g.V()];
		for (int v = 0; v < g.V(); v++) {
			all[v] = new DirectedDFS(g, v);
		}
	}
	
	public boolean reachable(int v, int w){
		return all[v].marked(w);
	}
}
