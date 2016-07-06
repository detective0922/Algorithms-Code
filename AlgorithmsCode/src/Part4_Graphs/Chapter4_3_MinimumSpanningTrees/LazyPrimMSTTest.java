package Part4_Graphs.Chapter4_3_MinimumSpanningTrees;

import java.io.File;
import java.util.Iterator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class LazyPrimMSTTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File tFile = new File("algs4-data//tinyEWG.txt");		
		In in = new In(tFile);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        Stopwatch timer = new Stopwatch();
        LazyPrimMST mst = new LazyPrimMST(G);
        StdOut.println(timer.elapsedTime());
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
		/*edu.princeton.cs.algs4.EdgeWeightedGraph G = new edu.princeton.cs.algs4.EdgeWeightedGraph(
				in);
		edu.princeton.cs.algs4.LazyPrimMST mst = new edu.princeton.cs.algs4.LazyPrimMST(
				G);
		for (edu.princeton.cs.algs4.Edge e : mst.edges()) {
			StdOut.println(e);
		}*/
        StdOut.println(mst.weight());
        //output: book time 0.0010 - 0.0020
        // my time 0.0010 -  0.0020
	}

}

class LazyPrimMST{
	private boolean[] marked;
	private Queue<Edge> mst;
	private MinPQ<Edge> pq;
	
	public LazyPrimMST(EdgeWeightedGraph g){
		marked = new boolean[g.V()];
		mst = new Queue<Edge>();
		pq = new MinPQ<Edge>();
		
		visit(g, 0);
		while (mst.size() < (g.V() - 1)) {
			Edge edge = pq.delMin();
			int either = edge.either();
			int other = edge.other(either);
			while (marked[either] && marked[other]) {
				edge = pq.delMin();
				either = edge.either();
				other = edge.other(either);
			}
			if(!marked[either]){
				visit(g, either);
			} else if(!marked[other]){
				visit(g, other);
			}
			mst.enqueue(edge);
		}
		
		//implementation in book
		/*while(!pq.isEmpty()){
			Edge edge = pq.delMin();
			int either = edge.either();
			int other = edge.other(either);
			if (marked[either] && marked[other]){
				continue;
			}
			mst.enqueue(edge);
			if (!marked[either]) {
				visit(g, either);
			}
			if (!marked[other]) {
				visit(g, other);
			}				
		}*/
			
	}
	
	private void visit(EdgeWeightedGraph g, int v){
		marked[v] = true;
		for (Edge e : g.adj(v)) {
			int other = e.other(v);
			if (!marked[other]) {
				pq.insert(e);
			}
		}
	}
	
	public Iterable<Edge> edges(){
		return mst;
	}
	
	public double weight(){
		double weight = 0;
		Iterator<Edge> edgeIterator = mst.iterator();
		while (edgeIterator.hasNext()) {
			Edge edge = (Edge) edgeIterator.next();
			weight += edge.getWeight();
		}
		return weight;
	}
	
}
