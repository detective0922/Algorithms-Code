package Part1_Fundamentals.Chapter1_3_BagsQueuesStacks;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;

public class Test1_3_2 {
	
	public static void main(String args[]){
		String inputStr = "It was - the best - of times - - - it was - the - -";
		String[] strs = inputStr.split(" ");
		Stack<String> s = new Stack<String>();
		for (String item : strs) {
			if (!item.equals("-")) {
				s.push(item);
			} else if (!s.isEmpty()) {
				StdOut.print(s.pop() + " ");
			}
		}
		StdOut.println("(" + s.size() + " left on stack)");
		//output:
		//was best times of the was the it (1 left on stack)
	}

}


class Stack<Item> implements Iterable<Item>{
	private Node first;
	private int N;
	
	private class Node {
		Item item;
		Node next;
	}
	
	public boolean isEmpty(){
		return first == null;
	}
	
	public int size(){
		return N;
	}
	
	public void push(Item item) {
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		N++;
	}
	
	public Item pop() {
		Item item = first.item;
		first = first.next;
		N--;
		return item;
	}
	
	//1.3.7
	public Item peek() {
		return first.item;
	}

	
	//1.3.12
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new ListIterator(first);
	}
	
	private class ListIterator implements Iterator<Item> {
		private Node currect;
		
		public ListIterator(Node first){
			this.currect = first;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return currect != null;
		}

		@Override
		public Item next() {
			// TODO Auto-generated method stub
			Item item = currect.item;
			currect = currect.next;
			return item;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub

		}

	}
	
}
