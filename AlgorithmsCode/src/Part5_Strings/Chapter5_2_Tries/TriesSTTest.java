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
	
	public Iterable<String> keys(){
		return keysWithPrefix("");
	}
	
	public Iterable<String> keysWithPrefix(String prefix){
		//int d = 0;
		int prefixLen = prefix.length();
		Node x = root;
		/*while(d<=prefixLen){
			char c = prefix.charAt(d);
			x = x.next[c];
			d++;
		}*/
		for (int d = 0; d < prefixLen; d++) {
			char c = prefix.charAt(d);
			x = x.next[c];
		}
		
		Queue<String> keys = new Queue<String>();
		Queue<String> tmpKeys = new Queue<String>();
		Queue<Node> q = new Queue<Node>();
		q.enqueue(x);
		tmpKeys.enqueue(prefix);
		while (!q.isEmpty()) {
			x = q.dequeue();
			prefix = tmpKeys.dequeue();
			if (x.val != null) {
				keys.enqueue(prefix);
			}
			for (char c = 0; c < R; c++) {
				if (x.next[c] != null) {
					tmpKeys.enqueue(prefix + c);
					q.enqueue(x.next[c]);
				}
			}
		}
		return keys;
	}
	
	public Iterable<String> keysThatMatch(String pat) {
		
	}
	
	public int longestPrefixOf(String s){
		
	}

	public int size(){
		Queue<Node> q = new Queue<Node>();
		q.enqueue(root);
		Node x = null;
		int size = 0;
		while (!q.isEmpty()) {
			x = q.dequeue();
			if (x.val != null) {
				size++;
			}
			for (char c = 0; c < R; c++) {
				if (x.next[c] != null) {
					q.enqueue(x.next[c]);
				}
			}
		}

		return size;
	}
	
	public void delete(String key) {
		
	}
	
	public boolean isEmpty(){
		
	}
	
	public boolean contains(String key){
		
	}
	
}