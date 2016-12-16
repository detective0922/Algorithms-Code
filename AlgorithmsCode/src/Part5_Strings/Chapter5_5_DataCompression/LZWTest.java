package Part5_Strings.Chapter5_5_DataCompression;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

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
			Huffman.compress();
			System.setOut(standardOut);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		Huffman.BinaryDump(binFile);
		
	}

}

