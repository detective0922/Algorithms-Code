package Part3_Searching.Chapter3_4_HashTables;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.SequentialSearchST;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class FrequencyCounterForSCHST {
	public static void main(String[] args) {
		SCHST<String, Integer> bst = new SCHST<String, Integer>();
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
			if (!bst.contains(word))
				bst.put(word, 1);
			else
				bst.put(word, bst.get(word) + 1);
		}

		String max = " ";
		bst.put(max, 0);
		Iterable<String> keyList = bst.keys();
		for (String word : keyList) {
			if (bst.get(word) > bst.get(max))
				max = word;
		}
				
		StdOut.println(max + ", " + bst.get(max));
		StdOut.println(timer.elapsedTime());
		//output:
		//government, 24763
		//21.006
	}

}

class SCHST<Key extends Comparable<Key>, Value>{
	
	private int N;
	private int M;
	private SequentialSearchST<Key, Value>[] st;
	
	public SCHST() {
		this(997);		
	}
	
	public SCHST(int M) {
		this.M = M;
		st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
		for (int i = 0; i < M; i++) {
			st[i] = new SequentialSearchST();
		}
	}
	
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}
	
	public void put(Key key, Value val) {
		int hashcode = hash(key);
		st[hashcode].put(key, val);
	}
	
	public Value get(Key key){
		int hashcode = hash(key);
		return st[hashcode].get(key);
	}
	
	public boolean contains(Key key) {
		return get(key) != null;
	}
	
	//3.4.19
	public Iterable<Key> keys(){
		List<Key> keys = new ArrayList<Key>();
		for (int i = 0; i < M; i++) {
			for(Key key: st[i].keys()){
				keys.add(key);
			}
		}
		return keys;		
	}
	
}
