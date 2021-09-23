/** @author Jason Rossi
* Date: March 8, 2020
* I pledge my honor that I have abided by the Stevens Honor System.
*/ 

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class IDLList<E> 
{
	
	
	private static class Node<E> 
	{
		E data;
		Node<E> prev;
		Node<E> next;
		
		/**
		 * Constructor that sets content of a Node to the parameter and sets 
		 * the previous and next pointers of a Node to null.
		 * @param item
		 */
		Node(E elem) 
		{
			this.data = elem;
			this.prev = null;
			this.next = null;
		}
		
		/**
		 * Constructor that sets the content of a Node, as well as the
		 * previous and next pointers of a Node.
		 * @param item
		 * @param prev
		 * @param next
		 */
		Node(E elem, Node<E> prev, Node<E> next) 
		{
			this.data = elem;
			this.prev = prev;
			this.next = next;
		}
		
		public String toString() 
		{
			if (this.data == null) 
			{
				return (String) null;
			}
			else {
				return this.data.toString();
			}
		}
	}
		
	
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;
	
	/**
	 * Constructor that creates an empty double-linked list.
	 */
	
	public IDLList() 
	{
		head = null;
		tail = null;
		indices = new ArrayList<Node<E>>();
		size = 0;
	}
	
	/**
	 * Adds an element at the position index.
	 * @param index Position in which where the element will be added
	 * @param elem The element to be added to the list
	 * @return True
	 */
	
	public boolean add(int index, E elem) {
		if (index < 0 || index > size) 
		{
			throw new IndexOutOfBoundsException();
		}
		
		else if (index == size) 
		{
			append(elem);
		}
		
		else if (index == 0) 
		{
			add(elem);
		}
		
		else 
		{
			Node<E> prev = indices.get(index-1);
			Node<E> temp = new Node<E>(elem, prev, prev.next);
			prev.next.prev = temp;
			prev.next = temp;
			indices.add(index, temp);
			size++;
		}
		
		return true;
	}
	
	/**
	 * Adds an element to the beginning of a list.
	 * @param elem The element to be added to the list
	 * @return True
	 */
	
	public boolean add(E elem) 
	{
		if (head == null) 
		{
			head = new Node<E>(elem);
			tail = head;
			indices.add(0, head);
			size++;
		}
		else 
		{
			Node<E> originalHead = head;
			head = new Node<E>(elem, null, head);
			originalHead.prev = head;
			indices.add(0, head);
			size++;
		}
		
		return true;
	}
	
	/**
	 * Adds an element to the end of a list.
	 * @param elem The element that is added to the list
	 * @return True
	 */
	
	public boolean append(E elem) 
	{
		if (tail == null) 
		{
			add(elem);
		}
		else 
		{
			Node<E> orginalTail = tail;
			tail = new Node<E>(elem, tail, null);
			originalTail.next = tail;
			indices.add(tail);
			size++;
		}
		
		return true;
	}
	
	/**
	 * Returns the object at position index from the head.
	 * @param index The position of an element in the list
	 * @return the head element of the list.
	 */
	
	public E get(int index) 
	{
		return indices.get(index).data;
	}
	
	/**
	 * Returns the object at the head of a list.
	 * @return the object at the head of a list.
	 */
	
	public E getHead() 
	{
		return head.data;
	}
	
	/**
	 * Returns the object at the tail of a list.
	 * @return the object at the tail of a list.
	 */
	public E getLast() 
	{
		return tail.data;
	}
	
	/**
	 * Returns the list size
	 * @return the list size
	 */
	
	public int size() 
	{
		return size;
	}
	
	/**
	 * Removes and returns the element at the head of the list.
	 * @return the element that was removed
	 */
	
	public E remove() 
	{
		if (head == null) 
		{
			throw new IllegalStateException();
		}
		if(head == tail) 
		{
			Node<E> temporary = head;
			head = null;
			tail = null;
			indices.clear();
			size--;
			return temporary.data;
		}
		Node<E> temporary = head;
		head = head.next;
		indices.remove(0);
		size--;
		return temporary.data;
	}
	
	/**
	 * Removes and returns the last element at the tail of a list.
	 * @return The element that was removed.
	 */
	
	public E removeLast() 
	{
		if (tail == null) 
		{
			throw new IllegalStateException();
		}
		else if (head == tail) 
		{
			Node<E> temp1 = tail;
			head = null;
			tail = null;
			indices.clear();
			size--;
			return temp1.data;
		}
		else 
		{
			Node<E> temp1 = tail;
			tail = tail.prev;
			tail.next = null;
			indices.remove(size - 1);
			size--;
			return temp1.data;
		}
	}
	
	/**
	 * Removes and returns the element at the index.
	 * @param index The position of an element to be removed.
	 * @return the element that was removed.
	 */	
	
	public E removeAt(int index) 
	{
		if (head == null || tail == null) 
		{
			throw new IllegalStateException();
		}
		
		if (index < 0 || index >= size) 
		{
			throw new IllegalStateException();
		}
		
		else if (index == size - 1) 
		{
			E element = removeLast();
			return element;
		}
		
		else if (index == 0) 
		{
			E element = remove();
			return element;
		}
		else 
		{
			Node<E> temp1 = indices.get(index);
			Node<E> prev = temp1.prev;
			Node<E> next = temp1.next;
			prev.next = next;
			next.prev = prev;			
			indices.remove(index);
			size--;
			return temp1.data;
		}
	}
	
	/**
	 * Removes the first occurrence of elem in the list and returns true.
	 * @param element The element to be removed
	 * @return True
	 */
	
	public boolean remove(E elem) 
	{
		if (head == null) 
		{
			throw new IllegalStateException();
		}
		int index = 0;
		boolean inL = false;
		while(index < size && !inL) 
		{
			if (indices.get(index).data.equals(elem)) 
			{
				removeAt(index);
				inL = true;
			}
			else 
			{
				index++;
			}
		}
		return inL;
	}
	
	/**
	 * Returns a String representation of the List.
	 * @return String representation the List
	 */
	
	public String toString() 
	{
		String s1 = "[";
		if (head == null) 
		{
			return "[]";
		}
		for (int i=0; i<size - 1; i++) 
		{
			s1 += indices.get(i).toString() + ",";
		}
		return s1 + indices.get(size - 1).toString() + "]";
	}
	
	public static void main(String[] args) 
	{
		IDLList<Integer> list1 = new IDLList<Integer>();
		list1.add(1);
		list1.add(4);
		list1.add(3);
		list1.add(2);
		System.out.println(list1);
		list1.remove();
		System.out.println(list1);
	}
	
}

