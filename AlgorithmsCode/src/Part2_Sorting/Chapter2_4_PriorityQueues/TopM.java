package Part2_Sorting.Chapter2_4_PriorityQueues;

import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;

public class TopM {
	
	public static void main(String[] args) {
		File tFile = new File("algs4-data//tinyBatch.txt");
		String[] inList = new In(tFile).readAllStrings();
		int M = inList.length;
		MaxPQ<Transaction> pq = new MaxPQ<Transaction>(M + 1);
		for (int i = 0; i < inList.length; i++) {
			pq.insert(new Transaction(inList[i]));
			if (pq.size() > M) {
				pq.delMax();
			}
		}

		Stack<Transaction> stack = new Stack<Transaction>();
		while (stack.isEmpty()) {
			stack.push(pq.delMax());
		}
		for (Transaction t : stack) {
			StdOut.println(t);
		}	
	}
}

class MaxPQ<Key extends Comparable<Key>> {
	
	private Key[] pq;
	private int N = 0;
	
	
	public MaxPQ(){
		pq = (Key[]) new Comparable[0];
	}
	
	public MaxPQ(int max) {
		pq = (Key[]) new Comparable[max + 1];
	}
	
	public MaxPQ(Key[] a){
		pq = a;
	}
	
	public void insert(Key v){
		pq[++N] = v;
		swim(N);
	}
	
	public Key Max(){
		return pq[1];
	}
	
	public Key delMax(){
		Key key = pq[1];
		pq[1] = pq[N];
		sink(N);
		return key;
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
		while(less(k, k/2)){
			exch(k, k/2);
			k = k/2;
		}
	}
	
	private void sink(int k) {
		while (less(k, k * 2)) {
			exch(k, k * 2);
			k = k * 2;
		}
	}

}

