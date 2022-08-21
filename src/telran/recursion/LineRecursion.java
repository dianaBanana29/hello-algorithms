package telran.recursion;
public class LineRecursion {
public static long factorial(int n) {
	if(n == 0) {
		return 1;
	}
	return n * factorial(n-1);
}
/**
 * 
 * @param a either negative or positive
 * @param b positive
 * @return a^b
 */

public static long pow(long a, long b) {
boolean isEven = b % 2 == 0;
boolean isPositive = a > 0;
if(b < 0) {
	throw new IllegalArgumentException();
}
long res = power(a, b);
return (!isPositive && !isEven) ? -res : res;
}


private static long power(long a, long b) {
	if(b == 0) {
		return 1;
		}

	if(a < 0) {
		a = -a;
	   }
	return multiplication(a, power(a, b - 1));
	
}
private static long multiplication(long a, long b) {
	if(b == 0) {
		return 0;
	}
	
	return a + multiplication(a, b - 1);
}


public static int square( int x) {
	if(x < 0) {
		x = -x;
	}
	if(x == 1) {
		return 1;
	}
	return x + x  + square(x - 1) - 1;
}


public static int sum( int ar[]) {

return	sum(0, ar);

}


private static int sum(int firstIndex, int[] ar) {
	if(firstIndex >= ar.length) {
		return 0;
	}
	return ar[firstIndex] + sum(firstIndex + 1, ar);
}
}