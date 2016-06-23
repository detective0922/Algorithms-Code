package Part4_Graphs.Chapter4_1_UndirectedGraphs;

import java.io.File;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class CCTest {
	public static void main(String[] args){
		
		File tFile = new File("algs4-data//tinyCG.txt");
		In in = new In(tFile);
		Graph g = new Graph(in);
		CC c = new CC(g);
		
		int M = CC.count();
		StdOut.println(M + " components");
		
		Bag<Integer>[] components = (Bag<Integer>[])new Bag[M]; 
		for (int i = 0; i < M; i++) {
			components[i] = new Bag<Integer>();
		}

		for (int v = 0; i < g.V(); i++) {
			components[c.id(v)].add(v);
		}

		for (int i = 0; i < M; i++) {
			for (int v : components[i]) {
				StdOut.print(v + " ");
			}
			StdOut.println();
		}
	}
}

class CC {
	private boolean[] marked;
	private int[] edgeTo;
	private final int s;
	
	public BreadthFirstPath(Graph g, int s) {
		marked = new boolean[g.V()];
		edgeTo = new int[g.V()];
		this.s = 0;
		bfs(g, s);
	}
	
	private void bfs(Graph g, int s) {

		Queue<Integer> queue = new Queue<Integer>();
		queue.enqueue(s);
		marked[s] = true;
		while (!queue.isEmpty()) {
			int v = queue.dequeue();
			//marked[v] = true;
			Iterable<Integer> vAdj = g.adj(v);
			for (int w : vAdj) {
				if (!marked[w]) {
					edgeTo[w] = v;
					queue.enqueue(w);
					marked[w] = true;
				}
			}
		}
	}
	
	public boolean hasPathTo(int v) {
		return marked[v];
	}
	
	public Iterable<Integer> pathTo(int v){
		if(!hasPathTo(v)){
			return null;
		}
		Stack<Integer> stack = new Stack<Integer>();
		/*stack.push(v);
		while(edgeTo[v]!=s){
			stack.push(edgeTo[v]);
			v = edgeTo[v];
		}*/
		for(int x = v; x!=s;x = edgeTo[x]){
			stack.push(x);
		}
		stack.push(s);
		return stack;
	}

}
