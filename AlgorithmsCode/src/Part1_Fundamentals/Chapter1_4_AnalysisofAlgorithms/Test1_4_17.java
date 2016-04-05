package Part1_Fundamentals.Chapter1_4_AnalysisofAlgorithms;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Test1_4_17 {
	public static void main(String[] args) { 
    	int N = 100000;
    	double[] inList = new double[N];
    	for(int i = 0; i<N;i++){
    		inList[i] = StdRandom.uniform();
    	}	
		max(inList);
    } 
    
	public static void max(double[] a) {
		Arrays.sort(a);
		int N = a.length;
		double max = Math.abs(a[N - 1] - a[0]);
		StdOut.println("min pair: " + max + ", " + a[N - 1] + ", " + a[0]);
	}

}
