package Part4_Graphs.Chapter4_1_UndirectedGraphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class SearchTest {
	public static void main(String[] args){
		Graph g = new Graph(new In(args[0]));
		int s = Integer.parseInt(args[1]);
		Search search = new Search(g, s);
		
		for(int v = 0; v<g.V();v++){
			if(search.marked(v)){
				StdOut.print(v+" ");
			}
		}
		StdOut.println();
		
		if(search.count()!=g.V()){
			StdOut.println("Not connected");
		}
		
		
	}

}
