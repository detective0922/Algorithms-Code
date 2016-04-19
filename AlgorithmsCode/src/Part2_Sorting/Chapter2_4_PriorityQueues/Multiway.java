package Part2_Sorting.Chapter2_4_PriorityQueues;

import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;

public class Multiway {
	
	public static void main(String[] args) {
		File tFile = new File("algs4-data//tinyBatch.txt");
		String[] inList = new In(tFile).readAllLines();
		int M = inList.length;
		MaxPQ<Transaction> pq = new MaxPQ<Transaction>(M + 1);
		for (int i = 0; i < inList.length; i++) {
			pq.insert(new Transaction(inList[i]));
			if (pq.size() > M) {
				pq.delMax();
			}
		}

		Stack<Transaction> stack = new Stack<Transaction>();
		while (!pq.isEmpty()) {
			stack.push(pq.delMax());
		}
		for (Transaction t : stack) {
			StdOut.println(t);
		}	
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
	
	private Item[] pq;
	private int N = 0;
	
	public IndexMinPQ(int max) {
		pq = (Key[]) new Comparable[max + 1];
	}
	
	public void insert(int k, Item Item){
		
	}

	public void change(int k, Item Item) {

	}
	
	public boolean contains(int k){
		
	}
	
	public void delete(int k) {

	}
	
	public Item min(){
		
	}
	
	public int minIndex(){
		
	}
	
	public int delMin(){
		
	}
	
	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}
	
	private boolean less(int i, int j) {
		return pq[i].compareTo(pq[j]) < 0;
	}
	
	private void exch(int i, int j){
		Key t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}
	
	private void swim(int k){
		while (k > 1 && less(k / 2, k)) {
			exch(k / 2, k);
			k = k / 2;
		}
	}
	
	private void sink(int k) {
		int sun = 0;
		
		while (2 * k <= N) {
			if (2 * k < N && less(2 * k, 2 * k + 1)) {
				sun = 2 * k + 1;
			} else {
				sun = 2 * k;
			}
			
			if(!less(k, sun))
				break;
			exch(k, sun);
			k = sun;
		}
		
	}

}