package textgen;

import java.util.AbstractList;
import java.util.ArrayList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// Implement this method.
		size = 0;
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// Implement this method.
		if(element == null) {
			throw new NullPointerException();
		}
		
		// getting the last node
		LLNode<E> lastNode = tail.prev;	
		// inserting the newly created node between the tail and last
		LLNode<E> node = new LLNode<E>(element, lastNode, tail);
		// Adjusting pointers from lastNode and tail
		lastNode.next = node;
		tail.prev = node;
		
		size ++;
		return true;
	}
	

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// Implement this method. 
		return getNode(index).data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// Implement this method.
		if(element == null) {
			throw new NullPointerException();
		}
		if (index == 0 && size == 0) {
			add(element);
			return;
		}
		
		LLNode<E> nextNode = getNode(index);
		LLNode<E> prevNode = nextNode.prev;
		LLNode<E> node = new LLNode<>(element, prevNode, nextNode);
		prevNode.next = node;
		nextNode.prev = node;
		size++;
		
	}


	/** Return the size of the list */
	public int size() 
	{
		// Implement this method.
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// Implement this method
			
		LLNode<E> nodeToDelete = getNode(index);
		LLNode<E> prevNode = nodeToDelete.prev;
		LLNode<E> nextNode = nodeToDelete.next;
		prevNode.next = nextNode;
		nextNode.prev = prevNode;
		size--;
		return nodeToDelete.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// Implement this method.
		if(element == null) 
			throw new NullPointerException();
		LLNode<E> nodeToChange = getNode(index);
		E replacedElement = nodeToChange.data;
		nodeToChange.data = element;
		return replacedElement;
	}
	
	/**
	 * get a node at an index position in the list
	 * @param The index of the node to get
	 * @return The node node at the index
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public String toString()
	{
		ArrayList<E> list= new ArrayList<E>();
		for(int i = 0; i < this.size(); i++)
		{
			list.add(this.get(i));
		}
		return list.toString();
	}
	
	/**
	 * get a node at an index position in the list
	 * @param The index of the node to get
	 * @return The node node at the index
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	private LLNode<E> getNode(int index)
	{
		// Check if index is in range
		if(index > size - 1 || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		// assign appropriate nodes
		// Starting at the head
		LLNode<E> prevNode = head;
		LLNode<E> currentNode;
		
		for(int pos = 0; pos < size; pos++){
			currentNode = prevNode.next;
			if(pos == index) {
				return currentNode;
			}
			prevNode = currentNode;
		}
		return prevNode;
	}
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// Add any other methods you think are useful here.
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
	public LLNode(E e, LLNode<E> prevNode, LLNode<E> nextNode)
	{
		this(e);
		this.prev = prevNode;
		this.next = nextNode;
	}

}
