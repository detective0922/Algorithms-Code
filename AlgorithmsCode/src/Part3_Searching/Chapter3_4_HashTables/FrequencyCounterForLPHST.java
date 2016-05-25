package Part3_Searching.Chapter3_4_HashTables;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.SequentialSearchST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class FrequencyCounterForLPHST {
	public static void main(String[] args) {
		LinearProbingHashST<String, Integer> lphst = new LinearProbingHashST<String, Integer>();
		//int minlen = 1;
		//int minlen = 8;
		int minlen = 10;
		//File tFile = new File("algs4-data//tinyTale.txt");
		//File tFile = new File("algs4-data//tale.txt");
		File tFile = new File("algs4-data//leipzig1M.txt");
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
			if (!lphst.contains(word))
				lphst.put(word, 1);
			else
				lphst.put(word, lphst.get(word) + 1);
		}

		String max = " ";
		lphst.put(max, 0);
		Iterable<String> keyList = lphst.keys();
		for (String word : keyList) {
			if (lphst.get(word) > lphst.get(max))
				max = word;
		}
				
		StdOut.println(max + ", " + lphst.get(max));
		StdOut.println(timer.elapsedTime());
		//output:
		//government, 24763
		//18.377

		//output of book's implementation
		//government, 24763
		//18.324

	}
}

class LinearProbingHashST<Key, Value>{
	
	private int N;
	private int M;
	private Key[] keys;
	private Value[] values;
	
	public LinearProbingHashST() {
		this(16);		
	}
	
	public LinearProbingHashST(int M) {
		this.M = M;
		keys = (Key[]) new Object[M];
		values = (Value[]) new Object[M];
	}
	
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}
	
	public void put(Key key, Value val) {
		if (N > M / 2) {
			resize(2 * M);
		}
		
		int hashcode = hash(key);
		while (keys[hashcode] != null) {
			if (keys[hashcode].equals(key)) {
				values[hashcode] = val;
				return;
			}
			hashcode = (hashcode + 1) % M;
		}
		
		
		//implementation in book
		/*int hashcode;
		for (hashcode = hash(key); keys[hashcode] != null; hashcode = (hashcode + 1)
				% M) {
			if (keys[hashcode].equals(key)) {
				values[hashcode] = val;
				return;
			}
		}*/
		keys[hashcode] = key;
		values[hashcode] = val;
		N++;
	}
	
	public Value get(Key key){
		int hashcode = hash(key);
		while (keys[hashcode] != null) {
			if (keys[hashcode].equals(key)) {
				return values[hashcode];
			}
			hashcode = (hashcode + 1) % M;
		}
		return null;
		
		//implementation in book
		/*int hashcode;
		for (hashcode = hash(key); keys[hashcode] != null; hashcode = (hashcode + 1)
				% M) {
			if (keys[hashcode].equals(key)) {
				return values[hashcode];

			}
		}
		return null;*/
	}
	
	public boolean contains(Key key) {
		return get(key) != null;
	}
	
	public void resize(int size){
		LinearProbingHashST<Key,Value> tmpSt = new LinearProbingHashST<Key,Value>(size);
		for (int i = 0; i < M; i++) {
			if (keys[i] != null) {
				tmpSt.put(keys[i], values[i]);
			}
		}
		this.M = size;
		keys = tmpSt.keys;
		values = tmpSt.values;
	}
	
	//3.4.19
	public Iterable<Key> keys() {
		List<Key> retKeys = new ArrayList<Key>();
		for (Key key : keys) {
			if (key != null) {
				retKeys.add(key);
			}
		}
		return retKeys;
	}
	
}
