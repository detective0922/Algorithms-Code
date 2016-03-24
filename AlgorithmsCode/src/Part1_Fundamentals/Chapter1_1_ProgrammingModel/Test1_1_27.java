package Part1_Fundamentals.Chapter1_1_ProgrammingModel;

import edu.princeton.cs.algs4.StdOut;

public class Test1_1_27 {

	public static void main(String args[]) {
		long startTime = System.nanoTime();
		//int count = 0;
		StdOut.println(binomial(30, 15, 0.5));
		long estimatedTime = System.nanoTime() - startTime;
		StdOut.println("count: " + count);
		StdOut.println("estimatedTime: " + estimatedTime + "ns");
		//output:
		//0.14446444809436798
		//count: 2438328997
		//estimatedTime: 7392948159ns
		
		count = 0;
		startTime = System.nanoTime();
		StdOut.println(betterBinomial(30, 15, 0.5));
		estimatedTime = System.nanoTime() - startTime;
		StdOut.println("count: " + count);
		StdOut.println("estimatedTime: " + estimatedTime + "ns");
		//0.14446444809436798
		//count: 65774
		//estimatedTime: 919665ns
		//after improved:
		//0.14446444809436798
		//count: 375
		//estimatedTime: 97037ns
	}

	public static long count = 0;
	public static double binomial(int N, int k, double p) {
		count++;
		if (N == 0 && k == 0)
			return 1.0;
		if (N < 0 || k < 0)
			return 0.0;
		return (1.0 - p) * binomial(N - 1, k, p) + p
				* binomial(N - 1, k - 1, p);
	}
	
	public static double betterBinomial(int N, int k, double p) {
		
		double[][] fArray = new double[N + 1][k + 1];
		for (int i = 0; i < N + 1; i++) {
			for (int j = 0; j < k + 1; j++) {
				fArray[i][j] = -1;
			}
		}
		return betterBinomial(fArray, N, k, p);
		
	}
	
	public static double betterBinomial(double[][] fArray, int N, int k,
			double p) {
		
		if (N == 0 && k == 0)
			return 1.0;
		if (N < 0 || k < 0)
			return 0.0;
		if (fArray[N][k] == -1) {
			count++;
			fArray[N][k] = (1.0 - p) * betterBinomial(fArray, N - 1, k, p) + p
					* betterBinomial(fArray, N - 1, k - 1, p);
		}
		return fArray[N][k];

	}

}
