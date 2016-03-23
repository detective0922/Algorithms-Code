package one;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D;
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.*;

import edu.princeton.cs.algs4.Count;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class test {
	
	public static void main(String args[]){
		
		/*System.out.println(mystery(2,25));
		System.out.println(mystery(3,11));*/
		
		/*long time1 = System.nanoTime();
		//String string = Long.toBinaryString(Long.MAX_VALUE);
		//String string = toBinaryString(Long.MAX_VALUE);
		//int a = lg(1023);
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				PointFrame frame = new PointFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);

			}
		});
		
		long time2 = System.nanoTime();
		System.out.println(time1);
		System.out.println(time2);
		System.out.println(time2 - time1);
		System.out.println();*/
		/*int[][] testArray = {
				{1,2,3},
				{4,5,6},
				{7,8,9},
				{10,11,12}
		};
		transposeArray(testArray);*/
		//System.out.print(gcd(33, 44));	
		/*File file = new File("algs4-data//tinyW.txt");
		File file2 = new File("algs4-data//tinyT.txt");
		int[] whitelist = new In(file).readAllInts();
		int[] inList = new In(file2).readAllInts();
		Arrays.sort(whitelist);
		for (int i = 0; i < inList.length; i++) {
			int key = inList[i];
			if (rank1(key, whitelist) < 0) {
				StdOut.println("key:" + key);
			}
		}*/
		//StdOut.println(mystery(2, 8));
		/*for (int N = 0; N < 100; N++) {
			StdOut.println(N + " " + F(N));
		}*/
		/*for (int N = 1; N < 10; N++) {
			StdOut.println(N + " " + lnN(N));
			StdOut.println(N + " " + Math.log(N));
		}*/
		
		StdOut.println(binomial( 20, 10, 0.5));
	}
	
	//1.1.27
	public static int count = 0;
	public static double binomial(int N, int k, double p) {
		StdOut.println(++count);
		StdOut.println("N: " + N + ", k: " + k);
		if (N == 0 && k == 0)
			return 0.0;
		if (N < 0 || k < 0)
			return 0.0;
		return (1.0 - p) * binomial(N - 1, k, p) + p
				* binomial(N - 1, k - 1, p);
	}
	
	
	//1.1.22
	public static int rank1(int key, int[] a) {
		return rank(key, a, 0, a.length - 1, 1);
	}
	
	public static int rank(int key, int[] a, int lo, int hi, int deep) {
		if (lo > hi)
			return -1;
		int mid = lo + (hi - lo) / 2;
		StdOut.println("deep: " + deep + ",lo: " + lo + ",hi: " + hi);
		deep++;
		if (key < a[mid])
			return rank(key, a, lo, mid - 1, deep);
		else if (key > a[mid])
			return rank(key, a, mid + 1, hi, deep);
		else
			return mid;
	}
	
	
	//1.1.20
	public static double lnN(int N){
		if (N == 1)
			return Math.log(1);
		return Math.log(N) + lnN(N - 1);
	}
	
	//1.1.19
	public static BigInteger F(int N){
		/*if(N == 0) return 0;
		if(N == 1) return 1;
		if (N > 1 && N < 93) {
			long[] fArray = new long[N + 1];
			fArray[0] = 0;
			fArray[1] = 1;
			for (int i = 2; i < N + 1; i++) {
				fArray[i] = fArray[i - 1] + fArray[i - 2];
			}
			return fArray[N];
		} */
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
	
	//1.1.16
	public static String exR1(int n) {
		if (n <= 0)
			return "";
		return exR1(n - 3) + n + exR1(n - 2) + n;
	}
	
	public static int rank(int key, int[] a){
		int lo = 0;
		int ho = a.length - 1;	
		while (lo < ho) {
			int mid = lo + (ho - lo) / 2;
			if (key < a[mid])
				ho = mid - 1;
			else if (key > a[mid])
				lo = mid + 1;
			else
				return mid;
		}
		return -1;
	}
	
	public static int gcd(int p, int q){
		if(q == 0) return p;
		int r = p % q;
		return gcd(q, r);
	}
	
	//1.1.9
	public static String toBinaryString(long val) {
		char[] buf = new char[64];
		int pos = 63;
		while (val != 0) {
			/*if ((val & 1) == 0)
				buf[pos--] = 0;
			else {
				buf[pos--] = 1;
			}*/
			buf[pos--] = digits[(int) (val & 1)];
			val >>>= 1;
		}
		return new String(buf, pos+1, 64-(pos+1));
	}
	
	//1.1.13
	public static void transposeArray(int[][] intArray) {
		int xLen = intArray.length;
		int yLen = intArray[0].length;
		int[][] newArray = new int[yLen][xLen];
		for (int i = 0; i < xLen; i++) {
			for (int j = 0; j < yLen; j++) {
				newArray[j][i] = intArray[i][j];
			}
		}
		
		for (int i = 0; i < newArray.length; i++) {
			for (int j = 0; j < newArray[0].length; j++) {
				StdOut.print(newArray[i][j] + ",");
			}
			StdOut.println();
		}
		
	}
	
	//1.1.14
	public static int lg(int n){
		int i = 0;
		int m = 2;
		while (m <= n) {
			m = m*2;
			i++;
		}
		return i;
	}
	
	//1.1.15
	public static int[] histogram(int[] a, int m){
		int[] array = new int[m];
		for (int i = 0; i < m; i++) {
			int count = 0;
			for (int j = 0; i < a.length; i++) {
				if (a[j] == i + 1)
					count++;
			}
			array[i] = count;
		}
		
		return array;
		
	}
	
	//1.1.18
	public static int mystery(int a, int b) {
		if (b == 0)
			return 1;
		if (b % 2 == 0)
			return mystery(a * a, b / 2);
		return mystery(a * a, b / 2) * a;

	}
	
	public static boolean isCircularkString(String s){
		return false;
	}
	
	final static char[] digits = {
		'0' , '1' , '2' , '3' , '4' , '5' ,
		'6' , '7' , '8' , '9' , 'a' , 'b' ,
		'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,
		'i' , 'j' , 'k' , 'l' , 'm' , 'n' ,
		'o' , 'p' , 'q' , 'r' , 's' , 't' ,
		'u' , 'v' , 'w' , 'x' , 'y' , 'z'
	    };
	
}

//1.2.1
class PointFrame extends JFrame {
	
	public PointFrame() {
		setTitle("PointTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		DrawComponent component = new DrawComponent();
		add(component);
	}

	private static final int DEFAULT_WIDTH = 400;
	private static final int DEFAULT_HEIGHT = 400;
}

class DrawComponent extends JComponent {
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		double leftX = 100;
		double topY = 100;
		double width = 1;
		double height = 1;
		points = new ArrayList<Point>();
		
		for (int i = 0; i < N; i++) {
			int x = new Random().nextInt(200);
			int y = new Random().nextInt(200);

			points.add(new Point(leftX + x, topY+ y));
			
		}
		
		for (Point p : points) {
			Rectangle2D rect = new Rectangle2D.Double(p.getX(), p.getY(),
					width, height);
			g2.draw(rect);
		}
		
	}
	
	private int N = 10000;
	private List<Point> points;
	
}

class Point{
	public Point(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}
	
	private double x = 0;
	private double y = 0;
}
