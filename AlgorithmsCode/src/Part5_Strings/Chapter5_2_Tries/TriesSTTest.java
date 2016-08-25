package Part5_Strings.Chapter5_2_Tries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class TriesSTTest {
	
	public static void main(String[] args){
		File tFile = new File("algs4-data//shellsST.txt");

		try {
			System.setIn(new FileInputStream(tFile.getAbsolutePath()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TrieST<Integer> st = new TrieST<Integer>();
		Stopwatch timer = new Stopwatch();
		long time1 = System.currentTimeMillis();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        
        st.put("shoree", 8);

        // print results
        if (st.size() < 100) {
            StdOut.println("keys(\"\"):");
            for (String key : st.keys()) {
                StdOut.println(key + " " + st.get(key));
            }
            StdOut.println();
        }

        StdOut.println("longestPrefixOf(\"shellsort\"):");
        StdOut.println(st.longestPrefixOf("shellsort"));
        StdOut.println();

        StdOut.println("longestPrefixOf(\"quicksort\"):");
        StdOut.println(st.longestPrefixOf("quicksort"));
        StdOut.println();

        StdOut.println("keysWithPrefix(\"shor\"):");
        for (String s : st.keysWithPrefix("shor"))
            StdOut.println(s);
        StdOut.println();

        StdOut.println("keysThatMatch(\".he.l.\"):");
        for (String s : st.keysThatMatch("s...."))
            StdOut.println(s);
        
        StdOut.println();
        st.delete("shore");        
        for (String key : st.keys()) {
            StdOut.println(key + " " + st.get(key));
        }
        
        long time2 = System.currentTimeMillis();
        StdOut.println(timer.elapsedTime());
        StdOut.println(time2 - time1);
	}

}

class TrieST<Value>{
	
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
		if (root == null) {
			root = new Node();
		}
		
		int d = 0;
		int len = key.length();
		//Queue<Node> q = new Queue<Node>();
		Stack<Node> q = new Stack<Node>();
		Node x = root;
		//q.enqueue(x);	
		q.push(x);
		while (!q.isEmpty()) {
			//x = q.dequeue();
			x = q.pop();
			if (d == len) {
				x.val = val;
				break;
			}
			char c = key.charAt(d);
			if (x.next[c] == null) {
				x.next[c] = new Node();
			}
			//q.enqueue(x.next[c]);
			q.push(x.next[c]);
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
	
	public Iterable<String> keysThatMatch(String pat) {
		
		/*Queue<String> keys = new Queue<String>();
		keysThatMatch(root, "", pat, keys);
		return keys;*/
		Node x = root;
		String prefix = "";
		Queue<String> keys = new Queue<String>();
		Queue<String> tmpKeys = new Queue<String>();
		Queue<Node> q = new Queue<Node>();		
		q.enqueue(x);		
		tmpKeys.enqueue(prefix);
		/*Stack<String> keys = new Stack<String>();
		Stack<String> tmpKeys = new Stack<String>();
		Stack<Node> q = new Stack<Node>();		
		q.push(x);		
		tmpKeys.push(prefix);*/
		while (!q.isEmpty()) {
			x = q.dequeue();
			prefix = tmpKeys.dequeue();
			//x = q.pop();
			//prefix = tmpKeys.pop();
			if (prefix.length() == pat.length() && x.val != null) {
				keys.enqueue(prefix);
				//keys.push(prefix);
				continue;
			}
			if(prefix.length() == pat.length()){
				continue;
			}
			char patC = pat.charAt(prefix.length());
			for (char c = 0; c < R; c++) {
				if (patC == '.' || patC == c) {
					if (x.next[c] != null) {
						tmpKeys.enqueue(prefix + c);
						q.enqueue(x.next[c]);
						//tmpKeys.push(prefix + c);
						//q.push(x.next[c]);
					}
				}
			}
		}
		return keys;
		
	}
	
	private void keysThatMatch(Node x, String prefix, String pat, Queue<String> keys) {
		if (x == null) {
			return;
		}
		if (prefix.length() == pat.length() && x.val != null) {
			keys.enqueue(prefix);
		}
		if(prefix.length() == pat.length()){
			return;
		}
		char patC = pat.charAt(prefix.length());		
		for (char c = 0; c < R; c++) {
			if (patC == '.' || patC == c) {
				keysThatMatch(x.next[c], prefix + c, pat, keys);
			}

		}
		
	}
	
	public String longestPrefixOf(String s){
		char[] chars = s.toCharArray();
		Stack<String> stack = new Stack<String>();
		Node x = root;
		String prefix = "";
		for (int i = 0; i < chars.length; i++) {
			if(x==null){
				break;
			}
			if (x.val != null) {
				stack.push(prefix);
			}
			x = x.next[chars[i]];
			prefix += chars[i];
		}
		if (stack.isEmpty()) {
			return null;
		}
		return stack.pop();
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
		//keysThatMatch(key);
		if (key == null) {
			return;			
		}
		int d = 0;
		int len = key.length();
		Stack<Node> q = new Stack<Node>();
		Node x = root;
		q.push(x);
		while ((!q.isEmpty()) && d <= len) {
			x = q.pop();
			if (x == null) {
				return;
			}
			if (d == len) {
				x.val = null;
				for (char c = 0; c < R; c++) {
					if (x.next[c] != null) {
						return;
					}
				}
			} else {
				char c = key.charAt(d);
				if(x.next[c]!=null){
					q.push(x.next[c]);
				} else {
					return;
				}
				d++;
			}
					
		}
		//return null;
	}
	
	public boolean isEmpty(){
		return size() == 0;
	}
	
	public boolean contains(String key){
		return get(key) != null;
	}
	
}