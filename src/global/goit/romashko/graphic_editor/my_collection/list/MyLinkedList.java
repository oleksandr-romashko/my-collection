package global.goit.romashko.graphic_editor.my_collection.list;

import global.goit.romashko.graphic_editor.my_collection.Node;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class MyLinkedList<T> implements MyList<T> {

    private Node<T> header;
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
            header = new Node<>(value);
        } else if (size == 1) {
            Node<T> newTail = new Node<>(value, header, header);
            header.setNext(newTail);
            header.setPrev(newTail);
        } else {
            Node<T> oldTail = header.getPrev();
            Node<T> newTail = new Node<>(value, header, oldTail);
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

        Node<T> current = (Node<T>) get(index);
        Node<T> prev = current.getPrev();
        Node<T> next = current.getNext();
        prev.setNext(next);
        next.setPrev(prev);

        if (index == 0) {
            header = next;
        }
        size--;
    }

    @Override
    public T get(int index) {
        Node<T> current = header;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return (T) current;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator<>(header);
    }

    public class MyIterator<E> implements Iterator<E> {
        private Node<E> current;
        private boolean isLoopTriggered = false;

        public MyIterator(Node<E> header) {
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

        Node<T> currentNode = header;
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
