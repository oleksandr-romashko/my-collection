package global.goit.romashko.my_collection.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class MyLinkedList<T> implements MyList<T> {

    private MyNode<T> header;
    private int size;

    public MyLinkedList() {
        assignEmptyList();
    }

    @Override
    public void clear() {
        assignEmptyList();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T value) {
        if (value == null) {
            throw new IllegalArgumentException("null Node value is not allowed");
        }
        if (size == 0) {
            header = new MyNode<>(value);
        } else if (size == 1) {
            MyNode<T> newTail = new MyNode<>(value, header, header);
            header.setNext(newTail);
            header.setPrev(newTail);
        } else {
            MyNode<T> oldTail = header.getPrev();
            MyNode<T> newTail = new MyNode<>(value, header, oldTail);
            oldTail.setNext(newTail);
            header.setPrev(newTail);
        }
        size++;
    }

    @Override
    public void remove(int index) {
        checkForIndexBounds(index);
        if (size == 1) {
            assignEmptyList();
            return;
        }

        MyNode<T> current = header;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        MyNode<T> prev = current.getPrev();
        MyNode<T> next = current.getNext();
        prev.setNext(next);
        next.setPrev(prev);

        current.setNext(null);
        current.setPrev(null);

        if (index == 0) {
            header = next;
        }
        size--;
    }

    @Override
    public T get(int index) {
        MyNode<T> current = header;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getElement();
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator<>(header);
    }

    private class MyIterator<E> implements Iterator<E> {
        private MyNode<E> current;
        private boolean isLoopTriggered = false;

        public MyIterator(MyNode<E> header) {
            current = header;
        }

        @Override
        public boolean hasNext() {
            if (current == null) {
                throw  new NoSuchElementException("List is empty");
            }
            if (current == header) {
                if (!isLoopTriggered) {
                    isLoopTriggered = true;
                } else {
                    return false;
                }
            }
            return true;
        }

        @Override
        public E next() {
            E element = current.getElement();
            current = current.getNext();
            return element;
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringJoiner sj = new StringJoiner(", ", "[", "]");

        MyNode<T> currentNode = header;
        sj.add(currentNode.getElement().toString());
        while (currentNode.getNext() != header) {
            currentNode = currentNode.getNext();
            sj.add(currentNode.getElement().toString());
        }
        return sj.toString();
    }

    private void assignEmptyList() {
        header = null;
        size = 0;
    }

    private void checkForIndexBounds(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Requested index " + index + " is out of bounds for size " + size);
        }
    }
}
