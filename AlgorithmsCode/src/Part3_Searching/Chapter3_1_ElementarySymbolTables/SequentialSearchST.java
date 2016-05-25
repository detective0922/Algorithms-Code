package Part3_Searching.Chapter3_1_ElementarySymbolTables;

import java.util.ArrayList;
import java.util.List;

public class SequentialSearchST<Key, Value>{
	
	private Node first;
	
	private class Node {
		Key key;
		Value value;
		Node next;

		public Node(Key key, Value val, Node next) {
			this.key = key;
			this.value = val;
			this.next = next;
		}
	}
	
	public Value get(Key key) {
		/*Node node = first;
		while (node != null) {
			if (key.equals(node.key)) {
				return node.value;
			}
			node = node.next;
		}*/
		for (Node node = first; node != null; node = node.next) {
			if (key.equals(node.key)) {
				return node.value;
			}
		}
		return null;
	}
	
	public void put(Key key, Value value) {
		/*Node current = first;
		while (current != null) {
			if (key.equals(current.key)) {
				current.value = value;
				return;
			}
			current = current.next;
		}*/
		for(Node node = first;node!=null;node = node.next){
			if (key.equals(node.key)) {
				node.value = value;
				return;
			}
		}
		first = new Node(key, value, first);
	}
	
	public boolean contains(Key key) {
		if (first == null)
			return false;
		return get(key) != null;
	}
	
	public Iterable<Key> keys(){
		List<Key> keys = new ArrayList<Key>();
		for (Node node = first; node.next != null; node = node.next) {
			keys.add(node.key);
		}
		return keys;		
	}
}
