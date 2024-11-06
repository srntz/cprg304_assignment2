package utilities;

import java.util.NoSuchElementException;

public interface QueueADT<E> {
	
	/**
     * Inserts the element into the queue.
     * 
     * Precondition: the element to be added is not null.
     * Postcondition: the element added to the end of the queue and
     * the size is increased by one.
     * 
     * @param toAdd The element to be added.
     * @return true if the element is added.
     * @throws NullPointerException If the specified element is <code>null</code>
     *                              and the queue implementation does not support
     *                              having <code>null</code> elements.
     */
    public boolean enqueue(E toAdd) throws NullPointerException;
    
    /**
     * Removes the first element of this queue.
     * 
     * Precondition: the queue is not empty.
     * Postcondition: the first element is removed and
     * the size is decreased by one.
     * 
     * @return The first element of this queue.
     * @throws NoSuchElementException If this queue is empty.
     */
    public E dequeue() throws NoSuchElementException;

    /**
     * Get the first element of the queue without removing it, or returns <code>null</code> if this queue is empty.
     * 
     * Precondition: None.
     * Postcondition: returns the first element without removing it and
     * returns null if queue is empty.
     * 
     * @return The first element of this queue, or <code>null</code> if this queue is empty.
     */
    public E peek();

	/**
	 * Removes all elements from the queue.
	 * 
	 * Precondition: None.
     * Postcondition: the queue is empty and the size is zero.
	 */
	public void dequeueAll();
	
    /**
	 * The size method will return the current element count contained in the queue.
	 * 
	 * Precondition: None.
     * Postcondition: count of elements is return.
     * 
	 * @return The current element count in this queue.
	 */
	public int size();
	
	/**
	 * Returns true if this queue contains no elements.
	 * 
	 * Precondition: None.
     * Postcondition: True if the queue is empty.
     * 
	 * @return true if this queue is empty.
	 */
	public boolean isEmpty();
	
	/**
	 * Returns true if this queue is full.
	 * 
	 * Precondition: None.
     * Postcondition: True if queue is full, false otherwise
     * 
	 * @return true if this queue is full.
	 */
	public boolean isFull();

	/**
	 * Returns true if this queue contains the specified element. 
	 * More formally, returns true if and only if this queue contains 
	 * at least one element <code>e</code> such that <code>toFind.equals(e)</code>.
	 * 
	 * Precondition: The element to be find is not null.
     * Postcondition: True if the queue contains specified element, 
     * false if it's not found
     * 
	 * @param toFind The element whose presence in this queue is to be tested.
	 * @return <code>true</code> if this queue contains the specified element.
	 * @throws NullPointerException If the specified element is <code>null</code>
	 *                              and the queue implementation does not support
	 *                              having <code>null</code> elements.
	 */
	public boolean contains( E toFind ) throws NullPointerException;
	
	/**
	 * Searches for the specified element in this queue.
	 * 
	 * Precondition: The element to be searched for is not null.
     * Postcondition: 
     * 
	 * @param toFInd The element to search for.
	 * @return 
	 */
	public int search(E toFInd);
	
	/**
	 * Compares the specified queue with this queue.
	 *
	 * Precondition: The otherQueue is not null.
     * Postcondition: True if specified queue is equal to this queue,
     * false otherwise.
     * 
	 * @param otherQueue The queue to be compared with this queue.
	 * @return true if the specified queue is equal to this queue.
	 */
	public boolean equals(QueueADT<E> otherQueue);
	
	/**
	 * Returns an array containing all of the elements in this queue in proper
	 * sequence; the runtime type of the returned array is that of the specified
	 * array.
	 * 
	 * Precondition: The specified array is not null.
     * Postcondition: Returns an array containing all of the elements in this queue.
     * 
	 * @param toHold The array into which the elements of this queue are to be
	 *               stored, if it is big enough; otherwise, a new array of the same
	 *               runtime type is allocated for this purpose.
	 * @return An array containing the elements of this queue.
	 * @throws NullPointerException If the specified array is <code>null</code>.
	 */
	public E[] toArray( E[] toHold ) throws NullPointerException;

	/**
	 * Returns an array containing all of the elements in this queue in proper sequence.
	 *
	 * Precondition: None.
     * Postcondition: Returns an array containing all of the elements in this queue.
     * 
	 * @return An array containing all of the elements in this queue in proper sequence.
	 */
	public Object[] toArray();

	/**
	 * Returns an iterator over the elements in this queue, in proper sequence.
	 * 
	 * Precondition: None.
     * Postcondition: Returns an iterator for the queue.
	 * 
	 * @return An iterator over the elements in this list, in proper sequence.
	 */
	public Iterator<E> iterator();

}
