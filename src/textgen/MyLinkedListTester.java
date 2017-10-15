/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		// Add more tests here Done!!!
		int b = list1.head.next.data;
		assertEquals("Remove: check previous element is correct linked ", 21, b);
		assertEquals("Remove: check next element is correct linked ", null, list1.head.next.prev.data);
		try {
			list1.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		try {
			emptyList.remove(0);
			fail("Remove element from empty list");
		}
		catch (IndexOutOfBoundsException e) {
		}
		try {
			longerList.remove(30);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e){
		}
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // Implement this test
		int initialSize = list1.size();
		boolean gotAdded = list1.add(66);
		int c = list1.tail.prev.prev.data;
		int d = list1.tail.prev.data;
		int nex = list1.tail.prev.prev.next.data; // 66

		assertEquals("AddEnd: check if gotAdded ", true, gotAdded);
		assertEquals("AddEnd: chek size is correct ", initialSize + 1, list1.size());
		assertEquals("AddEnd: check last element is correct ", (Integer)66, list1.get(list1.size()-1));
		assertEquals("AddEnd: check previous element is correct linked ", 42, c);
		assertEquals("AddEnd: check next element is correct linked ", 66, d);
		assertEquals("AddEnd: check previous element is correct linked ", 66, nex);
		assertEquals("AddEnd: check last is correct linked next ", null, list1.tail.prev.next.data);
		
		
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// Implement this test.
		assertEquals("Size: check list is empty ", 0, emptyList.size());		
		emptyList.add(1);
		assertEquals("Size: list1 expects one element ", 1, emptyList.size());
		emptyList.add(3);
		assertEquals("Size: list1 expects two elements ", 2, emptyList.size());
		emptyList.remove(0);
		emptyList.remove(0);
		assertEquals("Size: list1 excpets to be empty ", 0, emptyList.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // Implement this test.
		
		// check initial state of the list
		assertEquals("Add at index: check current element index ", (Integer)2, longerList.get(2));
		assertEquals("Add at index: check initial size ", 10, longerList.size());
		
		// check state after element was added
		longerList.add(2, 23);
		assertEquals("Add at index: check element at index ", (Integer)23, longerList.get(2));
		assertEquals("Add at index: check previous element is correct ", (Integer)1, longerList.get(1));
		assertEquals("Add at index: check next element is correct ", (Integer)2, longerList.get(3));
		assertEquals("Add at index: check size after add element ", 11, longerList.size());
		
		// Test against inexistent index. 
		try {
			longerList.add(-1, 23);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		try {
			longerList.add(1, null);
			fail("Check null pointer");
		}
		catch (NullPointerException e) {
		}
		try {
			emptyList.add(1, 2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
			
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // Implement this test.
		// Initial state of the list
		assertEquals("Set: check list size ", 10, longerList.size());

		// After set
	    int old = longerList.set(2, 66);
	    assertEquals("Set: check element has changed ", (Integer)66, longerList.get(2));
	    assertEquals("Set: check previous element ", 2, old);
	    assertEquals("Set: check list size ", 10, longerList.size());
	    
	    try {
	    	list1.set(-1, 34);
	    	fail("Set negative index");
	    }
	    catch (IndexOutOfBoundsException e){
	    }
	    try {
	    	list1.set(4, 4);
	    	fail("Check out of bounds");
	    }
	    catch (IndexOutOfBoundsException e){
	    }
	    try {
	    	emptyList.set(0, 1);
	    	fail("Check empty list");
	    }
	    catch (IndexOutOfBoundsException e){
	    }
	    try {
	    	list1.set(1, null);
	    	fail("Value set to null");
	    }
	    catch (NullPointerException e){
	    }
	    
	}
	
	
	// TODO: Optionally add more test methods.
	/** Test printing Linked List elements */
	@Test
	public void testToString()
	{
		assertEquals("toString: check elements print correctly ", "[65, 21, 42]", list1.toString());
		assertEquals("toString: check empty list is returned ", "[]", emptyList.toString());
	}
}
