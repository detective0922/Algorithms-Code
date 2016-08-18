package Part5_Strings.Chapter5_2_Tries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import edu.princeton.cs.algs4.Queue;

public class TriesSTTest {
	
	public static void main(String[] args){
		File tFile = new File("algs4-data//shellsST.txt");

		try {
			System.setIn(new FileInputStream(tFile.getAbsolutePath()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

class TriesST<Value>{
	
	private static int R = 256;
	private Node root;

	private static class Node {
		private Object val;
		private Node[] next = new Node[R];
	}
	
	public Value get(String key){
		int d = 0;
		int len = key.length();
		Node x = root;
		while (d <= len) {
			if (x == null) {
				return null;
			}
			if (d == len) {
				return (Value) x.val;
			}
			char c = key.charAt(d);
			x = x.next[c];
			d++;
		}
		return null;
	}
	
	public void put(String key, Value val){
		int d = 0;
		int len = key.length();
		Node x = root;
		while (d <= len) {
			if (x == null) {
				x = new Node();
			}
			if (d == len) {
				x.val = val;
				break;
			}
			char c = key.charAt(d);
			x = x.next[c];
			d++;
		}
	}
	
	public Iterable<Value> keys(){
		return keysWithPrefix("");
	}
	
	public Iterable<Value> keysWithPrefix(String prefix){
		
	}
	
	public Iterable<Value> keysThatMatch(String pat) {

	}
	
	public int longestPrefixOf(String s){
		
	}

	public int size(){
		Queue<Node> q = new Queue<TriesST.Node>();
		q.enqueue(root);
		Node x = null;
		int size = 0;
		while (!q.isEmpty()) {
			x = q.dequeue();
			if (x.val != null) {
				size++;
			}
			for (int i = 0; i < R; i++) {
				if (x.next[i] != null) {
					q.enqueue(x.next[i]);
				}
			}
		}

		return size;
	}
	
	public void delete(String key) {
		
	}
	
}