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
			root.c = key.charAt(0);
		}
		
		int d = 0;
		int len = key.length();
		Stack<Node> q = new Stack<Node>();
		Node x = root;
		q.push(x);
		while (!q.isEmpty()) {
			x = q.pop();
			char c = key.charAt(d);
			if (d == len - 1) {
				x.val = val;
				break;
			}
			if (c < x.c) {
				if(x.left==null){
					x.left = new Node();
					x.left.c = c;
				}
				q.push(x.left);
			} else if (c > x.c) {
				if(x.right==null){
					x.right = new Node();
					x.right.c = c;
				}
				q.push(x.right);
			} else {
				if(x.mid==null){
					x.mid = new Node();
					x.mid.c = key.charAt(d + 1);
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
			if(x.left !=null){
				q.enqueue(x.left);
			}
			if(x.mid !=null){
				q.enqueue(x.mid);
			}
			if(x.right !=null){
				q.enqueue(x.right);
			}			
		}

		return size;
	}
	
	public Iterable<String> keys(){
		return keysWithPrefix("");
	}
	
	public Iterable<String> keysWithPrefix(String prefix){
		int prefixLen = prefix.length();
		Node x = root;
		
		for (int d = 0; d < prefixLen; d++) {
			char c = prefix.charAt(d);
			if (c < x.c) {
				x = x.left;
			} else if (c > x.c) {
				x = x.right;
			} else {
				x = x.mid;
			}
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

			if(x.left !=null){
				tmpKeys.enqueue(prefix + x.left.c);
				q.enqueue(x.left);
			}
			if(x.mid !=null){
				tmpKeys.enqueue(prefix + x.mid.c);
				q.enqueue(x.mid);
			}
			if(x.right !=null){
				tmpKeys.enqueue(prefix + x.right.c);
				q.enqueue(x.right);
			}
		}
		return keys;
	}
	
	
}