package Part5_Strings.Chapter5_5_DataCompression;

public class HuffmanTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if      (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();

	}

}

class Huffman {
	private static class Node implements Comparable<Node> {
		
		private char ch;
		private int freq;
		private final Node left, right;

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}
}
