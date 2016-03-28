package Part1_Fundamentals.Chapter1_3_BagsQueuesStacks;

public class Test1_3_1 {

}

class FixedCapacityStackOfStrings {
	private String a[];
	private int N;

	public FixedCapacityStackOfStrings(int cap) {
		a = new String[cap];
		N = 0;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}

	public void push(String item) {
		a[N++] = item;
	}

	public String pop() {
		return a[--N];
	}

	//new added
	public boolean isFull() {
		return N == a.length;
	}
}
