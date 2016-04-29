package Part3_Searching.Chapter3_1_ElementarySymbolTables;

import java.io.BufferedReader;
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
import edu.princeton.cs.algs4.Transaction;

public class FrequencyCounter {
	public static void main(String[] args) {
		SequentialSearchST<String, Integer> ssst = new SequentialSearchST<String, Integer>();
		//int minlen = 1;
		int minlen = 8;
		//int minlen = 10;
		//File tFile = new File("algs4-data//tinyTale.txt");
		File tFile = new File("algs4-data//tale.txt");
		//File tFile = new File("algs4-data//leipzig1M.txt");
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
		StdOut.println(timer.elapsedTime());
	}
}

class SequentialSearchST<Key, Value>{
	
	private Node first;
	
	private class Node {
		Key key;
		Value value;
		Node next;

		public Node(Key key, Value val, Node next) {
			this.key = key;
			this.value = val;
			this.next = next;
		}
	}
	
	public Value get(Key key) {
		/*Node node = first;
		while (node != null) {
			if (key.equals(node.key)) {
				return node.value;
			}
			node = node.next;
		}*/
		for (Node node = first; node != null; node = node.next) {
			if (key.equals(node.key)) {
				return node.value;
			}
		}
		return null;
	}
	
	public void put(Key key, Value value) {
		/*Node current = first;
		while (current != null) {
			if (key.equals(current.key)) {
				current.value = value;
				return;
			}
			current = current.next;
		}*/
		for(Node node = first;node!=null;node = node.next){
			if (key.equals(node.key)) {
				node.value = value;
				return;
			}
		}
		first = new Node(key, value, first);
	}
	
	public boolean contains(Key key) {
		if (first == null)
			return false;
		return get(key) != null;
	}
	
	public Iterable<Key> keys(){
		List<Key> keys = new ArrayList<Key>();
		for (Node node = first; node.next != null; node = node.next) {
			keys.add(node.key);
		}
		return keys;		
	}
}

