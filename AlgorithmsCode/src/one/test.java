package one;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.*;

public class test {
	
	public static void main(String args[]){
		
		/*System.out.println(mystery(2,25));
		System.out.println(mystery(3,11));*/
		
		long time1 = System.nanoTime();
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
		System.out.println();
	}
	
	//1.1.18
	public static int mystery(int a, int b){
		if (b == 0)
			return 0;
		if (b % 2 == 0)
			return mystery(a + a, b / 2);
		return mystery(a + a, b / 2) + a;
		
		
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
