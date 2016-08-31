package Part5_Strings.Chapter5_2_Tries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class TSTTest {
	
	public static void main(String[] args) {
		File tFile = new File("algs4-data//shellsST.txt");

		try {
			System.setIn(new FileInputStream(tFile.getAbsolutePath()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TST<Integer> st = new TST<Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }		
        
        if (st.size() < 100) {
            StdOut.println("keys(\"\"):");
            for (String key : st.keys()) {
                StdOut.println(key + " " + st.get(key));
            }
            StdOut.println();
        }
	}

}


class TST<Value> {
	private Node root;
	private int R =1;

	private class Node {
		private char c;
		private Object val;
		private Node left, mid, right;
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
			//x = x.next[c];
			if (c < x.c) {
				x = x.left;
			} else if (c > x.c) {
				x = x.right;
			} else {
				x = x.mid;
				d++;
			}
		}
		return null;
	}
	
	public void put(String key, Value val) {
		if (root == null) {
			root = new Node();
		}
		
		int d = 0;
		int len = key.length();
		Stack<Node> q = new Stack<Node>();
		Node x = root;
		q.push(x);
		while (!q.isEmpty()) {
			x = q.pop();
			if (d == len) {
				x.val = val;
				break;
			}
			char c = key.charAt(d);
			if (x == null) {
				x = new Node();
				x.c = c;
			}
			if (c < x.c) {
				if(x.left==null){
					x.left = new Node();				
				}
				q.push(x.left);
			} else if (c > x.c) {
				if(x.right==null){
					x.right = new Node();				
				}
				q.push(x.right);
			} else {
				if(x.mid==null){
					x.mid = new Node();				
				}
				q.push(x.mid);
				d++;
			}
		}
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
		/*Queue<String> keys = new Queue<String>();
		Stack<String> tmpKeys = new Stack<String>();
		Stack<Node> q = new Stack<Node>();
		q.push(x);
		tmpKeys.push(prefix);*/
		while (!q.isEmpty()) {
			x = q.dequeue();
			prefix = tmpKeys.dequeue();
			//x = q.pop();
			//prefix = tmpKeys.pop();
			if (x.val != null) {
				keys.enqueue(prefix);
				//keys.push(prefix);
			}
			for (char c = 0; c < R; c++) {
				if (x.next[c] != null) {
					tmpKeys.enqueue(prefix + c);
					q.enqueue(x.next[c]);
					//tmpKeys.push(prefix + c);
					//q.push(x.next[c]);
				}
			}
		}
		return keys;
	}
	
	
}