package Part1_Fundamentals.Chapter1_3_BagsQueuesStacks;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Test1_3_8 {
	public static void main(String args[]){
		String inputStr = "It was - the best - of times - - - it was - the - -";
		String[] strs = inputStr.split(" ");
		StdOut.println("old length:" + strs.length);
		DoublingStackOfStrings s = new DoublingStackOfStrings();
		for (String item : strs) {
			s.push(item);
		}
		String[] strArray = s.getArray();
		StdOut.println("new length:" + strArray.length);
		for (String item : strArray) {
			StdOut.print(item + ", ");
		}
		//output:
		//old length:17
		//new length:32
		//It, was, -, the, best, -, of, times, -, -, -, it, was, -, the, -, -, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
	}
	

}

class DoublingStackOfStrings implements Iterable<String> {
    private String[] a;
    private int N;

    public DoublingStackOfStrings() {
        a = new String[2];
        N = 0;
    }

    // is the stack empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // resize the underlying array holding the elements
    private void resize(int capacity) {
        String[] temp = new String[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    // push a new item onto the stack
    public void push(String item) {
        if (N == a.length) resize(2*a.length);
        a[N++] = item;
    }

    // delete and return the item most recently added
    public String pop() {
        if (isEmpty()) throw new RuntimeException("Stack underflow error");
        String item = a[--N];
        a[N] = null;  // to avoid loitering
        if (N > 0 && N == a.length/4) resize(a.length/2);
        return item;
    }

    public Iterator<String> iterator() {
        return new ReverseArrayIterator();
    }
    
    //added for 1.3.7
    public String[] getArray(){
    	return a;
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ReverseArrayIterator implements Iterator<String> {
        private int i = N;
        public boolean hasNext()  { return i > 0;                               }
        public void remove()      { throw new UnsupportedOperationException();  }

        public String next() {
            if (!hasNext()) throw new NoSuchElementException();
            return a[--i];
        }
    }

}

