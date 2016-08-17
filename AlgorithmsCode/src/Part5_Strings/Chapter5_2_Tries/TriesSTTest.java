package Part5_Strings.Chapter5_2_Tries;

public class TriesSTTest {

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
		
	}
	
	public Iterable<Value> keysWithPrefix(String prefix){
		
	}
	
	public int size(){
		
	}
	
}