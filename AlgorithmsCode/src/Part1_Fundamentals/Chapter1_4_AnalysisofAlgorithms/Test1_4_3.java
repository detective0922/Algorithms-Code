package Part1_Fundamentals.Chapter1_4_AnalysisofAlgorithms;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.ThreeSum;
import edu.princeton.cs.algs4.ThreeSumFast;

public class Test1_4_3 {
	private static final int MAXIMUM_INTEGER = 1000000;
	
	public static double timeTrial(int N, int[] inList) {
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = inList[i];
        }
        Stopwatch timer = new Stopwatch();
        ThreeSum.count(a);
        //ThreeSumFast.count(a);
        return timer.elapsedTime();
    }

    
    public static void main(String[] args) { 
    	File tFile = new File("algs4-data//1Mints.txt");
		int[] inList = new In(tFile).readAllInts();
		List<Integer> nList = new ArrayList<Integer>();
		List<Double> timeList = new ArrayList<Double>();
		StdDraw.setXscale(0, 200000);
		StdDraw.setYscale(0, 100);
		for (int N = 50; N < inList.length; N += N) {
            double time = timeTrial(N, inList);
            StdOut.printf("%7d %5.1f\n", N, time);
            nList.add(N);
            timeList.add(time);
            int size = nList.size();
			if (size > 1) {
				int x0 = nList.get(size - 2);
				double y0 = timeList.get(size - 2);
				StdDraw.line(x0, y0, N, time);
			}
        } 
    } 
    
}
