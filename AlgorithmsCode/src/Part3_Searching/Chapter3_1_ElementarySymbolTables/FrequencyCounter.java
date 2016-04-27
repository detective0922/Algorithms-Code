package Part3_Searching.Chapter3_1_ElementarySymbolTables;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;

public class FrequencyCounter {
	public static void main(String[] args) {
		int minlen = 1;
		SequentialSearchST<String, Integer> ssst = new SequentialSearchST<String, Integer>();
		File tFile = new File("algs4-data//tinyTale.txt");
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
		Node node = first;
		while (node != null) {
			if (key.equals(node.key)) {
				return node.value;
			}
			node = node.next;
		}
		return null;
	}
	
	public void put(Key key, Value value) {
		Node node = new Node(key, value, null);
		if (first == null) {
			first = node;
			return;
		}
		Node current = first;
		while (current.next != null) {
			current = current.next;
		}
		current.next = node;
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

