package Part1_Fundamentals.Chapter1_5_CaseStudyUnionFind;

import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class Test1_5_12 {
	public static void main(String[] args) {
		File tFile = new File("algs4-data//largeUF.txt");
		In in = new In(tFile);
		int N = in.readInt();
		PathCompressionQuickUnion pcqu = new PathCompressionQuickUnion(N);
		Stopwatch timer = new Stopwatch();
		//the tinyUF.txt has a empty line at the end of file
		try {
			while (in.hasNextLine()) {
				int p = in.readInt();
				int q = in.readInt();
				if (pcqu.connected(p, q))
					continue;
				pcqu.union(p, q);
				//StdOut.println(p + ", " + q);
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		StdOut.println(timer.elapsedTime());
		StdOut.println(pcqu.count() + "components");

	}
	
	
}

class PathCompressionQuickUnion {
	
	private int[] id;
	private int count;
	
	public PathCompressionQuickUnion(int N) {
		count = N;
		id = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
	}
	
	public void union(int p, int q){
		int pRoot = find(p);
		int qRoot = find(q);
		if (pRoot == qRoot)
			return;
		
		id[pRoot] = qRoot;
		count--;
	}
	
	public int find(int p){
		int pRoot = p;
		while (pRoot != id[pRoot]) {
			pRoot = id[pRoot];
		}
		while (p != pRoot) {
			int tempP = id[p];
			id[p] = pRoot;
			p = tempP;
		}
		return pRoot;
	}
	
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}
	
	public int count(){
		return count;
	}
	
}
