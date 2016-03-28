package Part1_Fundamentals.Chapter1_3_BagsQueuesStacks;

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


class Stack<Item>{
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
	
}
