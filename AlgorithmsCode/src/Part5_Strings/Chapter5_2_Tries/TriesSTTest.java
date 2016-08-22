package Part5_Strings.Chapter5_2_Tries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

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
        for (String s : st.keysThatMatch(".he.l."))
            StdOut.println(s);
        
        StdOut.println(timer.elapsedTime());
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
		Queue<Node> q = new Queue<Node>();
		Node x = root;
		q.enqueue(x);	
		while (!q.isEmpty()) {
			x = q.dequeue();
			if (d == len) {
				x.val = val;
				break;
			}
			char c = key.charAt(d);
			if (x.next[c] == null) {
				x.next[c] = new Node();
			}
			q.enqueue(x.next[c]);
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
		
		Queue<StringBuffer> tmpKeys = new Queue<StringBuffer>();
		Queue<Node> q = new Queue<Node>();
		q.enqueue(root);
		StringBuffer prefix = new StringBuffer();		
		tmpKeys.enqueue(prefix);		
		Node x = root;
	
		char[] chars = pat.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if ('.' == chars[i]) {
				for (char c = 0; c < R; c++) {
					if (x.next[c] != null) {
						tmpKeys.enqueue(prefix.append(c));
						q.enqueue(x.next[c]);
					}
				}
			} else {
				for(StringBuffer strBuf: tmpKeys){
					strBuf.append(chars[i]);
				}				
				q.enqueue(x.next[chars[i]]);
			}
		}
		
		Queue<String> keys = new Queue<String>();
		while (!q.isEmpty()) {
			x = q.dequeue();
			prefix = tmpKeys.dequeue();
			if (x.val != null) {
				keys.enqueue(prefix);
			}		
		}
		
		return keys;
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
	}
	
	public boolean isEmpty(){
		return size() == 0;
	}
	
	public boolean contains(String key){
		return get(key) != null;
	}
	
}