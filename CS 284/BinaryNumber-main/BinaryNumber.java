// Author: Jason Rossi
// Date: 2/2/20
// Honor Code: I pledge my Honor that I have abided by the Stevens Honor System. 

import java.lang.Math;
import java.util.Arrays;

public class BinaryNumber 
{
	private int data[]; 
	private int length; 

	/**
	 * Constructs a binary number with a certain length and consisting only of zeroes.
	 * @param length the length that was created in the constructor. 
	 */
	
	public BinaryNumber(int length) 
	{
		if (length < 0)
		{
			throw new IllegalArgumentException("Length should be a non-negative number.");
		}
		data = new int[length];
		this.length = length;
		for (int i = 0; i < length; i++) 
		{
			data[i] = 0;
		}
	}
	
	/**
	 * Constructs a binary number, given a string.
	 * @param str the string that is turned into a binary number. 
	 */
	
	public BinaryNumber(String str) 
	{
		length = str.length();
		data = new int[length];
		for (int i = 0; i < length; i++) 
		{
			int value = Character.getNumericValue(str.charAt(i));
			if (value != 0 && value != 1)
			{
				throw new IllegalArgumentException("The string must have only 0s and 1s.");
			}
			else
			{
			data[i] = value;
			}
		}
	}
	
	/**
	 * Determine the length of a binary number. 
	 * @return the length of a binary number.
	 */
	
	public int getLength() 
	{
		return length;
	}
	
	/**
	 * Returns the integer array representing the binary number.
	 * @return the integer array representing the binary number.
	 */
	
	public int[] getInnerArray() 
	{
		return data;
	}
	
	/**
	 * This is for obtaining a digit of a binary number given 
	 * a certain index.
	 * @param index the index starts at 0 and is used throughout the code
	 * to determine the digit at that index.
	 * @return the the digit at the given index. 
	 */
	
	public int getDigit(int index) 
	{
		if (index < 0)
		{
			throw new IllegalArgumentException("Index should be a positive number less than length.");
		}
		else if (index > length)
		{
			throw new IllegalArgumentException("Index should be less than the length.");
		}
		else {
			if (length > index) 
			{
				return data[index];
			}
			else
			{
				return -1;
			}
		}
	}
	
	/**
	 * Produces and returns the decimal form of a bianry number. 
	 * @return the decimal form of a binary number.
	 */
	
	public int toDecimal() 
	{
		int total = 0;
		for (int i = 0; i<length; i++) 
		{
			total += data[i]*Math.pow(2,length-i-1);
		}
		return total;
	}
	
	/**
	 * Shifts the number to the left or right a certain amount of index 
	 * steps and adds or subtracts from the number's length accordingly
	 * with 0s and 1s.
	 * @param direction the direction the number is going to go in.
	 * @param amount how many index steps the number goes in that direction.
	 */
	
	public void bitShift(int direction, int amount) 
	{
		if (amount < 0)
		{
			throw new IllegalArgumentException("Amount should be a non-negative number.");
		}
		else
		{
			if (direction == 1) 
			{
				if (amount > length)
				{
					throw new IllegalArgumentException("If the direction is right, the amount cannot be greter than the length.");
				}
				else
				{
					int array[] = new int[length - amount];
					for (int i = 0; i < length - amount; i++)
					{
						array[i] = data[i];
					}
					data = array;
					length = length - amount;
				}
			}
			else if (direction == -1) 
			{
				{
					int buffer[] = new int[length + amount];
					for (int i = 0; i < length + amount; i++) 
					{
						if (i < length)
						{
							buffer[i] = data[i];
						}
					}
					data = buffer;
				}
			length = length + amount;
			}
			else
			{
				throw new IllegalArgumentException("Direction should only be -1 or 1.");
			}
		}
	}
	
	/**
	 * This computes the bitwise or of the two numbers.
	 * @param bn1 A binary number that must be the same length as bn2.
	 * @param bn2 A binary number that must be the same length as bn1.
	 * @return the bitwise or of the two numbers.
	 */
	
	public static int[] bwor(BinaryNumber bn1, BinaryNumber bn2) 
	{
		if (bn1.length != bn2.length)
		{
			throw new IllegalArgumentException("The length of the arguments must be equal to each other.");
		}
			int[] result = new int[bn1.length];
			for (int i = 0; i < bn1.length; i++)
			{
				if (bn1.getDigit(i) == 0 && bn2.getDigit(i) == 0)
				{
					result[i] = 0;
				}
				else
				{
					result[i] = 1;
				}
			}
			return result;
		}
	
