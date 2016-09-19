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
        
        StdOut.println("keysWithPrefix(\"shor\"):");
        for (String s : st.keysWithPrefix("sh"))
            StdOut.println(s);
        StdOut.println();
        
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
			/*if (d == len - 1) {
				return (Value) x.val;
			}*/
			char c = key.charAt(d);
			//x = x.next[c];
			if (c < x.c) {
				x = x.left;
			} else if (c > x.c) {
				x = x.right;
			} else if (d < len - 1) {
				x = x.mid;
				d++;
			} else {
				return (Value) x.val;
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
			} else if(d < len -1 ){
				if(x.mid==null){
					x.mid = new Node();
					x.mid.c = key.charAt(d + 1);
				}
				q.push(x.mid);
				d++;
			} else {
				x.val = val;
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
		//return keysWithPrefix("");
		//TODO
		Queue<String> keys = new Queue<String>();
		String prefix = "";
		Node x = root;
		
		if (x.val != null) {
			keys.enqueue(prefix);
		}
		
		Queue<String> tmpKeys = new Queue<String>();
		Queue<Node> q = new Queue<Node>();
		q.enqueue(x);
		//prefix = prefix + x.mid.c;
		tmpKeys.enqueue(prefix);
		
		while (!q.isEmpty()) {
			x = q.dequeue();
			prefix = tmpKeys.dequeue();
			
			if (x.val != null) {
				keys.enqueue(prefix + x.c);
			}

			if(x.left !=null){
				tmpKeys.enqueue(prefix);
				q.enqueue(x.left);
			}
			if(x.mid !=null){
				tmpKeys.enqueue(prefix + x.c);
				q.enqueue(x.mid);
			}
			if(x.right !=null){
				tmpKeys.enqueue(prefix);
				q.enqueue(x.right);
			}
		}
		return keys;
	}
	
	/*public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> queue = new Queue<String>();
        Node x = get(root, prefix, 0);
        if (x == null) return queue;
        if (x.val != null) queue.enqueue(prefix);
        collect(x.mid, new StringBuilder(prefix), queue);
        return queue;
    }

    // all keys in subtrie rooted at x with given prefix
    private void collect(Node x, StringBuilder prefix, Queue<String> queue) {
        if (x == null) return;
        collect(x.left,  prefix, queue);
        if (x.val != null) queue.enqueue(prefix.toString() + x.c);
        collect(x.mid,   prefix.append(x.c), queue);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(x.right, prefix, queue);
    }
    
    private Node get(Node x, String key, int d) {
        if (key == null) throw new NullPointerException();
        if (key.length() == 0) throw new IllegalArgumentException("key must have length >= 1");
        if (x == null) return null;
        char c = key.charAt(d);
        if      (c < x.c)              return get(x.left,  key, d);
        else if (c > x.c)              return get(x.right, key, d);
        else if (d < key.length() - 1) return get(x.mid,   key, d+1);
        else                           return x;
    }*/
	
	public Iterable<String> keysWithPrefix(String prefix){	
		Queue<String> keys = new Queue<String>();
		int prefixLen = prefix.length();
		Node x = root;
		
		int d = 0;
		while (d < prefixLen - 1) {
			char c = prefix.charAt(d);
			if (c < x.c) {
				x = x.left;
			} else if (c > x.c) {
				x = x.right;
			} else {
				x = x.mid;
				d++;
			}
		}
		
		if (x.val != null) {
			keys.enqueue(prefix);
		}
		
		Queue<String> tmpKeys = new Queue<String>();
		Queue<Node> q = new Queue<Node>();
		q.enqueue(x.mid);
		//prefix = prefix + x.mid.c;
		tmpKeys.enqueue(prefix);
		
		while (!q.isEmpty()) {
			x = q.dequeue();
			prefix = tmpKeys.dequeue();
			
			if (x.val != null) {
				keys.enqueue(prefix + x.c);
			}

			if(x.left !=null){
				tmpKeys.enqueue(prefix);
				q.enqueue(x.left);
			}
			if(x.mid !=null){
				tmpKeys.enqueue(prefix + x.c);
				q.enqueue(x.mid);
			}
			if(x.right !=null){
				tmpKeys.enqueue(prefix);
				q.enqueue(x.right);
			}
		}
		return keys;
	}
	
	public boolean contains(String key){
		return get(key) != null;
	}
	
	public Iterable<String> keysThatMatch(String pat) {
		Queue<String> keys = new Queue<String>();
		int prefixLen = pat.length();
		Node x = root;
		
		int d = 0;
		while (d < prefixLen - 1) {
			char c = pat.charAt(d);
			if (c < x.c) {
				x = x.left;
			} else if (c > x.c) {
				x = x.right;
			} else {
				x = x.mid;
				d++;
			}
		}
		
		if (x.val != null) {
			keys.enqueue(pat);
		}
		
		Queue<String> tmpKeys = new Queue<String>();
		Queue<Node> q = new Queue<Node>();
		q.enqueue(x.mid);
		//prefix = prefix + x.mid.c;
		tmpKeys.enqueue(pat);
		
		while (!q.isEmpty()) {
			x = q.dequeue();
			pat = tmpKeys.dequeue();
			
			if (x.val != null) {
				keys.enqueue(pat + x.c);
			}

			if(x.left !=null){
				tmpKeys.enqueue(pat);
				q.enqueue(x.left);
			}
			if(x.mid !=null){
				tmpKeys.enqueue(pat + x.c);
				q.enqueue(x.mid);
			}
			if(x.right !=null){
				tmpKeys.enqueue(prefix);
				q.enqueue(x.right);
			}
		}
		return keys;
    }
	
	public String longestPrefixOf(String s) {
        return null;
    }
	
	
	
	
}