package utilities;

public interface QueueADT<E> {
	
	/**
     * Inserts the element into the queue.
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
     * @return The first element of this queue.
     * @throws EmptyQueueException If this queue is empty.
     */
    public E dequeue() throws EmptyQueueException;

    /**
     * Get the first element of the queue without removing it, or returns <code>null</code> if this queue is empty.
     * 
     * @return The first element of this queue, or <code>null</code> if this queue is empty.
     */
    public E peek();

	/**
	 * Removes all of the elements from the queue.
	 */
	public void dequeueAll();
	
    /**
	 * The size method will return the current element count contained in the queue.
	 * 
	 * @return The current element count in this queue.
	 */
	public int size();
	
	/**
	 * Returns true if this queue contains no elements.
	 * 
	 * @return true if this queue contains no elements.
	 */
	public boolean isEmpty();
	
	/**
	 * Returns true if this queue is full.
	 * @return true if this queue is full.
	 */
	public boolean isFull();

	/**
	 * Returns true if this queue contains the specified element. 
	 * More formally, returns true if and only if this queue contains 
	 * at least one element <code>e</code> such that <code>toFind.equals(e)</code>.
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
	 * @param toFInd The element to search for.
	 * @return 
	 */
	public int search(E toFInd);
	
	/**
	 * Compares the specified queue with this queue.
	 * @param otherQueue The queue to be compared with this queue.
	 * @return true if the specified queue is equal to this queue.
	 */
	public boolean equals(QueueADT<E> otherQueue);
	
	/**
	 * Returns an array containing all of the elements in this queue in proper
	 * sequence; the runtime type of the returned array is that of the specified
	 * array.
	 * @param toHold The array into which the elements of this queue are to be
	 *               stored, if it is big enough; otherwise, a new array of the same
	 *               runtime type is allocated for this purpose.
	 * @return An array containing the elements of this queue.
	 * @throws NullPointerException If the specified array is <code>null</code>.
	 */
	public E[] toArray( E[] toHold ) throws NullPointerException;

	/**
	 * Returns an array containing all of the elements in this queue in proper sequence.
	 * @return An array containing all of the elements in this queue in proper sequence.
	 */
	public Object[] toArray();

	/**
	 * Returns an iterator over the elements in this queue, in proper sequence.
	 * 
	 * @return An iterator over the elements in this list, in proper sequence.
	 */
	public Iterator<E> iterator();

}