	/**
	 * This computes the bitwise and of the two numbers.
	 * @param bn1 A binary number that must be the same length as bn2.
	 * @param bn2 A binary number that must be the same length as bn1.
	 * @return the bitwise and of the two numbers.
	 */
	
	public static int[] bwand(BinaryNumber bn1, BinaryNumber bn2) 
	{
		if (bn1.length != bn2.length)
		{
			throw new IllegalArgumentException("The length of the arguments must be equal to each other.");
		}
		int[] result = new int[bn1.length];
		for (int i = 0; i < bn1.length; i++)
		{
			if (bn1.getDigit(i) == 1 && bn2.getDigit(i) == 1) 
			{
				result[i] = 1;
			}
			else
			{
				result[i] = 0;
			}
		}
		return result;
	}

	/**
	 * Adds the 0s in the add method.
	 * @param amount the amount of 0s necessary to be added to the result. 
	 */
	
	public void prepend(int amount) 
	{
		if (amount < 0)
		{
			throw new IllegalArgumentException("Amount cannot be negative.");
		}
		else
		{
			int[] buffer = new int[length+amount];
			for(int i = 0; i < length + amount; i++)
			{
				buffer[i] = 0;
			}
			for (int i = amount; i < length + amount; i++)
			{
				buffer[i] = data[i-amount];
			}
			data = buffer;
		}
	}
	
	/**
	 * Returns the BinaryNumber as the corresponding encoded string.
	 */
	
	public String toString() 
	{
		String str = "";
		for (int i = 0; i < length; i++)
		{
			str += data[i];
		}
		return str;
	}
	
	/**
	 * Adds two binary numbers, one is the binary number that receives the message
	 * and the other is given as a parameter. If one is longer than the other, 
	 * 0s fill the remaining indexes.
	 * @param aBinaryNumber is one of the 2 binary numbers that are added together.
	 */
	
	public void add(BinaryNumber aBinaryNumber) 
	{
		if (this.length > aBinaryNumber.getLength())
		{
			aBinaryNumber.prepend(this.length-aBinaryNumber.getLength());
		}
		else if (this.length < aBinaryNumber.getLength())
		{
			this.prepend(aBinaryNumber.getLength() - this.length);
		}
		
		String temp_str = "";
		int carrier = 0;
		for (int i = this.length-1; i >= 0; i--)
		{
			int firstDigit = this.getDigit(i) + aBinaryNumber.getDigit(i) + carrier;
			carrier = 0;
			if (firstDigit > 1)
			{
				carrier = 1;
				firstDigit -= 2;
			}
			temp_str = firstDigit + temp_str;
		}
		if (carrier == 1) 
		{
			temp_str = "1" + temp_str;
		}
		BinaryNumber temp_num = new BinaryNumber(temp_str);
		this.length = temp_num.getLength();
		this.data = temp_num.getInnerArray();
	}
	
	public static void main(String[] args) 
	{
		BinaryNumber test1 = new BinaryNumber("0111");
		System.out.println(test1.getLength());
		BinaryNumber test2 = new BinaryNumber("0111111");
		System.out.println(test2.getLength());
		BinaryNumber test3 = new BinaryNumber("");
		System.out.println(test3.getLength());
		BinaryNumber test4 = new BinaryNumber("1101");
		System.out.println(test4.getDigit(2));
		BinaryNumber test7 = new BinaryNumber("1101");
		System.out.println(test7.toDecimal());
		BinaryNumber test8 = new BinaryNumber("11111");
		System.out.println(test8.toDecimal());
		BinaryNumber test9 = new BinaryNumber("");
		System.out.println(test9.toDecimal());
		BinaryNumber test10 = new BinaryNumber("");
		int[] array1 = test10.getInnerArray();
		System.out.println(Arrays.toString(array1));
		BinaryNumber test11 = new BinaryNumber("111");
		int[] array2 = test11.getInnerArray();
		System.out.println(Arrays.toString(array2));
		BinaryNumber test12 = new BinaryNumber("101");
		int[] array3 = test12.getInnerArray();
		System.out.println(Arrays.toString(array3));
		BinaryNumber test13 = new BinaryNumber("101");
		test13.bitShift(1, 1);
		System.out.println(test13);
		BinaryNumber test14 = new BinaryNumber("1011");
		test14.bitShift(-1,1);
		System.out.println(test14);
		BinaryNumber test15 = new BinaryNumber("1011");
		test15.bitShift(1,1);
		System.out.println(test15);
	}

}
