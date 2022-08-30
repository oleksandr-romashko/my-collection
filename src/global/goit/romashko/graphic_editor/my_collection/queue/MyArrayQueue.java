package global.goit.romashko.graphic_editor.my_collection.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class MyArrayQueue<T> implements MyQueue<T> {
    private Object[] data;
    private int size;

    public MyArrayQueue() {
        assignEmptyQueue();
    }

    @Override
    public void clear() {
        assignEmptyQueue();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T value) {
        Object[] newData = new Object[size + 1];
        System.arraycopy(data,0,newData,0,size);
        newData[size] = value;
        data = newData;
        size++;
    }

    @Override
    public void remove(int index) {
        if(index < 0 || index >= size) {
            throw new NoSuchElementException("No such element for index " + index);
        }
        if (size == 1) {
            assignEmptyQueue();
            return;
        }
        Object[] newData = new Object[size - 1];
        System.arraycopy(data,0,newData,0,index);
        System.arraycopy(data,index+1,newData,index,size - index -1);
        data = newData;
        size--;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T peek() {
        if (size == 0) {
            return null;
        }
        return (T) data[0];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T poll() {
        if (size == 0) {
            return null;
        }
        Object first = data[0];
        remove(0);
        return (T) first;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        for (Object datum : data) {
            sj.add(datum.toString());
        }
        return sj.toString();
    }

    private void assignEmptyQueue() {
        data = new Object[0];
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator<>();
    }

    private class MyIterator<E> implements Iterator<E> {
        int indexPosition = 0;

        @Override
        public boolean hasNext() {
            return indexPosition < size;
        }

        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            Object value = data[indexPosition];
            indexPosition++;
            return (E) value;
        }
    }
}
