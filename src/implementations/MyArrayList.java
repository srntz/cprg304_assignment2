package implementations;

import utilities.Iterator;
import utilities.ListADT;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class MyArrayList<T> implements ListADT<T> {
    private static final int INITIAL_ARRAY_CAPACITY = 10;
    private T[] array;
    private int size;

    public MyArrayList() {
        array = (T[]) new Object[INITIAL_ARRAY_CAPACITY];
        size = 0;
    }

    private void verifySufficientCapacity() {
        if (size == array.length) {
            T[] newArray = (T[]) new Object[array.length * 2];
            int index = 0;
            for (T item : array) {
                newArray[index++] = item;
            }
            array = newArray;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        array = (T[]) new Object[INITIAL_ARRAY_CAPACITY];
        size = 0;
    }

    @Override
    public boolean add(
            int index,
            T toAdd) throws NullPointerException, IndexOutOfBoundsException {
        if(toAdd == null) {
            throw new NullPointerException();
        }

        if(index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }

        verifySufficientCapacity();

        for(int i = size(); i > index; i--) {
            array[i] = array[i-1];
        }

        array[index] = toAdd;
        size++;

        return true;
    }

    @Override
    public boolean add(T toAdd) throws NullPointerException {
        if(toAdd == null) {
            throw new NullPointerException();
        }

        verifySufficientCapacity();

        array[size] = toAdd;
        size++;

        return true;
    }

    @Override
    public boolean addAll(ListADT<? extends T> toAdd) throws NullPointerException {
        Iterator<? extends T> iterator = toAdd.iterator();
        while(iterator.hasNext()) {
            T item = iterator.next();
            verifySufficientCapacity();
            array[size] = item;
            size++;
        }
        return true;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if(index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        return array[index];
    }

    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        if(index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        T temp = array[index];

        for(int i = index+1; i < size(); i++) {
            array[i-1] = array[i];
        }

        size--;

        return temp;
    }

    @Override
    public T remove(T toRemove) throws NullPointerException {
        if(toRemove == null) {
            throw new NullPointerException();
        }

        Integer index = null;

        for(int i = 0; i < size(); i++) {
            if(array[i].equals(toRemove)) {
                index = i;
            }
        }

        if(index == null) {
            return null;
        } else {
            T temp = array[index];

            for(int i = index+1; i < size(); i++) {
                array[i-1] = array[i];
            }

            size--;

            return temp;
        }
    }

    @Override
    public T set(
            int index,
            T toChange) throws NullPointerException, IndexOutOfBoundsException {
        if(toChange == null) {
            throw new NullPointerException();
        }

        if(index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        T temp = array[index];

        array[index] = toChange;

        return temp;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object toFind) throws NullPointerException {
        if(toFind == null) {
            throw new NullPointerException();
        }

        for(int i = 0; i < size(); i++) {
            if(array[i].equals(toFind)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public T[] toArray(T[] toHold) throws NullPointerException {
        if(toHold == null) {
            throw new NullPointerException();
        }

        if(toHold.length < size()) {
            @SuppressWarnings("unchecked")
           T[] newArray = (T[]) Array.newInstance(toHold.getClass().getComponentType(), size());

            for(int i = 0; i < size(); i++) {
                newArray[i] = array[i];
            }

            return newArray;
        } else {
            for(int i = 0; i < size(); i++) {
                toHold[i] = array[i];
            }

            return toHold;
        }

    }

    @Override
    public Object[] toArray() {
        Object[] newArray = new Object[size()];

        for(int i = 0; i < size(); i++) {
            newArray[i] = array[i];
        }

        return newArray;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyArrayListIterator<>(array);
    }

    private class MyArrayListIterator<T> implements Iterator<T> {
        private T[] iteratorArray;
        private int currentIteratorIndex;

        private MyArrayListIterator(T[] array) {
            iteratorArray = (T[]) new Object[array.length];
            for(int i = 0; i < array.length; i++) {
                iteratorArray[i] = array[i];
            }

            currentIteratorIndex = -1;
        }

        @Override
        public boolean hasNext() {
            if(currentIteratorIndex + 1 < size()) {
                return true;
            }
            return false;
        }

        @Override
        public T next() throws NoSuchElementException {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            currentIteratorIndex++;
            return iteratorArray[currentIteratorIndex];
        }
    }
}
