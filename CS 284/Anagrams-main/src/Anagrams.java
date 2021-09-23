import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Jason Rossi.
 * Spring 2020
 * Section 284-A
 * I pledge my honor that I have abided by the Stevens Honor System.
 */

public class Anagrams 
{
	final Integer[] primes = {2 , 3 , 5 , 7, 11 , 13 , 17 , 19 , 23 , 29 , 31 , 37 , 41 , 43 , 47 , 53 , 59 , 61 , 67 , 71 , 73 , 79 , 83 , 89 , 97 , 101};
	Map<Character, Integer> letterTable;
	Map<Long, ArrayList<String>> anagramTable;
	
	/**
	 * This is a constructor that creates an empty anagram table
	 * and a default Anagram instance with a letter table as well. 
	 */
	
	public Anagrams() 
	{
		buildLetterTable();
		anagramTable = new HashMap<Long, ArrayList<String>>();
	}
	
	/**
	 * Receives the name of a text file containing words. 
	 * @param s the name of a text file. 
	 * @throws IOException if the file has not been found or is not readable. 
	 */
	
	private void processFile(String s) throws IOException 
	{
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine ;
		while ((strLine=br.readLine()) != null) 
		{
			this.addWord(strLine);
		}
		br.close ();
	}
	
	/**
	 * Builds the hash table letterTable, where each letter is given a prime number value. 
	 */
	
	private void buildLetterTable() 
	{
		Character alphabet[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		letterTable = new HashMap<Character, Integer>();
		for(int i = 0; i<26; i++) 
		{
			letterTable.put(alphabet[i], primes[i]);
		}
	}
	
	/**
	 * Computes the hash code for String s.
	 * @param s the string used to be computed.
	 * @return the Long hash code
	 */
	
	private long myHashCode(String s) 
	{
		if(s == null) 
		{
			throw new NullPointerException("String cannot be null");
		}
		Long a = 1L;
		for (Character b : s.toCharArray()) 
		{
			a *= (long)letterTable.get(b);
		}
		return a;
	}
	
	/**
	 * Computes the hash code of the string s passed as argument, and
	 * adds this word to the hash table anagramTable
	 * @param s is the string that gets added to the hash table anagramTable
	 */
	
	private void addWord(String s) 
	{
		if(s == null) 
		{
			throw new NullPointerException("String cannot be null");
		}
		Long x = myHashCode(s);
		if(anagramTable.get(x) == null) 
		{
			ArrayList<String> t = new ArrayList<String>();
			t.add(s);
			anagramTable.put(x, t);
		}
		else 
		{
			anagramTable.get(x).add(s);
		}
	}
	
	/**
	 * @return the entries in the anagramTable that have the largest number of anagrams
	 */
	
	private ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries() 
	{
		int n = 0;
		ArrayList<Map.Entry<Long,ArrayList<String>>> max = new ArrayList<Map.Entry<Long,ArrayList<String>>>();
		for(Entry<Long,ArrayList<String>> a : anagramTable.entrySet()) 
		{
			if(a.getValue().size() > n) 
			{
				n = a.getValue().size();
				max.clear();
				max.add(a);
			}
			else if(a.getValue().size() == n)
			{
				max.add(a);
			}
		}
		return max;
	}
	
	static String maxEntriesTest = "";
	
	public static void main (String [] args)
	{
		Anagrams a = new Anagrams ();
		final long startTime = System . nanoTime ();
		try
		{
			a.processFile("words_alpha.txt");
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		ArrayList <Map.Entry<Long, ArrayList<String>>> maxEntries = a.getMaxEntries ();
		maxEntriesTest = maxEntries.toString();
		final long estimatedTime = System.nanoTime() - startTime ;
		final double seconds = ((double)estimatedTime / 1000000000);
		System.out.println ("Time: " + seconds);
		System.out.println("List of max anagrams: "+ maxEntries);
		}
}