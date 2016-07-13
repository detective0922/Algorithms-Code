package Part4_Graphs.Chapter4_4_ShortestPaths;

public class DijkstraAllPairsSPTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}


class DijkstraAllPairsSP{
	private DijkstraSP[] all;
	
	public DijkstraAllPairsSP(EdgeWeightedDigraph g) {
		all = new DijkstraSP[g.V()];
		for(int v = 0;v<g.V();v++){
			all[v] = new DijkstraSP(g, v);
		}

	}
	
	public Iterable<DirectedEdge> path(int s, int t) {
		return all[s].pathTo(t);		
	}
	
	public double dist(int s, int t) {
		return all[s].distTo(t);
	}
}
