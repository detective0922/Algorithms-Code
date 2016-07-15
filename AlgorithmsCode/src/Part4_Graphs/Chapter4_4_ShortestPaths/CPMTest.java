package Part4_Graphs.Chapter4_4_ShortestPaths;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import edu.princeton.cs.algs4.AcyclicLP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class CPMTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File tFile = new File("algs4-data//jobsPC.txt");
		
		try {
			System.setIn(new FileInputStream(tFile.getAbsolutePath()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int N = StdIn.readInt();
		String aa = StdIn.readLine();

        // source and sink
        int s = 2*N, t = 2*N + 1;

        EdgeWeightedDigraph g = new EdgeWeightedDigraph(2*N + 2);
        for (int i = 0; i < N; i++) {
        	String temp = StdIn.readLine();
        	String[] a = temp.split("\\s+");
            double duration = Double.parseDouble(a[0]);
            g.addEdge(new DirectedEdge(i, i+N,    duration));
            g.addEdge(new DirectedEdge(s, i, 0.0));
            g.addEdge(new DirectedEdge(i+N, t, 0.0));
            
            
			int M = StdIn.readInt();
			for (int j = 1; j < a.length; j++) {
				int successor = Integer.parseInt(a[j]);
				g.addEdge(new DirectedEdge(N + i, successor, 0.0));
			}
        }

        AcyclicLP lp = new AcyclicLP(g, s);

        // print results
        StdOut.println(" Start times:");
        for (int i = 0; i < N; i++) {
            StdOut.printf("%4d %5.1f\n", i, lp.distTo(i));
        }
        StdOut.printf("Finish time: %5.1f\n", lp.distTo(t));

	}

}
