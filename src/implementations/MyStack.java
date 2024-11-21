package implementations;

import utilities.StackADT;
import utilities.Iterator;

import java.lang.reflect.Array;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class MyStack<E> implements StackADT<E> {
    private MyArrayList<E> stack;

    public MyStack() {
        stack = new MyArrayList<>();
    }

    @Override
    public void push(E toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException("Cannot add null to the stack");
        }
        stack.add(toAdd);
    }

    @Override
    public E pop() throws EmptyStackException {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.remove(stack.size() - 1);
    }

    @Override
    public E peek() throws EmptyStackException {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.get(stack.size() - 1);
    }

    @Override
    public void clear() {
        stack.clear();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public Object[] toArray() {
        Object[] listOrderArray =  stack.toArray();
        return getStackOrderArray(listOrderArray);
    }

    @Override
    public E[] toArray(E[] holder) throws NullPointerException {
        E[] listOrderArray = stack.toArray(holder);
        return getStackOrderArray(listOrderArray);
    }

    private <T> T[] getStackOrderArray(T[] array) {
        @SuppressWarnings("unchecked")
        T[] stackOrderArray = (T[]) Array.newInstance(array.getClass().getComponentType(), stack.size());

        int currentInsertionPosition = 0;
        for(int i = array.length - 1; i >= 0; i--) {
            stackOrderArray[currentInsertionPosition] = array[i];
            currentInsertionPosition++;
        }

        return stackOrderArray;
    }

    @Override
    public boolean contains(E toFind) throws NullPointerException {
        return stack.contains(toFind);
    }

    @Override
    public int search(E toFind) {
        int position = 1;
        for (int i = stack.size() - 1; i >= 0; i--, position++) {
            if (stack.get(i).equals(toFind)) {
                return position;
            }
        }
        return -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new StackIterator();
    }

    @Override
    public boolean equals(StackADT<E> that) {
        if (this == that) return true;
        if (that == null || this.size() != that.size()) return false;

        Iterator<E> thisIterator = this.iterator();
        Iterator<E> thatIterator = that.iterator();

        while (thisIterator.hasNext()) {
            if (!thisIterator.next().equals(thatIterator.next())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public boolean stackOverflow() {
        return false;
    }

    private class StackIterator implements Iterator<E> {
        private int currentIndex;

        public StackIterator() {
            this.currentIndex = stack.size();
        }

        @Override
        public boolean hasNext() {
            return currentIndex > 0;
        }

        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the stack");
            }
            return stack.get(--currentIndex);
        }
    }
}
