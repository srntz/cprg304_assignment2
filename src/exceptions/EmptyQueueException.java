package exceptions;

/**
 * Exception thrown when attempting to access an element from an empty queue.
 */
public class EmptyQueueException extends Exception {
    // Default constructor
    public EmptyQueueException() {
        super("The queue is empty.");
    }

    // Constructor that allows for a custom message
    public EmptyQueueException(String message) {
        super(message);
    }
}
