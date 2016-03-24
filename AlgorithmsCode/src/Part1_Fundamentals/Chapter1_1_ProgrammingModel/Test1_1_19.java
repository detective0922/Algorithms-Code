package Part1_Fundamentals.Chapter1_1_ProgrammingModel;

import java.math.BigInteger;

import edu.princeton.cs.algs4.StdOut;

public class Test1_1_19 {

	public static void main(String args[]) {
		/*for (int N = 0; N < 100; N++) {
			StdOut.println(N + " " + F(N));
		}*/
		//output: 2 min, F(N) will at about N = 49
		long startTime = System.nanoTime();
		for (int N = 0; N < 100; N++) {
			StdOut.println(N + " " + betterF(N));
		}
		long estimatedTime = System.nanoTime() - startTime;
		StdOut.println("estimatedTime: " + estimatedTime + "ns");
		//output: finish all, estimatedTime: 6418682ns
	}

	public static long F(int N) {
		if (N == 0)
			return 0;
		if (N == 1)
			return 1;
		return F(N - 1) + F(N - 2);
	}
	
	public static BigInteger betterF(int N){
		/*if(N == 0) return 0;
		if(N == 1) return 1;
		if (N > 1 ) {
			long[] fArray = new long[N + 1];
			fArray[0] = 0;
			fArray[1] = 1;
			for (int i = 2; i < N + 1; i++) {
				fArray[i] = fArray[i - 1] + fArray[i - 2];
			}
			return fArray[N];
		} 
		return -1*/
		if(N == 0) return new BigInteger("0");
		if(N == 1) return new BigInteger("1");
		if(N > 1){
			BigInteger[] fArray = new BigInteger[N + 1];
			fArray[0] = new BigInteger("0");
			fArray[1] = new BigInteger("1");
			for (int i = 2; i < N + 1; i++) {
				fArray[i] = fArray[i - 1].add(fArray[i - 2]);
			}
			return fArray[N];
		}
		return new BigInteger("-1");
		
		/*if(N == 0) return 0;
		if(N == 1) return 1;
		return F(N - 1) + F(N - 2);*/
	}

}
