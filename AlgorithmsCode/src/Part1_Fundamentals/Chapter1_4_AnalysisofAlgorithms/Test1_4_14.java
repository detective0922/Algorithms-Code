package Part1_Fundamentals.Chapter1_4_AnalysisofAlgorithms;

import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.ThreeSum;

public class Test1_4_14 {
	
	public static void main(String[] args)  { 
		File tFile = new File("algs4-data//1Mints.txt");
		int[] inList = new In(tFile).readAllInts();
		for (int N = 50; N < inList.length; N += N) {
            double time = timeTrial(N, inList);
            StdOut.printf("%7d %5.1f\n", N, time);
		}
    }
	
	public static double timeTrial(int N, int[] inList) {
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = inList[i];
        }
        Stopwatch timer = new Stopwatch();
        int cnt = FourSum.count(a);
        double elapsedTime = timer.elapsedTime();
        //StdOut.println(cnt);
        return elapsedTime;
    }
}
	
class FourSum {

	public static int count(int[] a) {
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                for (int k = j+1; k < N; k++) {
                    for (int l = k+1; l < N; l++) {
                        if (a[i] + a[j] + a[k] + a[l] == 0) {
                            cnt++;
                        }
                    }
                }
            }
        }
        return cnt;
    }
}
