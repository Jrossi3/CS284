/**
 * Jason Rossi.
 * Spring 2020
 * Section 284-A
 * I pledge my honor that I have abided by the Stevens Honor System.
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TreapTester 
{
	@Test
	void test() 
	{
		Treap<Integer> treap = new Treap<Integer>();
		assertTrue(treap.add(4,19)); 
		assertTrue(treap.add(2,31));
		assertTrue(treap.add(6,70)); 
		assertTrue(treap.add(1,84));
		assertTrue(treap.add(3,12)); 
		assertTrue(treap.add(5,83));
		assertTrue(treap.add(7,26));
		
		assertEquals(treap.toString(), "(key = 1, priority = 84)\n" + 
				"  null\n" + 
				"  (key = 5, priority = 83)\n" + 
				"    (key = 2, priority = 31)\n" + 
				"      null\n" + 
				"      (key = 4, priority = 19)\n" + 
				"        (key = 3, priority = 12)\n" + 
				"          null\n" + 
				"          null\n" + 
				"        null\n" + 
				"    (key = 6, priority = 70)\n" + 
				"      null\n" + 
				"      (key = 7, priority = 26)\n" + 
				"        null\n" + 
				"        null");
		
		assertFalse(treap.add(4,50));
		
		assertTrue(treap.find(2));
		assertFalse(treap.find(13));
		
		assertTrue(treap.delete(2));
		assertFalse(treap.delete(13));
	}
}