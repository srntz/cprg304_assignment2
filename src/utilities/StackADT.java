package utilities;

import java.util.EmptyStackException;

public interface StackADT<T> {
    /**
     * Adds a specified item to the stack
     *
     * @param item Item to be added to the stack
     * @return Added item
     */
    public T push(T item);

    /**
     * Removes the topmost item from the stack
     *
     * @return Removed item
     * @throws EmptyStackException if the stack is empty
     */
    public T pop() throws EmptyStackException;

    /**
     * Gets the object from the top of the stack
     *
     * @return Topmost object
     * @throws EmptyStackException if the stack is empty
     */
    public T peek() throws EmptyStackException;

    /**
     * Checks if the stack is empty
     *
     * @return {@code true} if the stack is empty
     */
    public boolean isEmpty();
}
