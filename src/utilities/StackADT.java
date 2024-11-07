package utilities;

import java.util.EmptyStackException;

public interface StackADT<T> {
    /**
     * Adds a specified item to the stack
     * <p>
     *     Precondition: None
     * <p>
     *     Postcondition: The element is added to the stack.
     *
     * @param item Item to be added to the stack
     * @return Added item
     */
    public T push(T item);

    /**
     * Removes the topmost item from the stack.
     * <p>
     *     Precondition: None
     * <p>
     *     Postcondition: The topmost element is removed from the stack.
     *
     * @return Removed item
     * @throws EmptyStackException if the stack is empty
     */
    public T pop() throws EmptyStackException;

    /**
     * Gets the object from the top of the stack
     * <p>
     *     Precondition: None
     * <p>
     *     Postcondition: The topmost object is returned.
     *
     * @return Topmost object
     * @throws EmptyStackException if the stack is empty
     */
    public T peek() throws EmptyStackException;

    /**
     * Removes all elements from the stack
     * <p>
     *     Precondition: None
     * <p>
     *     Postcondition: All elements are deleted and the stack is empty
     */
    public void clear();

    /**
     * Returns the number of elements in the stack
     * <p>
     *     Precondition: None
     * <p>
     *     Postcondition: Size is returned
     *
     * @return Number of elements
     */
    public int size();

    /**
     * Checks if the specified item is present in the stack.
     * <p>
     *     Precondition: The specified item is not null.
     * <p>
     *     Postcondition: True or false is returned based on
     *     whether the item exists in the stack or not
     *
     * @param item Item to be checked for.
     * @return {@code true} if the item exists in the stack.
     * @throws NullPointerException if the item is null.
     */
    public boolean contains(T item) throws NullPointerException;

    /**
     * Searches for the specified item and returns its position.
     * <p>
     *     Precondition: None
     * <p>
     *     Postcondition: Item's position or -1 is returned.
     *
     * @param item Item to be searched for.
     * @return Item's position or -1 if the item does not exist in the stack.
     * @throws NullPointerException If the specified item is {@code null}.
     */
    public int search(T item) throws NullPointerException;

    /**
     * Checks if two stack objects have the exact same elements in the same order.
     * <p>
     *     Precondition: Passed stack is not null.
     * <p>
     *     Postcondition: True or false is returned based on the equality of the two stacks.
     *
     * @param stack Object to compare this stack to
     * @return {@code true} if the stacks are equal.
     * @throws NullPointerException if the specified stack is {@code null}
     */
    public boolean equals(StackADT<T> stack) throws NullPointerException;

    /**
     * Checks if the stack is empty
     *
     * @return {@code true} if the stack is empty
     */
    public boolean isEmpty();

    /**
     * Returns a new array containing all elements from the stack.
     * Array[0] represents the top element of the stack.
     * <p>
     *     Precondition: None
     * <p>
     *     Postcondition: An array with elements from the stack is created and returned
     *
     * @return Array containing stack elements
     */
    public T[] toArray();

    /**
     * Appends all elements of the stack to the specified array
     * starting from the top of the stack.
     *
     * <p>
     *     Precondition: The specified array is not null
     * <p>
     *     Postcondition: Items are appended and the array is returned
     *
     * @param toStore Array which items would get appended to.
     * @return  Array with items from the stack.
     * @throws NullPointerException If the specified array is {@code null}
     */
    public T[] toArray(T[] toStore) throws NullPointerException;

    /**
     * Returns an iterator over elements of type {@code T}
     * <p>
     * Precondition: None
     * <p>
     * Postcondition: An iterator is returned
     *
     * @return iterator
     */
    public Iterator<T> iterator();
}
