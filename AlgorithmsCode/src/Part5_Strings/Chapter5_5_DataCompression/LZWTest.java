package Part5_Strings.Chapter5_5_DataCompression;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.TST;

public class LZWTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File tFile = new File("algs4-data//abra.txt");
		File binFile = new File("bin.txt");
		PrintStream standardOut = System.out;
		
		try {
			System.setIn(new FileInputStream(tFile.getAbsolutePath()));
			PrintStream ps = new PrintStream(new FileOutputStream(binFile));
			System.setOut(ps);
			//edu.princeton.cs.algs4.Huffman.compress();
			LZW.compress();
			System.setOut(standardOut);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		LZW.HexDump(binFile);
		
	}

}

class LZW{
	private static final int R = 256;
	private static final int L = 4096;
	private static final int W = 12;
	
	public static void compress() {
		
		String input = BinaryStdIn.readString();
		TST<Integer> tst = new TST<Integer>();
		
		for (int i = 0; i < R; i++) {
			tst.put("" + (char) i, i);
		}
		
		int code = R + 1;
		
		while (input.length() > 0) {
			String s = tst.longestPrefixOf(input);
			BinaryStdOut.write(tst.get(s), W);

			int t = s.length();
			if (t < input.length() && code < L) {
				tst.put(input.substring(0, t + 1), code++);
			}

			input = input.substring(t);
		}
		
		BinaryStdOut.write(R, W);
		BinaryStdOut.close();
		
	}
	
	public static void expand() {
		String[] st = new String[L];
		int i;
		
		for (i = 0; i < R; i++) {
			st[i] = ""+(char)i;			
		}
		st[i++] = "";
		
		int codeWord = BinaryStdIn.readInt(W);
		String val = st[codeWord];
		
		while(true) {
			BinaryStdOut.write(val);
			codeWord = BinaryStdIn.readInt(W);
			if (codeWord == R) {
				break;
			}
			
			String s = st[codeWord];
			if (i == codeWord) {
				s = val + val.charAt(0);
			}
			
			if (i < L) {
				st[i++] = val + s.charAt(0);
			}
			val = s;
		}
		
		BinaryStdOut.close();
	}
	
}

