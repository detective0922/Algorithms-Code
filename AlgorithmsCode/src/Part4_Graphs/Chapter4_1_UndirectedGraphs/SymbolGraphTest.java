package Part4_Graphs.Chapter4_1_UndirectedGraphs;

import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;

public class SymbolGraphTest {

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
			for (int v : G.adj(s)) {
				StdOut.println("   " + sg.name(v));
			}
		} else {
			StdOut.println("input not contain '" + source + "'");
		}
		
		/*for (int i = 0; i < G.V(); i++) {
			StdOut.println(sg.name(i) + ":");
			for (int v : G.adj(i)) {
				StdOut.println("   " + sg.name(v));
			}
		}*/

	}
}

class SymbolGraph{
	private ST<String, Integer> st;
	private String[] keys;
	private Graph G;
	
	public SymbolGraph(String filename, String delimiter){
		st = new ST<String, Integer>();
		File file = new File(filename);
		In in = new In(file);
		while (in.hasNextLine()) {
			String[] line = in.readLine().split(delimiter);
			for (String string : line) {
				if (!st.contains(string)) {
					st.put(string, st.size());
				}
			}
		}
		keys = new String[st.size()];
		for (String key : st.keys()) {
			keys[st.get(key)] = key;
		}
		
		G = new Graph(st.size());
		in = new In(filename);
		while (in.hasNextLine()) {
			String[] line = in.readLine().split(delimiter);
			int v = st.get(line[0]);
			for (int i = 1; i < line.length; i++) {
				G.addEdge(v, st.get(line[i]));
			}
		}
	}
	
	public boolean contains(String key){
		return st.contains(key);
	}
	
	public int index(String key){
		return st.get(key);
	}
	
	public String name(int v){
		return keys[v];
	}
	
	public Graph G(){
		return G;
	}
}
