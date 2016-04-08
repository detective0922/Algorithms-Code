package Part1_Fundamentals.Chapter1_5_CaseStudyUnionFind;

import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class UF {
	
	public static void main(String[] args) {
		File tFile = new File("algs4-data//mediumUF.txt");
		In in = new In(tFile);
		int N = in.readInt();
		UF uf = new UF(N);
		Stopwatch timer = new Stopwatch();
		//the tinyUF.txt has a empty line at the end of file		
		try {
			while (in.hasNextLine()) {
				int p = in.readInt();
				int q = in.readInt();
				if (uf.connected(p, q))
					continue;
				uf.union(p, q);
				//StdOut.println(p + ", " + q);
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		StdOut.println(timer.elapsedTime());	
		StdOut.println(uf.count + "components");

	}
	
	private int[] id;
	private int count;
	
	public UF(int N) {
		count = N;
		id = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
	}
	
	public void union(int p, int q){
		int pId = find(p);
		int qId = find(q);
		if (pId == qId)
			return;
		
		for (int i = 0; i < id.length; i++) {
			if (id[i] == pId)
				id[i] = qId;
		}
		count--;
	}
	
	public int find(int p){
		return id[p];
	}
	
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}
	
	public int count(){
		return count;
	}

}
