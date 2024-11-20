package implementations;

import utilities.Iterator;
import utilities.ListADT;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class MyDLL<T> implements ListADT<T> {
    private MyDLLNode<T> head;
    private MyDLLNode<T> tail;
    private int size;

    public MyDLL() {
        head = null;
        tail = null;
        size = 0;
    }

    private MyDLLNode<T> getNodeAtIndex(int index) {
        if(index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }

        MyDLLNode<T> currentNode = head;
        for(int i = 1; i <= index; i++) {
            currentNode = currentNode.next;
        }

        return currentNode;
    }

    private void indexSafeRemove(int index, MyDLLNode<T> node) {
        if(index == 0) {
            head = node.next;
        } else if(index == size() - 1) {
            tail = node.prev;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    private void indexSafeAdd(int index, MyDLLNode<T> item, MyDLLNode<T> targetNode) {
        if(index == 0) {
            item.next = head;
            head.prev = item;
            head = item;
        } else if(index == size()) {
            tail.next = item;
            item.prev = tail;
            tail = item;
        } else {
            item.next = targetNode;
            item.prev = targetNode.prev;
            item.prev.next = item;
            item.next.prev = item;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean add(int index, T toAdd) throws NullPointerException, IndexOutOfBoundsException {
        if(toAdd == null) {
            throw new NullPointerException();
        }

        if(index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }

        if(index == size()) {
            return add(toAdd);
        }

        MyDLLNode<T> newNode = new MyDLLNode<>(toAdd);

        MyDLLNode<T> targetNode = getNodeAtIndex(index);

        indexSafeAdd(index, newNode, targetNode);

        size++;

        return true;
    }

    @Override
    public boolean add(T toAdd) throws NullPointerException {
        if(toAdd == null) {
            throw new NullPointerException();
        }

        MyDLLNode<T> newNode = new MyDLLNode<>(toAdd);

        if(size() == 0) {
            head = newNode;
            tail = newNode;
            size++;
            return true;
        }

        tail.next = newNode;
        newNode.prev = tail;

        tail = newNode;

        size++;

        return true;
    }

    @Override
    public boolean addAll(ListADT<? extends T> toAdd) throws NullPointerException {
        if(toAdd == null) {
            throw new NullPointerException();
        }

        Iterator<? extends T> iterator = toAdd.iterator();
        while(iterator.hasNext()) {
            add(iterator.next());
        }

        return true;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if(index < 0 || index > size() || size() == 0) {
            throw new IndexOutOfBoundsException();
        }

        MyDLLNode<T> targetNode = getNodeAtIndex(index);

        return targetNode.element;
    }

    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        if(index < 0 || index > size() || size() == 0) {
            throw new IndexOutOfBoundsException();
        }

        MyDLLNode<T> nodeAtTargetIndex = getNodeAtIndex(index);
        T temp = nodeAtTargetIndex.element;

        indexSafeRemove(index, nodeAtTargetIndex);

        size--;

        return temp;
    }

    @Override
    public T remove(T toRemove) throws NullPointerException {
        if(toRemove == null) {
            throw new NullPointerException();
        }

        MyDLLNode<T> currentNode = head;
        for(int i = 0; i < size(); i++) {
            if(toRemove.equals(currentNode.element)) {
                T temp = currentNode.element;

                indexSafeRemove(i, currentNode);

                size--;
                currentNode = null;

                return temp;
            }
            currentNode = currentNode.next;
        }

        return null;
    }

    @Override
    public T set(int index, T toChange) throws NullPointerException, IndexOutOfBoundsException {
        if(toChange == null) {
            throw new NullPointerException();
        }
        if(index < 0 || index > size() || size() == 0) {
            throw new IndexOutOfBoundsException();
        }

        MyDLLNode<T> targetNode = getNodeAtIndex(index);

        T temp = targetNode.element;

        targetNode.element = toChange;

        return temp;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(T toFind) throws NullPointerException {
        if(toFind == null) {
            throw new NullPointerException();
        }

        MyDLLNode<T> currentNode = head;
        for(int i = 0; i < size(); i++) {
            if(toFind.equals(currentNode.element)) {
                return true;
            }
            currentNode = currentNode.next;
        }

        return false;
    }

    @Override
    public T[] toArray(T[] toHold) throws NullPointerException {
        if(toHold == null) {
            throw new NullPointerException();
        }

        T[] array = toHold;
        if(toHold.length < size()) {
            array = Arrays.copyOf(toHold, size());
        }

        MyDLLNode<T> currentNode = head;
        for(int i = 0; i < size(); i++) {
            array[i] = currentNode.element;
            currentNode = currentNode.next;
        }

        return array;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size()];

        MyDLLNode<T> currentNode = head;
        for(int i = 0; i < size(); i++) {
            array[i] = currentNode.element;
            currentNode = currentNode.next;
        }

        return array;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyDLLIterator();
    }

    private class MyDLLIterator implements Iterator<T> {
        private MyDLLNode<T> currentIteratorNode;

        public MyDLLIterator() {
            MyDLLNode<T> initialPlaceholderNode = new MyDLLNode<>(null);
            initialPlaceholderNode.next = head;
            currentIteratorNode = initialPlaceholderNode;
        }

        @Override
        public boolean hasNext() {
            return currentIteratorNode.next != null;
        }

        @Override
        public T next() throws NoSuchElementException {
            if(currentIteratorNode.next == null) {
                throw new NoSuchElementException();
            }

            currentIteratorNode = currentIteratorNode.next;
            return currentIteratorNode.element;
        }
    }

    private class MyDLLNode<T> {
        T element;
        MyDLLNode<T> next;
        MyDLLNode<T> prev;

        public MyDLLNode(T element) {
            this.element = element;
            next = null;
            prev = null;
        }
    }
}
