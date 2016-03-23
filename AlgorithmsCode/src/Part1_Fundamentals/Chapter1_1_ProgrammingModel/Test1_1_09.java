package Part1_Fundamentals.Chapter1_1_ProgrammingModel;

import edu.princeton.cs.algs4.StdOut;

public class Test1_1_09 {
	
	public static void main(String args[]) {
		StdOut.println(toBinaryString(666));
		//output: 1010011010
	}
	

	// refer to jdk implementation of Integer.toString(int i) and Integer.toString(i, radix)
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
	
	final static char[] digits = {
		'0' , '1' , '2' , '3' , '4' , '5' ,
		'6' , '7' , '8' , '9' , 'a' , 'b' ,
		'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,
		'i' , 'j' , 'k' , 'l' , 'm' , 'n' ,
		'o' , 'p' , 'q' , 'r' , 's' , 't' ,
		'u' , 'v' , 'w' , 'x' , 'y' , 'z'
	    };

}
