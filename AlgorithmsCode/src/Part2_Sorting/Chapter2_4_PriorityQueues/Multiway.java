package Part2_Sorting.Chapter2_4_PriorityQueues;

import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;

public class Multiway {
	
	public static void main(String[] args) {
		File File0 = new File("algs4-data//m1.txt");
		File File1 = new File("algs4-data//m2.txt");
		File File2 = new File("algs4-data//m3.txt");
		
		int N = 3;
		In[] streams = new In[N];
		streams[0] = new In(File0);
		streams[1] = new In(File1);
		streams[2] = new In(File2);
		merge(streams);
	}
	
	public static void merge(In[] streams) {
		int N = streams.length;
		IndexMinPQ<String> pq = new IndexMinPQ<String>(N);
		for (int i = 0; i < N; i++) {
			if (!streams[i].isEmpty()) {
				pq.insert(i, streams[i].readString());
			}
		}
		
		while (!pq.isEmpty()) {
			StdOut.println(pq.min());
			int i = pq.delMin();
			if (!streams[i].isEmpty()) {
				pq.insert(i, streams[i].readString());
			}
		}

	}
}

class IndexMinPQ<Item extends Comparable<Item>> {
	
	private int[] indexToPos;
	private int[] posToIndex;
 	private Item[] pq;
	private int N = 0;
	
	public IndexMinPQ(int maxN) {
		pq = (Item[]) new Comparable[maxN + 1];
		//indexs = new int[maxN + 1];
		posToIndex = new int[maxN + 1];
		for (int i = 0; i < posToIndex.length; i++) {
			posToIndex[i] = -1;
		}
		
	}
	
	public void insert(int k, Item item){
		N++;
		indexs[N] = k;
		posToIndex[N] = k;	
		pq[k] = item;
	}

	public void change(int k, Item item) {
		pq[k] = item;
	}
	
	public boolean contains(int k){
		return positions[k] != -1;
	}
	
	public void delete(int k) {
		int position = positions[k];
		exch(position, N);
		N--;
		swim(position);
		sink(position);
		pq[k] = null;
		positions[k] = -1;	
	}
	
	public Item min(){
		return pq[positions[1]];
	}
	
	public int minIndex(){
		return positions[1];
	}
	
	public int delMin(){
		
	}
	
	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}
	
	private boolean greater(int i, int j) {
		return pq[i].compareTo(pq[j]) > 0;
	}
	
	private void exch(int i, int j){
		Item t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}
	
	private void swim(int k){
		while (k > 1 && greater(k / 2, k)) {
			exch(k / 2, k);
			k = k / 2;
		}
	}
	
	private void sink(int k) {
		int sun = 0;
		
		while (2 * k <= N) {
			if (2 * k < N && greater(2 * k, 2 * k + 1)) {
				sun = 2 * k + 1;
			} else {
				sun = 2 * k;
			}
			
			if(!greater(k, sun))
				break;
			exch(k, sun);
			k = sun;
		}
		
	}

}