package Part4_Graphs.Chapter4_3_MinimumSpanningTrees;

public class DirectedEdgeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}


class DirectedEdge implements Comparable<Edge>{
	private int v;
	private int w;
	private double weight;
	
	public DirectedEdge(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	public int getV() {
		return v;
	}

	public int getW() {
		return w;
	}

	public double getWeight() {
		return weight;
	}
	
	public int from() {
        return v;
    }
	
	public int to(int vertex) {
        return w;
    }

	public String toString() {
		return String.format("%d-%d %.5f", v, w, weight);
	}

	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		if (weight < o.getWeight())
			return -1;
		else if (weight > o.getWeight())
			return 1;
		else
			return 0;
	}
	
}