package Part3_Searching.Chapter3_1_ElementarySymbolTables;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class FrequencyCounterForBSST {
	public static void main(String[] args) {
		BinarySearchST<String, Integer> bsst = new BinarySearchST<String, Integer>(40000000);
		//int minlen = 1;
		int minlen = 8;
		//int minlen = 10;
		//File tFile = new File("algs4-data//tinyTale.txt");
		File tFile = new File("algs4-data//tale.txt");
		//File tFile = new File("algs4-data//leipzig1M.txt");
		//String[] inList = new In(tFile).readAllStrings();
		/*In in = new In(tFile);
		List<String> lineList = new ArrayList<String>();
		List<String> strList = new ArrayList<String>();
		while(in.hasNextLine()){
			lineList.add(in.readLine());
		}
		for(String line:lineList){
			strList.addAll(Arrays.asList(line.split(" ")));
		}
		String[] inList = strList.toArray(new String[strList.size()]);*/
		
		try {
			System.setIn(new FileInputStream(tFile.getAbsolutePath()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Stopwatch timer = new Stopwatch();
		while(!StdIn.isEmpty()){
		//for (int i = 0; i < inList.length; i++) {
			//String word = inList[i];
			String word = StdIn.readString();
			if (word.length() < minlen)
				continue;
			if (!bsst.contains(word))
				bsst.put(word, 1);
			else
				bsst.put(word, bsst.get(word) + 1);
		}

		String max = " ";
		bsst.put(max, 0);
		for (String word : bsst.keys()) {
			if (bsst.get(word) > bsst.get(max))
				max = word;
		}
		
		StdOut.println(max + ", " + bsst.get(max));
		StdOut.println(timer.elapsedTime());
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
	
	public int rank(Key key) {
		// return rank(key, 0, N - 1);
		int lo = 0, hi = N - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (key.compareTo(keys[mid]) < 0)
				hi = mid - 1;
			else if (key.compareTo(keys[mid]) > 0)
				lo = mid + 1;
			else
				return mid;
		}
		return lo;
	}
	
	private int rank(Key key, int lo, int hi) {

		if (lo > hi)
			return lo;
		int mid = lo + (hi - lo) / 2;
		if (key.compareTo(keys[mid]) < 0)
			return rank(key, lo, mid - 1);
		else if (key.compareTo(keys[mid]) > 0)
			return rank(key, mid + 1, hi);
		else
			return mid;
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
