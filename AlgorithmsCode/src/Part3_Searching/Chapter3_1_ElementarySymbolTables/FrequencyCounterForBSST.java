package Part3_Searching.Chapter3_1_ElementarySymbolTables;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class FrequencyCounterForBSST {
	public static void main(String[] args) {
		SequentialSearchST<String, Integer> ssst = new SequentialSearchST<String, Integer>();
		//int minlen = 1;
		int minlen = 8;
		//int minlen = 10;
		//File tFile = new File("algs4-data//tinyTale.txt");
		File tFile = new File("algs4-data//tale.txt");
		//File tFile = new File("algs4-data//leipzig1M.txt");
		String[] inList = new In(tFile).readAllStrings();
		
		for (int i = 0; i < inList.length; i++) {
			String word = inList[i];
			if (word.length() < minlen)
				continue;
			if (!ssst.contains(word))
				ssst.put(word, 1);
			else
				ssst.put(word, ssst.get(word) + 1);
		}

		String max = " ";
		ssst.put(max, 0);
		for (String word : ssst.keys()) {
			if (ssst.get(word) > ssst.get(max))
				max = word;
		}
		StdOut.println(max + ", " + ssst.get(max));
	}
}

class BinarySearchST<Key extends Comparable<Key>, Value>{
	
	private Key[] keys;
	private Value[] vals;
	private int N;
	
	public BinarySearchST(int Capacity) {
		keys = (Key[]) new Comparable[Capacity];
		vals = (Value[]) new Object[Capacity];
		N = 0;
	}
	
	public int rank(Key key){
		
	}
	
	public Value get(Key key) {
		
		/*int pos = rank(key);
		return vals[pos];*/
		if(isEmpty())
			return null;
		int index = rank(key);
		if (index < N && keys[index].compareTo(key) == 0) {
			return vals[index];
		} else {
			return null;
		}
	}
	
	public void put(Key key, Value value) {
		/*int pos = rank(key);
		if(pos == -1){
			for (int i = N; i >= 0; i--) {
				if (keys[i].compareTo(key) > 0) {
					keys[i+1] = keys[i];
					vals[i+1] = vals[i];
				} else {
					keys[i+1] = key;
					vals[i+1] = value;
				}
			}
			N++;
		} else{
			vals[pos] = value;
		}*/
		int index = rank(key);
		if (index < N && keys[index].compareTo(key) == 0) {
			vals[index] = value;
			return;
		}
		for (int i = N; i >index; i--) {
			keys[i] = keys[i-1];
			vals[i] = vals[i-1];
		}
		keys[index] = key;
		vals[index] = value;			
		
	}
	
	public boolean isEmpty(){
		return N == 0;
	}
	
	public boolean contains(Key key) {
		return rank(key) != -1;
	}
	
	public Key[] keys(){
		return keys;		
	}
}
