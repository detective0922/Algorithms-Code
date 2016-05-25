package Part3_Searching.Chapter3_1_ElementarySymbolTables;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.Transaction;

public class FrequencyCounter {
	public static void main(String[] args) {
		SequentialSearchST<String, Integer> ssst = new SequentialSearchST<String, Integer>();
		//int minlen = 1;
		//int minlen = 8;
		int minlen = 10;
		//File tFile = new File("algs4-data//tinyTale.txt");
		//File tFile = new File("algs4-data//tale.txt");
		File tFile = new File("algs4-data//leipzig1M.txt");
		/*In in = new In(tFile);
		List<String> lineList = new ArrayList<String>();
		List<String> strList = new ArrayList<String>();
		while(in.hasNextLine()){
			lineList.add(in.readLine());
		}
		for(String line:lineList){
			strList.addAll(Arrays.asList(line.split(" ")));
		}
		String[] inList = strList.toArray(new String[strList.size()]);*/
		
		try {
			System.setIn(new FileInputStream(tFile.getAbsolutePath()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Stopwatch timer = new Stopwatch();
		while(!StdIn.isEmpty()){
		//for (int i = 0; i < inList.length; i++) {
			//String word = inList[i];
			String word = StdIn.readString();
			if (word.length() < minlen)
				continue;
			if (!ssst.contains(word))
				ssst.put(word, 1);
			else
				ssst.put(word, ssst.get(word) + 1);
		}

		String max = " ";
		ssst.put(max, 0);
		for (String word : ssst.keys()) {
			if (ssst.get(word) > ssst.get(max))
				max = word;
		}
		StdOut.println(max + ", " + ssst.get(max));
		StdOut.println(timer.elapsedTime());
		//output:
		//government, 24763
		//73.177
	}
}

