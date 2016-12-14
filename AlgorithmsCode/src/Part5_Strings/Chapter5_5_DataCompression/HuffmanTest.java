package Part5_Strings.Chapter5_5_DataCompression;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.MinPQ;

public class HuffmanTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if      (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();

	}

}

class Huffman {
	
	private static int R = 256;
	
	public static void compress() {

	}
	
	public static void expand() {
		Node root = readTrie();
		int N = BinaryStdIn.readInt();
		for (int i = 0; i < N; i++) {
			Node x = root;
			while(!x.isLeaf()){
				if(BinaryStdIn.readBoolean()){
					x = x.right;
				} else {
					x = x.left;
				}
			}
			BinaryStdOut.write(x.ch);			
		}
		
		BinaryStdOut.close();

	}
	
	private static class Node implements Comparable<Node> {
		
		private char ch;
		private int freq;
		private final Node left, right;
		
		public Node(char ch, int freq, Node left, Node right) {
			this.ch = ch;
			this.freq = freq;
			this.left = left;
			this.right = right;
		}
		
		public boolean isLeaf() {
			return left == null && right == null;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.freq - o.freq;
		}
		
	}
	
	private static void writeTrie(Node x){
		if(x.isLeaf()){
			BinaryStdOut.write(true);
			BinaryStdOut.write(x.ch);
			return;
		}
		BinaryStdOut.write(false);
		writeTrie(x.left);
		writeTrie(x.right);
	}
	
	private static Node readTrie() {
		if(BinaryStdIn.readBoolean()){
			return new Node(BinaryStdIn.readChar(), 0, null, null);
		}
		
		return new Node('\0', 0, readTrie(), readTrie());
	}
	
	private static void buildTrie(int[] freq) {
		MinPQ<Node> pq = new MinPQ<Huffman.Node>();
		for (char c = 0; c < R; c++) {
			if (freq[c] > 0) {
				pq.insert(new Node(c, freq[c], null, null));
			}
		}
		
		while (pq.size() > 1) {
			Node x = pq.delMin();
			Node y = pq.delMin();
			Node parent = new Node('\0', x.freq + y.freq, x, y);
			pq.insert(parent);
		}
	}
	
	
}
