package Part1_Fundamentals.Chapter1_1_ProgrammingModel;

import edu.princeton.cs.algs4.StdOut;

public class Test1_1_20 {

	public static void main(String args[]) {
		for (int N = 1; N < 10; N++) {
			StdOut.println(N + ": " + lnNFactorial(N));
			StdOut.println(N + ": " + Math.log(N));
		}
		//output: 
		/*1: 0.0
		1: 0.0
		2: 0.6931471805599453
		2: 0.6931471805599453
		3: 1.791759469228055
		3: 1.0986122886681098
		4: 3.1780538303479453
		4: 1.3862943611198906
		5: 4.787491742782046
		5: 1.6094379124341003
		6: 6.579251212010101
		6: 1.791759469228055
		7: 8.525161361065415
		7: 1.9459101490553132
		8: 10.60460290274525
		8: 2.0794415416798357
		9: 12.80182748008147
		9: 2.1972245773362196*/
	}

	public static double lnNFactorial(int N) {
		if (N == 1)
			return Math.log(1);
		return Math.log(N) + lnNFactorial(N - 1);
	}

}
