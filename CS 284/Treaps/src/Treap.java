/**
 * Jason Rossi.
 * Spring 2020
 * Section 284-A
 * I pledge my honor that I have abided by the Stevens Honor System.
 */

import java.util.Random;
import java.util.Stack;

public class Treap<E extends Comparable<E>>
{
	private class Node<E>
	{
	    public E data;
	    public int priority;
	    public Node<E> left;
	    public Node<E> right;
	    
	    /**
	     * This creates a new node with the given data and priority. 
	     * @param data
	     * @param priority
	     */
	    
	    public Node(E data, int priority)
	    {
	        if (data == null)
	        {
	            throw new IllegalArgumentException("The data field cannot be null!");
	        }
	        this.data = data;
	        this.priority = priority;
	        left = null;
	        right = null;
	    }
	    
	    /**
	     * Performs a right rotation. 
	     * @return a new treap with the updated data and priority attributes as well as
	     * the left and right pointers of the involved nodes accordingly. 
	     */
	    
	    Node<E> rotateRight()
	    {
	        if (left == null)
	        {
	            return this; 
	        }
	        else
	        {
	            Node<E> new1 = left;
	            left = left.right;
	            new1.right = this;
	            return new1;
	        }
	    }
	    
	    /**
	     * Performs a left rotation. 
	     * @return a new treap with the updated data and priority attributes as well as
	     * the left and right pointers of the involved nodes accordingly. 
	     */
	    
	    Node<E> rotateLeft()
	    {
	        if (right == null)
	        {
	            return this; 
	        }
	        else
	        {
	            Node<E> new1 = right;
	            right = right.left;
	            new1.left = this;
	            return new1;
	        }
	    }
	    
	    /**
	     * Converts the treap in a string.
	     */
	    
	    public String toString()
	    {
	        return "(key=" + data + ", priority=" + priority + ")";
	    }
	}
	
	private Random priorityGenerator;
	private Node<E> root;
	
	/**
	 * Constructs an empty treap and initializes priorityGenerator using new Random().
	 */
	
	public Treap()
	{
		root = null;
		priorityGenerator = new Random();
	}
	
	/**
	 * Constructs an empty treap and initializes priorityGenerator using new Random(seed).
	 * @param seed
	 */
	
	public Treap(long seed)
	{
		root = null;
		priorityGenerator = new Random(seed);
	}
	
	/**
	 * Helper function for the add(E key) method. Reorganizes the treap after the treap
	 * has been added, in order to be a heap. 
	 * @param a
	 * @param b
	 */
	
	public void addHelper(Node<E> a, Stack<Node<E>> b) 
	{
		while (b.isEmpty() == false) 
		{
			Node<E> parent = b.pop();
			if (parent.priority < a.priority)
			{
				if (parent.data.compareTo(a.data) > 0) 
				{
					a = parent.rotateRight();
				}
				else 
				{
					a = parent.rotateLeft();
				}
				if (!b.isEmpty())
				{
					if (b.peek().left == parent) 
					{
						b.peek().left = a;
					}
					else 
					{
						b.peek().right = a;
					}
				}
				else
				{ 
					this.root = a;
				}
			}
			else
			{
				break;
			}
		}
	}
	
	/**
	 * Adds a key to the treap. 
	 * @param key
	 * @param priority
	 * @return false if it is done successfully and true if not
	 */
	
	boolean add(E key, int priority) 
	{
		if (root == null)
		{
			root = new Node<E>(key, priority);
			return true;
		}
		else
		{
			
			Node<E> x = root;
			Node<E> y = new Node<E>(key, priority);
			Stack<Node<E>> tempStack = new Stack<Node<E>>();
			
			while (x != null)
			{
				if (x.data.compareTo(key) == 0)
				{
					return false;
				}
				else
				{
					if (x.data.compareTo(key) < 0)
					{
						tempStack.push(x);
						if (x.right == null)
						{
							x.right = y;
							addHelper(y, tempStack);
							return true;
						}
						else
						{
							x = x.right;
						}
					}
					else
					{
						tempStack.push(x);
						if (x.left == null)
						{
							x.left = y;
							addHelper(y, tempStack);
							return true;
						}
						else
						{
							x = x.left;
						}
					}
				}
			}
			return false;
		}
	}
	
	/**
	 * With a random priorityGenerator, this method adds a key to the treap/
	 * @param key
	 * @return true if it is successful and false if it is not. 
	 */
	
	boolean add(E key) 
	{
		return add(key, priorityGenerator.nextInt());
	}
	
	private Node<E> deleteHelper(E key, Node<E> d)
	{
		if (d == null)
		{
			return d;
		}
		else
		{
			if (d.data.compareTo(key) < 0)
			{
				d.right = deleteHelper(key, d.right);
			}
			else
			{
				if (d.data.compareTo(key) > 0)
				{
					d.left = deleteHelper(key, d.left);
				}
				else
				{
					if (d.right == null)
					{
						d = d.left;
					}
					else if (d.left == null)
					{
						d = d.right;
					}
					else
					{
						if (d.right.priority < d.left.priority)
						{
							d = d.rotateRight();
							d.right = deleteHelper(key, d.right);
						}
						else
						{
							d = d.rotateLeft();
							d.left = deleteHelper(key, d.left);
						}
					}
				}
			}
		}
		return d;
	}

	/**
	 * Uses the deleteHelper method to delete the node pertaining to the key
	 * and to reorganize the treap after the deletion is made.
	 * @param key
	 * @return true if the node is deleted
	 */
	
	public boolean delete(E key)
	{
		if (root == null || find(key) == false)
		{
			return false;
		}
		else
		{
			root = deleteHelper(key, root);
			return true;
		}
	}
	
	/**
	 * This is a helper function for the find(E key) method.
	 * @param root
	 * @param key
	 * @return true if the key is found and false if it is not. 
	 */
	
	private boolean find(Node<E> root, E key)
	{
		if (root == null)
		{	
			return false;
		}
		else
		{
			if (root.data.compareTo(key) == 0)
			{
				return true;
			}
			else
			{
				return find(root.right, key) || find(root.left, key);
			}
		}
	}

	/**
	 * Find the item pertaining to the given key. 
	 * @param key
	 * @return true if the key is found and false if it is not. 
	 */
	
	public boolean find(E key)
	{
		return find(root, key);
	}

	/**
	 * This is a helper function for the toString() method. 
	 * @param a
	 * @param indent
	 * @return the converted version of the treap into a string. 
	 */
	
	private String toStringHelper(Node<E> a, int indent)
	{
		StringBuilder word = new StringBuilder();
		// add indentation
		for (int i=0;i<indent;i++)
		{
			word.append("  ");
		}
		if (a==null)
		{
			word.append("null");
		}
		else
		{
			word.append("(key = " + a.data +", priority = " + a.priority + ")" );
			word.append("\n");
			word.append(toStringHelper(a.left, indent+1));
			word.append("\n");
			word.append(toStringHelper(a.right, indent+1));
		}
		return word.toString();
		
	}
	
	/**
	 * Uses the toStringHelper to turn the treap into a string. 
	 */
	
	public String toString()
	{
		return toStringHelper(root,0);
	}
}