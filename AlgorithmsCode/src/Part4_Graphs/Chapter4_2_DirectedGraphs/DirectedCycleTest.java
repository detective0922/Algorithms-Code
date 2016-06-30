package Part4_Graphs.Chapter4_2_DirectedGraphs;

import java.io.File;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class DirectedCycleTest {

	public static void main(String[] args) {

		File tFile = new File("algs4-data//tinyDG.txt");
		In in = new In(tFile);
		Digraph g = new Digraph(in);
		DirectedCycle c = new DirectedCycle(g);

		StdOut.println(c.hasCycle());
		if (c.hasCycle()) {
            for (int v : c.cycle()) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
	}
}

class DirectedCycle{
	private boolean[] marked;
	private int[] edgeTo;
	private Stack<Integer> cycle;
	private boolean[] onStack;
	private boolean hasCycle;
	
	public DirectedCycle(Digraph g) {
		marked = new boolean[g.V()];
		edgeTo = new int[g.V()];
		onStack = new boolean[g.V()];
		hasCycle = false;
		for (int v = 0; v < g.V(); v++) {
			if (!marked[v]) {
				dfs(g, v, v);
			}
		}
	}
	
	private void dfs(Digraph g, int currentV, int lastV) {

		onStack[currentV] = true;
		marked[currentV] = true;
		Iterable<Integer> sAdj = g.adj(currentV);
		for (int w : sAdj) {
			if(hasCycle){
				return;
			} else if (!marked[w]) {
				edgeTo[w] = currentV;
				dfs(g, w, currentV);
			} else if( /*w != lastV*/onStack[w]){
				hasCycle = true;
				cycle = new Stack<Integer>();
				for (int x = currentV; x != w; x = edgeTo[x]) {
					cycle.push(x);
				}
				cycle.push(w);
				cycle.push(currentV);
			}
		}
		onStack[currentV] = false;
	}
	
	public boolean marked(int v) {
		return marked[v];
	}
	
	public boolean hasCycle(){
		return hasCycle;
	}
	
	public Iterable<Integer> cycle() {
		return cycle;
	}
}

class DirectedCycleInBook {
    private boolean[] marked;        // marked[v] = has vertex v been marked?
    private int[] edgeTo;            // edgeTo[v] = previous vertex on path to v
    private boolean[] onStack;       // onStack[v] = is vertex on the stack?
    private Stack<Integer> cycle;    // directed cycle (or null if no such cycle)

    /**
     * Determines whether the digraph <tt>G</tt> has a directed cycle and, if so,
     * finds such a cycle.
     * @param G the digraph
     */
    public DirectedCycleInBook(Digraph G) {
        marked  = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo  = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v]) dfs(G, v);
    }

    // check that algorithm computes either the topological order or finds a directed cycle
    private void dfs(Digraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : G.adj(v)) {

            // short circuit if directed cycle found
           /*if (cycle != null) return;

            //found new vertex, so recur
            else */if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }

            // trace back directed cycle
            else if (onStack[w]) {
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
                assert check();
            }
        }
        onStack[v] = false;
    }

    /**
     * Does the digraph have a directed cycle?
     * @return <tt>true</tt> if the digraph has a directed cycle, <tt>false</tt> otherwise
     */
    public boolean hasCycle() {
        return cycle != null;
    }

    /**
     * Returns a directed cycle if the digraph has a directed cycle, and <tt>null</tt> otherwise.
     * @return a directed cycle (as an iterable) if the digraph has a directed cycle,
     *    and <tt>null</tt> otherwise
     */
    public Iterable<Integer> cycle() {
        return cycle;
    }


    // certify that digraph has a directed cycle if it reports one
    private boolean check() {

        if (hasCycle()) {
            // verify cycle
            int first = -1, last = -1;
            for (int v : cycle()) {
                if (first == -1) first = v;
                last = v;
            }
            if (first != last) {
                System.err.printf("cycle begins with %d and ends with %d\n", first, last);
                return false;
            }
        }


        return true;
    }

    /**
     * Unit tests the <tt>DirectedCycle</tt> data type.
     */
    

}
