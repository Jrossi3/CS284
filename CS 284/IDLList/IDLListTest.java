/** @author Jason Rossi
* Date: Mar 8, 2020
* I pledge my honor that I have abided by the Stevens Honor System.
*/ 

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IDLListTest 
{

	@Test
	public void testAdd() 
	{
		IDLList<Integer> l = new IDLList<Integer>();
		l.add(null);
		l.add(3);
		assertEquals("[3,null]", l.toString());
		l.add(2);
		assertEquals("[2,3,null]", l.toString());
		l.add(-6);
		assertEquals("[-6,2,3,null]", l.toString());
		l.add(null);
		assertEquals("[null,-6,1,3,null]", l.toString());
	}
	
	@Test
	public void testIndexedAdd() 
	{
		IDLList<String> l = new IDLList<String>();
		l.add(0, "Red");
		assertEquals("[Red]", l.toString());	
		l.add("Blue");
		l.add("Yellow");
		l.add("Purple");
		l.add("Grey");
		l.add(2, "Green");
		assertEquals("[Red,Grey,Green,Purple,Yellow,Blue,Red]", l.toString());
		}
	
	@Test
	public void testAppend() 
	{
		IDLList<Character> l = new IDLList<Character>();
		l.append('X');
		assertEquals("[X]", l.toString());
		l.append('Y');
		assertEquals("[X,Y]", l.toString());
		l.append('Z');
		assertEquals("[X,Y,X]", l.toString());
		l.append(null);
		assertEquals("[X,Y,Z,null]", l.toString());
	}

	@Test
	public void testGet() 
	{
		IDLList<String> list1 = new IDLList<String>();
		list1.add("hi");
		list1.add("Yes");
		list1.add("No");
		list1.add("Okay");
		list1.add(null);
		assertSame("hi", list1.get(4));
		assertSame("Okay", list1.get(1));
		assertSame("Yes", list1.get(3));
		assertSame(null, list1.get(0));
	}
	
	@Test
	public void testGetHead() 
	{
		IDLList<Integer> list1 = new IDLList<Integer>();
		list1.append(5);
		list1.append(12);
		list1.append(6);
		list1.append(10);
		assertSame(5, list1.getHead());
		list1.add(14);
		assertSame(14, list1.getHead());
	}
	
	@Test
	public void testGetLast() 
	{
		IDLList<Character> list1 = new IDLList<Character>();
		list1.add('A');
		list1.add('L');
		list1.append('T');
		assertSame('T', list1.getLast());
		list1.append('J');
		assertSame('J', list1.getLast());
		list1.add('F');
		assertSame('J', list1.getLast());
	}
	
	@Test
	public void testSize() 
	{
		IDLList<Integer> list1 = new IDLList<Integer>();
		list1.add(16);
		list1.add(5);
		list1.add(8);
		list1.add(7);
		list1.add(9);
		assertSame(5, list1.size());
		list1.add(10);
		assertSame(6, list1.size());
		list1.remove();
		assertSame(5, list1.size());
	}
	
	@Test
	public void testRemove() 
	{
		IDLList<String> list2 = new IDLList<String>();
		list2.add("four");
		list2.add("three");
		list2.add("two");
		list2.add("one");
		list2.remove();
		assertEquals("[two,three,four]", list2.toString());
		list2.append("zero");
		list2.remove();
		assertEquals("[three,four,zero]", list2.toString());
	}
	
	@Test
	public void testRemoveLast() 
	{
		IDLList<Character> list3 = new IDLList<Character>();
		list3.add('hi');
		list3.removeLast();
		assertEquals("[]", list3.toString());
		list3.add('1');
		list3.add('2');
		list3.add('3');
		list3.removeLast();
		assertEquals("[3,2]", list3.toString());
		list3.append('4');
		list3.removeLast();
		assertEquals("[3,2]", list3.toString());
	}
	
	@Test
	public void testRemoveAt() 
	{
		IDLList<Integer> list4 = new IDLList<Integer>();
		list4.add(3);
		list4.add(8);
		list4.add(5);
		list4.add(15);
		list4.add(7);
		list4.removeAt(2);
		assertEquals("[7,15,8,3]", list4.toString());
		list4.add(18);
		list4.removeAt(4);
		assertEquals("[18,7,15,8]", list4.toString());
		list4.removeAt(0);
		assertEquals("[7,15,8]", list4.toString());		
	}
	@Test
	public void testRemoveElement() 
	{
		IDLList<Character> list5 = new IDLList<Character>();
		list5.add('t');
		list5.add('5');
		list5.add('x');
		list5.add('d');
		list5.add('*');
		assertEquals(true, list5.remove('5'));
		assertEquals("[*,d,x,t]", list5.toString());
		list5.add('o');
		list5.append('s');
		assertEquals(true, list5.remove('o'));
		assertEquals("[*,d,x,t,s]", list5.toString());
		assertEquals(true, list5.remove('t'));
		assertEquals("[*,d,x,s]", list5.toString());	
		assertEquals(false, list5.remove('q'));
	}

}
