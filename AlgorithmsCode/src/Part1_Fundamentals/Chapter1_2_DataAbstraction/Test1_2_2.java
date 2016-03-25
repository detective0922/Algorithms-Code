package Part1_Fundamentals.Chapter1_2_DataAbstraction;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdOut;

public class Test1_2_2 {

	public static void main(String args[]) {
		int N = 10;
		Interval1D[] intervals = createIntervals(N);
		boolean[][] intersectsPairs = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				intersectsPairs[i][j] = false;
			}
		}
		
		for (int i = 0; i < N; i++) {
			Interval1D sourceInterval = intervals[i];
			for (int j = 0; j < N; j++) {
				if(intersectsPairs[i][j] == false){
					Interval1D destinationInterval = intervals[j];
					boolean isIntersect = sourceInterval.intersects(destinationInterval);
					if (isIntersect == true) {
						intersectsPairs[i][j] = true;
						intersectsPairs[j][i] = true;
					}			
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (intersectsPairs[i][j] == true) {
					StdOut.println("Pair is " + intervals[i] + ", "
							+ intervals[j]);
				}
			}
		}

	}

	public static Interval1D[] createIntervals(int N){
		List<Interval1D> intervals = new ArrayList<Interval1D>();
		double x = 0;
		double y = 0;
		for (int i = 0; i < N; i++) {
			x = Math.random();
			y = Math.random();
			Interval1D interval = null;
			if (x <= y) {
				interval = new Interval1D(x, y);
			} else {
				interval = new Interval1D(y, x);
			}
			intervals.add(interval);
		}
		return intervals.toArray(new Interval1D[intervals.size()]);
	}

}
