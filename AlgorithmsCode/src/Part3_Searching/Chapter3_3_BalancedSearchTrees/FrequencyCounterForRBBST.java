package Part3_Searching.Chapter3_3_BalancedSearchTrees;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class FrequencyCounterForRBBST {
	public static void main(String[] args) {
		RBBST<String, Integer> bst = new RBBST<String, Integer>();
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

class RBBST<Key extends Comparable<Key>, Value>{
	
	private Node root;
	
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	private class Node {
		private Key key;
		private Value value;
		private Node left, right;
		private int N;
		private boolean color;

		public Node(Key key, Value val, int N, boolean color) {
			this.key = key;
			this.value = val;
			this.N = N;
			this.color = color;
		}
	}
	
	public boolean isRed(Node x) {
		if (x == null)
			return false;
		return x.color == RED;
	}
	
	public int size(Node node) {
		if (node == null)
			return 0;
		else
			return node.N;
	}
	
	public Node rotateLeft(Node h){
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		x.N =  h.N;
		h.N = size(h.left) + size(h.right) + 1;
		return x;
	}
	
	public Node rotateRight(Node h){
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = size(h.left) + size(h.right) + 1;
		return x;
	}
	
	public void flipColors(Node h){
		h.color = RED;
		h.left.color = BLACK;
		h.right.color = BLACK;
	}
	
	public boolean isEmpty(){
		return root == null;
	}
	
	public boolean contains(Key key) {
		return get(key) != null;
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
	
	public void put(Key key, Value value) {
		if (isEmpty()) {
			root = new Node(key, value, 1, BLACK);
			return;
		}
		
		Node node = root;
		Stack<Node> stack = new Stack<Node>();
		stack.push(node);
		
		while (key.compareTo(node.key) != 0) {
			
			if (key.compareTo(node.key) < 0) {
				if (node.left == null) {
					node.left = new Node(key, value, 1, RED);
				}
				node = node.left;				
			} else if (key.compareTo(node.key) > 0) {
				if (node.right == null) {
					node.right = new Node(key, value, 1, RED);
				}
				node = node.right;
			}
			if (!isRed(node.left) && isRed(node.right))
				node = rotateLeft(node);
			if (isRed(node.left) && isRed(node.left.left))
				node = rotateRight(node);
			if (isRed(node.left) && isRed(node.right))
				flipColors(node);
			
			stack.push(node);
			//node.N = size(node.left) + size(node.right) + 1;
		}
		while (!stack.isEmpty()) {
			Node tmpNode = stack.pop();
			tmpNode.N = size(tmpNode.left) + size(tmpNode.right) + 1;
		}
		node.value = value;
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
