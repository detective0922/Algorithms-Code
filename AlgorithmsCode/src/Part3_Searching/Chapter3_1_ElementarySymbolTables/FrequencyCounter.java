package Part3_Searching.Chapter3_1_ElementarySymbolTables;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FrequencyCounter {
	public static void main(String[] args) {
		int minlen = Integer.parseInt(args[0]);
		SequentialSearchST<String, Integer> ssst = new SequentialSearchST<String, Integer>();
		while(!StdIn.isEmpty()){
			String word = StdIn.readString();
			if(word.length()<minlen)continue;
			if(!ssst.contains(word)) ssst.put(word,1);
			else ssst.put(word,ssst.get(word)+1);
		}
		
		String max = " ";
		ssst.put(max,0);
		for(String word: ssst.keys()){
			if(ssst.get(word)>ssst.get(max))
				max = word;
		}
		StdOut.println(max+", "+ssst.get(max));
	}
}

class SequentialSearchST<Key, Value>{
	
}

