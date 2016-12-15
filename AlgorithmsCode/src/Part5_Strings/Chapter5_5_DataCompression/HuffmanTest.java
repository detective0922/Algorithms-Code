package Part5_Strings.Chapter5_5_DataCompression;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

import com.sun.org.apache.bcel.internal.classfile.Code;

import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StdOut;

public class HuffmanTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		     
        
		
		
		File tFile = new File("algs4-data//abra.txt");
		File binFile = new File("bin.txt");
		//In in = new In(tFile);
		try {
			System.setIn(new FileInputStream(tFile.getAbsolutePath()));
			PrintStream ps = new PrintStream(new FileOutputStream(binFile));
			System.setOut(ps);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Huffman.compress();
		
		
		
		Huffman.BinaryDump(binFile);
		
		
		/*final PipedOutputStream binOutPut = new PipedOutputStream();
		PrintStream ps = new PrintStream(binOutPut);*/
				
		
		
		/*Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				byte[] bys = new byte[4096];
				try {
					PipedInputStream binInput = new PipedInputStream();
					binInput.connect(binOutPut);
					binInput.read(bys);
					
					int bitsPerLine = 60;

			        int count = 0;
			        for (count = 0; !binInput.; count++) {
			           if (count != 0 && count % bitsPerLine == 0) StdOut.println();
			            if (BinaryStdIn.readBoolean()) StdOut.print(1);
			            else                           StdOut.print(0);
			        }
			        while(binInput.read()>0){
						if (count != 0 && count % bitsPerLine == 0) {
							StdOut.println();
						}
						if (BinaryStdIn.readBoolean()) {
							StdOut.print(1);
						} else {
							StdOut.print(0);
						}
						count++;
			        }
			        if (bitsPerLine != 0) StdOut.println();
			        StdOut.println(count + " bits");
			        
			        
					binInput.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}

			}
		};
		new Thread(runnable).start();*/
		

	}

}

class Huffman {
	
	private static int R = 256;
	
	public static void compress() {
		String s = BinaryStdIn.readString();
        char[] input = s.toCharArray();

		int[] freq = new int[R];
		for (int i = 0; i < input.length; i++) {
			freq[input[i]]++;
		}

        Node root = buildTrie(freq);

        String[] st = new String[R];
        buildCode(st, root, "");

        writeTrie(root);

        BinaryStdOut.write(input.length);
        
		for (int i = 0; i < input.length; i++) {
			String code = st[input[i]];
			for (int j = 0; j < code.length(); j++) {
				if(code.charAt(j)=='1'){
					BinaryStdOut.write(true);
				} else {
					BinaryStdOut.write(false);
				}
			}			
		}
		
		BinaryStdOut.close();
	}
	
	public static void expand() {
		Node root = readTrie();
		int N = BinaryStdIn.readInt();
		for (int i = 0; i < N; i++) {
			Node x = root;
			while(!x.isLeaf()){
				if(BinaryStdIn.readBoolean()){
					x = x.right;
				} else {
					x = x.left;
				}
			}
			BinaryStdOut.write(x.ch);			
		}
		
		BinaryStdOut.close();

	}
	
	private static class Node implements Comparable<Node> {
		
		private char ch;
		private int freq;
		private final Node left, right;
		
		public Node(char ch, int freq, Node left, Node right) {
			this.ch = ch;
			this.freq = freq;
			this.left = left;
			this.right = right;
		}
		
		public boolean isLeaf() {
			return left == null && right == null;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.freq - o.freq;
		}
		
	}
	
	private static void writeTrie(Node x){
		if(x.isLeaf()){
			BinaryStdOut.write(true);
			BinaryStdOut.write(x.ch);
			return;
		}
		BinaryStdOut.write(false);
		writeTrie(x.left);
		writeTrie(x.right);
	}
	
	private static Node readTrie() {
		if(BinaryStdIn.readBoolean()){
			return new Node(BinaryStdIn.readChar(), 0, null, null);
		}
		
		return new Node('\0', 0, readTrie(), readTrie());
	}
	
	private static Node buildTrie(int[] freq) {
		MinPQ<Node> pq = new MinPQ<Huffman.Node>();
		for (char c = 0; c < R; c++) {
			if (freq[c] > 0) {
				pq.insert(new Node(c, freq[c], null, null));
			}
		}
		
		while (pq.size() > 1) {
			Node x = pq.delMin();
			Node y = pq.delMin();
			Node parent = new Node('\0', x.freq + y.freq, x, y);
			pq.insert(parent);
		}
		
		return pq.delMin();
	}
	
	private static String[] buildCode(Node root) {
		String[] st = new String[R];
		buildCode(st, root, "");
		return st;
	}

	private static void buildCode(String[] st, Node x, String s) {
		// TODO Auto-generated method stub
		if(x.isLeaf()){
			st[x.ch] = s;
			return;
		}
		buildCode(st, x.left, "0");
		buildCode(st, x.right, "0");
	}
	
	public static void BinaryDump(File binFile) {
		
		try {
			System.setIn(new FileInputStream(binFile.getAbsolutePath()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BinaryIn binIn = null;
		try {
			binIn = new BinaryIn(new FileInputStream(binFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int bitsPerLine = 60;
		int count;
		for (count = 0; !binIn.isEmpty(); count++) {
			if (count != 0 && count % bitsPerLine == 0) {
				StdOut.println();
			}
			if (binIn.readBoolean()) {
				StdOut.print(1);
			} else {
				StdOut.print(0);
			}
		}
		if (bitsPerLine != 0) {
			StdOut.println();
		}
		StdOut.println(count + " bits");
	}
}
