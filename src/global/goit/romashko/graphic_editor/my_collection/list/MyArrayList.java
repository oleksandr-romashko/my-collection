package global.goit.romashko.graphic_editor.my_collection.list;

import java.util.StringJoiner;

public class MyArrayList<T> implements MyList {

    private static final int DEFAULT_CAPACITY = 10;
    Object[] data;
    int size;

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
    public void add(Object value) {
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
    public Object get(int index) {
        checkCollectionBounds(index);
        return (T) data[index];
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
}
