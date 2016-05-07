package Part3_Searching.Chapter3_3_BalancedSearchTrees;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Part3_Searching.Chapter3_2_BinarySearchTrees.BST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class FrequencyCounterForRBBST {
	public static void main(String[] args) {
		BST<String, Integer> bst = new BST<String, Integer>();
		//int minlen = 1;
		//int minlen = 8;
		int minlen = 10;
		//File tFile = new File("algs4-data//tinyTale.txt");
		//File tFile = new File("algs4-data//tale.txt");
		File tFile = new File("algs4-data//leipzig1M.txt");
		//String[] inList = new In(tFile).readAllStrings();
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
			if (!bst.contains(word))
				bst.put(word, 1);
			else
				bst.put(word, bst.get(word) + 1);
		}

		String max = " ";
		bst.put(max, 0);
		Iterable<String> keyList = bst.keys();
		for (String word : keyList) {
			if (bst.get(word) > bst.get(max))
				max = word;
		}
				
		StdOut.println(max + ", " + bst.get(max));
		StdOut.println(timer.elapsedTime());
		//output:
		//government, 24763
		//21.006
	}

}
