package Part4_Graphs.Chapter4_4_ShortestPaths;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import edu.princeton.cs.algs4.BellmanFordSP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ArbitrageTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		File tFile = new File("algs4-data//rates.txt");

		try {
			System.setIn(new FileInputStream(tFile.getAbsolutePath()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int V = StdIn.readInt();
        String[] name = new String[V];

        EdgeWeightedDigraph G = new EdgeWeightedDigraph(V);
        for (int v = 0; v < V; v++) {
            name[v] = StdIn.readString();
            for (int w = 0; w < V; w++) {
                double rate = StdIn.readDouble();
                DirectedEdge e = new DirectedEdge(v, w, -Math.log(rate));
                G.addEdge(e);
            }
        }

        BellmanFordSP spt = new BellmanFordSP(G, 0);
		if (spt.hasNegativeCycle()) {
			double stake = 1000.0;
			for (DirectedEdge e : spt.negativeCycle()) {
				StdOut.printf("%10.5f %s ", stake, name[e.from()]);
				stake *= Math.exp(-e.weight());
				StdOut.printf("= %10.5f %s\n", stake, name[e.to()]);
			}
		} else {
			StdOut.println("No arbitrage opportunity");
		}

	}

}
