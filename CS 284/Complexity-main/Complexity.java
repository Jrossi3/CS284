// I pledge my honor that I have abided by the Stevens Honor System. 
// Name: Jason Rossi

public class Complexity 
{
	
	/**
	 * This program will execute n^2 times.
	 * @param n the number inputed into the program. 
	 */
	
	public static void method1(int n) 
	{
		int counter = 0;
		for (int i = 0; i < n; i++) 
		{
			for(int j = 0; j<n; j++) 
			{
				System.out.println (" Operation "+ counter );
				counter ++;
			}
		}
	}
	
	/**
	 * This program will execute n^3 times.
	 * @param n the number inputed into the program. 
	 */
	
	public static void method2(int n) 
	{
		int counter = 0;
		for (int i = 0; i < n; i++) 
		{
			for(int j = 0; j<n; j++) 
			{
				for(int k = 0; k<n;k++) 
				{
					System.out.println (" Operation "+ counter );
					counter ++;
				}
			}
		}
	}
	
	/**
	 * This program will execute log(n) times.
	 * @param n the number inputed into the program. 
	 */
	
	public static void method3(int n) 
	{
		int counter = 0;
		for(int i=1; i < n; i *= 2) 
		{
			System.out.println (" Operation "+ counter );
			counter ++;
		}
	}
	
	/**
	 * This program will execute nlog(n) times.
	 * @param n the number inputed into the program. 
	 */
	
	public static void method4(int n) 
	{
		int counter = 0;
		for (int j=0; j<n; j++)
		{
			for(int i=2; i < n; i *= 2) 
			{
				System.out.println (" Operation "+ counter );
				counter ++;
			}
		}
	}
	
	/**
	 * This program will execute log(log(n)) times.
	 * @param n the number inputed into the program. 
	 */
	
	public static void method5(int n) 
	{
		int counter = 0;
		for(int i=2; i<n; i = i*i) 
		{
			System.out.println (" Operation "+ counter );
			counter ++;
		}
	}
}