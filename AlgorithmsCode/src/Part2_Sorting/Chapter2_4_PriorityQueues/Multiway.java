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
	
	//pq posToIndex
	//qp indexToPos
	
	private int[] indexToPos;
	private int[] posToIndex;
 	private Item[] pq;
	private int N = 0;
	
	public IndexMinPQ(int maxN) {
		pq = (Item[]) new Comparable[maxN + 1];
		indexToPos = new int[maxN + 1];
		posToIndex = new int[maxN + 1];
		for (int i = 0; i < posToIndex.length; i++) {
			indexToPos[i] = -1;
		}
		
	}
	
	public void insert(int index, Item item){
		N++;
		indexToPos[index] = N;
		posToIndex[N] = index;	
		pq[index] = item;
	}

	public void change(int index, Item item) {
		pq[index] = item;
	}
	
	public boolean contains(int index){
		return indexToPos[index] != -1;
	}
	
	public void delete(int index) {
		int position = indexToPos[index];
		exch(position, N);
		N--;
		swim(position);
		sink(position);
		pq[index] = null;
		indexToPos[index] = -1;	
	}
	
	public Item min(){
		return pq[posToIndex[1]];
	}
	
	public int minIndex(){
		return posToIndex[1];
	}
	
	public int delMin(){
		int minIndex = posToIndex[1];
		exch(1, N);
		pq[posToIndex[N]] = null;
		N--;
		sink(1);
		indexToPos[minIndex] = -1;
		posToIndex[1] = -1;
		/*N--;
		sink(1);
		pq[N] = null;
		indexToPos[minIndex] = -1;
		posToIndex[1] = -1;*/
		return minIndex;
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
	
	private void exch(int posi, int posj){
		int index = posToIndex[posi];
		posToIndex[posi] = posToIndex[posj];
		posToIndex[posj] = index;
		indexToPos[posToIndex[posi]] = posj;
		indexToPos[posToIndex[posj]] = posi;
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