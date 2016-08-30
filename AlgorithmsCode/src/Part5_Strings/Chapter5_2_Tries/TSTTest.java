package Part5_Strings.Chapter5_2_Tries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Part5_Strings.Chapter5_2_Tries.TrieST.Node;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.Stopwatch;

public class TSTTest {
	
	public static void main(String[] args) {
		File tFile = new File("algs4-data//shellsST.txt");

		try {
			System.setIn(new FileInputStream(tFile.getAbsolutePath()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TrieST<Integer> st = new TrieST<Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
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
}