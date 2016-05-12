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
		BST<String, Integer> bst = new BST<String, Integer>();
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
	
	public Value get(Key key) {
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
	}
	
	/*public Value get(Key key) {
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
	}*/
	
	public void put(Key key, Value value) {
		if (isEmpty()) {
			root = new Node(key, value, 1);
			return;
		}
		
		Node node = root;
		while (key.compareTo(node.key) != 0) {
			node.N = size(node.left) + size(node.right) + 1;
			if (key.compareTo(node.key) < 0) {
				if (node.left == null) {
					node.left = new Node(key, value, 1);
					break;
				} else {
					node = node.left;
				}
			} else if (key.compareTo(node.key) > 0) {
				if (node.right == null) {
					node.right = new Node(key, value, 1);
					break;
				} else {
					node = node.right;
				}
			}			
		}
		node.value = value;
	}
	
	/*public void put(Key key, Value val){
		root = put(root, key, val);
	}
	
	private Node put(Node x, Key key, Value val){
		if (x == null)
			return new Node(key, val, 1);
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = put(x.left, key, val);
		else if (cmp > 0)
			x.right = put(x.right, key, val);
		else
			x.value = val;
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}*/
	
	public boolean isEmpty(){
		return root == null;
	}
	
	public boolean contains(Key key) {
		return get(key) != null;
	}
	
	public Iterable<Key> keys() {
		return keys(min(), max());
	}
	
	private Iterable<Key> keys(Key lo, Key hi) {
        List<Key> list = new ArrayList<Key>();
        keys(root, list, lo, hi);
        return list;
    } 

    private void keys(Node x, List<Key> list, Key lo, Key hi) { 
        if (x == null) return; 
        int cmplo = lo.compareTo(x.key); 
        int cmphi = hi.compareTo(x.key); 
        if (cmplo < 0) keys(x.left, list, lo, hi); 
        if (cmplo <= 0 && cmphi >= 0) list.add(x.key); 
        if (cmphi > 0) keys(x.right, list, lo, hi); 
    } 
    
	public Key min() {
		if (isEmpty())
			return null;
		return min(root).key;
	}

	private Node min(Node x) {
		if (x.left == null)
			return x;
		else
			return min(x.left);
	}

	public Key max() {
		if (isEmpty())
			return null;
		return max(root).key;
	}

	private Node max(Node x) {
		if (x.right == null)
			return x;
		else
			return max(x.right);
	}


}
