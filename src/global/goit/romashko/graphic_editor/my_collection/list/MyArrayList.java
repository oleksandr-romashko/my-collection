package global.goit.romashko.graphic_editor.my_collection.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.StringJoiner;

public class MyArrayList<T> implements MyList<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] data;
    private int size;

    public MyArrayList() {
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
            throw new IllegalArgumentException("null value is not allowed");
        }
        resizeIfNecessary();
        data[size] = value;
        size++;
    }

    @Override
    public void remove(int index) {
        checkCollectionBounds(index);
        if (size == 1) {
            assignEmptyList();
            return;
        }
        Object[] newData = new Object[size - 1];
        System.arraycopy(data,0,newData,0,index);
        System.arraycopy(data,index+1,newData,index,size - index -1);
        data = newData;
        size--;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        for (int i = 0; i < size; i++) {
            sj.add(data[i].toString());
        }
        return sj.toString();
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkCollectionBounds(index);
        return (T) data[index];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyArrayList<?> that = (MyArrayList<?>) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }

    private void assignEmptyList() {
            data = new Object[DEFAULT_CAPACITY];
            size = 0;
    }
    private void resizeIfNecessary() {
        if(size == data.length) {
            Object[] newData = new Object[data.length * 2];
            System.arraycopy(data, 0, newData, 0, data.length);
            data = newData;
        }
    }

    private void checkCollectionBounds(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Requested index " + index + " is out of bounds for size " + size);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator<>();
    }

    public class MyIterator<E> implements Iterator<E> {
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
