package exceptions;

/**
 * Exception thrown when attempting to access an element from an empty queue.
 */
public class EmptyQueueException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4841203106897644399L;

	public EmptyQueueException(String message) {
        super(message);
    }
}
