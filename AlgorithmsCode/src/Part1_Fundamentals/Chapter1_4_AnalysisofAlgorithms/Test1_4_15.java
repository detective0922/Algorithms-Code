package Part1_Fundamentals.Chapter1_4_AnalysisofAlgorithms;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class Test1_4_15 {
	public static void main(String[] args)  { 
		File tFile = new File("algs4-data//1Mints.txt");
		int[] inList = new In(tFile).readAllInts();
		List<Integer> nList = new ArrayList<Integer>();
		List<Double> timeList = new ArrayList<Double>();
		StdDraw.setXscale(0, 200000);
		StdDraw.setYscale(0, 100);
		for (int N = 50; N < inList.length; N += N) {
            double time = timeTrial(N, inList);
            StdOut.printf("%7d %5.4f\n", N, time);
            nList.add(N);
            timeList.add(time);
            int size = nList.size();
			if (size > 1) {
				int x0 = nList.get(size - 2);
				double y0 = timeList.get(size - 2);
				StdDraw.line(x0, y0, N, time);
			}
        }
		/*for (int N = 50; N < inList.length; N += N) {
            double time = timeTrial(N, inList);
            StdOut.printf("%7d %5.4f\n", N, time);
		}*/
    }
	
	public static double timeTrial(int N, int[] inList) {
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = inList[i];
        }
        Stopwatch timer = new Stopwatch();
        int cnt = ThreeSumFast.countFaster(a);
        double elapsedTime = timer.elapsedTime();
        StdOut.println(cnt);
        return elapsedTime;
    }

}

class TwoSumFast {

    public static int count(int[] a) {
        int N = a.length;
        Arrays.sort(a);
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            int j = Arrays.binarySearch(a, -a[i]);
            if (j > i) cnt++;
        }
        return cnt;
    }
    
    //1.4.15
	public static int countFaster(int[] a) {
        Arrays.sort(a);
        int lo = 0;
        int hi = a.length - 1;
        int cnt = 0;
		while (lo < hi) {
			if (a[lo] + a[hi] == 0) {
				cnt++;
				lo++;
				hi--;
			} else if (a[lo] + a[hi] > 0) {
				hi--;
			} else if (a[lo] + a[hi] < 0) {
				lo++;
			}
		}     
        return cnt;
	}
} 

class ThreeSumFast {

    public static int count(int[] a) {
        int N = a.length;
        Arrays.sort(a);
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                int k = Arrays.binarySearch(a, -(a[i] + a[j]));
				if (k > j) {
					cnt++;
					//StdOut.println(a[k] + ", " + a[i] + ", " + a[j]);
				}
            }
        }
        return cnt;
    } 
    
    //1.4.15
    public static int countFaster(int[] a) {
    	int N = a.length;
        Arrays.sort(a);
        /*int lo = 0;
        int hi = N - 1;*/
        int cnt = 0;      
        for (int i = 0; i < N; i++) {
        	int lo = i + 1;
        	int hi = N - 1;
        	while (lo < hi) {
				if (a[i] + a[lo] + a[hi] == 0) {
					cnt++;
					//StdOut.println(a[i] + ", " + a[lo] + ", " + a[hi]);
					lo++;
					hi--;
				} else if (a[i] + a[lo] + a[hi] > 0) {
					hi--;
				} else if (a[i] + a[lo] + a[hi] < 0) {
					lo++;
				}
    		}
        }
        return cnt;
	}
} 


