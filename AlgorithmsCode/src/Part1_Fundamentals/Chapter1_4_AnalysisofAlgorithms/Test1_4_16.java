package Part1_Fundamentals.Chapter1_4_AnalysisofAlgorithms;

import java.io.File;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Test1_4_16 {
	
    public static void main(String[] args) { 
    	int N = 100000;
    	double[] inList = new double[N];
    	for(int i = 0; i<N;i++){
    		inList[i] = StdRandom.uniform();
    	}	
		min(inList);
    } 
    
	public static void min(double[] a) {
		Arrays.sort(a);
		int N = a.length;
		double min = Double.MAX_VALUE;
		int minIndex = 0;
		double diffabs = 0;
		for (int i = 0; i < N - 1; i++) {
			diffabs = Math.abs(a[i] - a[i + 1]);
			if (diffabs < min) {
				min = diffabs;
				minIndex = i;
			}
		}
		StdOut.println("min pair: " + min + ", " + a[minIndex] + ", "
				+ a[minIndex + 1]);
	}

}
