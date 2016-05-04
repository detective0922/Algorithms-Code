package Part3_Searching.Chapter3_2_BinarySearchTrees;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class FrequencyCounterForBST {
	public static void main(String[] args) {
		BST<String, Integer> bsst = new BST<String, Integer>();
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
			if (!bsst.contains(word))
				bsst.put(word, 1);
			else
				bsst.put(word, bsst.get(word) + 1);
		}

		String max = " ";
		bsst.put(max, 0);
		Iterable<String> keyList = bsst.keys();
		for (String word : keyList) {
			if (bsst.get(word) > bsst.get(max))
				max = word;
		}
				
		StdOut.println(max + ", " + bsst.get(max));
		StdOut.println(timer.elapsedTime());
		//output:
		//government, 24763
		//71.175
	}
}

class BST<Key extends Comparable<Key>, Value>{
	
	private Node root;
	
	private class Node {
		private Key key;
		private Value value;
		private Node left, right;
		private int N;

		public Node(Key key, Value val, int N) {
			this.key = key;
			this.value = val;
			this.N = N;
		}
	}
	
	public int size(){
		return size(root);
	}
	
	public int size(Node node) {
		if (node == null)
			return 0;
		else
			return node.N;
	}
	
	/*public Value get(Key key) {
		if(isEmpty())
			return null;
		Node node = root;
		while(key.compareTo(node.key)!=0){
			if(key.compareTo(node.key)>0){
				node = node.right;
			} else {
				node = node.left;
			}
			if(node == null){
				return null;
			}
		}
		return node.value;
	}*/
	
	public Value get(Key key) {
		return get(root, key);
	}
	
	private Value get(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			return get(x.left, key);
		else if (cmp > 0)
			return get(x.right, key);
		else
			return x.value;
	}
	
	/*public void put(Key key, Value value) {
		if (isEmpty()) {
			root = new Node(key, value, 1);
			return;
		}
		
		Node node = root;
		while(key.compareTo(node.key)!=0){
			int nodeN = node.N;
			if(key.compareTo(node.key)>0){
				node = node.right;
			} else {
				node = node.left;
			}
			if(node == null){
				node = new Node(key, value, nodeN + 1);
				return;
			}
		}
		
		node = new Node(key, value, node.N + 1);
	}*/
	
	public void put(Key key, Value val){
		root = put(root, key, val);
	}
	
	private Node put(Node x, Key key, Value val){
		if (x == null)
			return new Node(key, val, 1);
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			return x.left = put(x.left, key, val);
		else if (cmp > 0)
			return x.right = put(x.right, key, val);
		else
			x.value = val;
		
		return x;
	}
	
	public boolean isEmpty(){
		return root == null;
	}
	
	public boolean contains(Key key) {
		return get(key) != null;
	}
	
	public Iterable<Key> keys() {
		List<Key> tmpkeys = new ArrayList<Key>();
		for (int i = 0; i < N; i++) {
			tmpkeys.add(root.key);
		}
		return tmpkeys;
	}
}
