package Part1_Fundamentals.Chapter1_2_DataAbstraction;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdOut;

public class Test1_2_1 {

	public static void main(String args[]) {
		int N = 10;
		Point2D[] points = createPoints(N);
		double[][] p2pDists = new double[N][N];
		double[] minDists = new double[N];
		Point2D[] minPoints = new Point2D[N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				p2pDists[i][j] = 0.0;
			}
		}		
		for (int i = 0; i < N; i++) {
			minDists[i] = 1.0;
		}
		
		for (int i = 0; i < N; i++) {
			Point2D sourcePoint = points[i];
			for (int j = 0; j < N; j++) {
				if(p2pDists[i][j] == 0.0){
					Point2D destinationPoint = points[j];
					double dist = sourcePoint.distanceTo(destinationPoint);
					p2pDists[i][j] = dist;
					p2pDists[j][i] = dist;
					if (dist > 0 && dist < minDists[i]) {
						minDists[i] = dist;
						minPoints[i] = destinationPoint;
					}
					if (dist > 0 && dist < minDists[j]) {
						minDists[j] = dist;
						minPoints[j] = sourcePoint;
					}
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			StdOut.println("point " + i + ": " + points[i]
					+ ", minDistPoint is " + minPoints[i] + ", minDist is "
					+ minDists[i]);
			points[i].drawTo(minPoints[i]);
		}
	}

	public static Point2D[] createPoints(int N){
		List<Point2D> points = new ArrayList<Point2D>();
		double x = 0;
		double y = 0;
		for (int i = 0; i < N; i++) {
			x = Math.random();
			y = Math.random();
			Point2D point = new Point2D(x, y);
			points.add(point);
			point.draw();
		}
		return points.toArray(new Point2D[points.size()]);
	}

}
