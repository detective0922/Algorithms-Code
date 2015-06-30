package one;

public class test {
	
	public static void main(String args[]){
		
		/*System.out.println(mystery(2,25));
		System.out.println(mystery(3,11));*/
		
		long time1 = System.nanoTime();
		//String string = Long.toBinaryString(Long.MAX_VALUE);
		String string = toBinaryString(Long.MAX_VALUE);
		long time2 = System.nanoTime();
		System.out.println(time1);
		System.out.println(time2);
		System.out.println(time2 - time1);
		System.out.println(string);
	}
	
	public static int mystery(int a, int b){
		if (b == 0)
			return 0;
		if (b % 2 == 0)
			return mystery(a + a, b / 2);
		return mystery(a + a, b / 2) + a;
		
		
	}
	
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
