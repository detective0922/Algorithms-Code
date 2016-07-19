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
		StdIn.readLine();

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
            
          //in book, it is j=1, that is incorrect
			for (int j = 2; j < a.length; j++) {
				int successor = Integer.parseInt(a[j]);
				g.addEdge(new DirectedEdge(N + i, successor, 0.0));
			}
        }

        StdOut.println(g);
        AcyclicLP lp = new AcyclicLP(g, s);

        // print results
        StdOut.println(" Start times:");
        for (int i = 0; i < N; i++) {
            StdOut.printf("%4d %5.1f\n", i, lp.distTo(i));
        }
        StdOut.printf("Finish time: %5.1f\n", lp.distTo(t));
		
		// number of jobs
        /*int N = StdIn.readInt();

        // source and sink
        int source = 2*N;
        int sink   = 2*N + 1;

        // build network
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(2*N + 2);
        for (int i = 0; i < N; i++) {
            double duration = StdIn.readDouble();
            G.addEdge(new DirectedEdge(source, i, 0.0));
            G.addEdge(new DirectedEdge(i+N, sink, 0.0));
            G.addEdge(new DirectedEdge(i, i+N,    duration));

            // precedence constraints
            int M = StdIn.readInt();
            for (int j = 0; j < M; j++) {
                int precedent = StdIn.readInt();
                G.addEdge(new DirectedEdge(N+i, precedent, 0.0));
            }
        }

        StdOut.println(G);
        // compute longest path
        AcyclicLP lp = new AcyclicLP(G, source);

        // print results
        StdOut.println(" job   start  finish");
        StdOut.println("--------------------");
        for (int i = 0; i < N; i++) {
            StdOut.printf("%4d %7.1f %7.1f\n", i, lp.distTo(i), lp.distTo(i+N));
        }
        StdOut.printf("Finish time: %7.1f\n", lp.distTo(sink));*/

	}

}
