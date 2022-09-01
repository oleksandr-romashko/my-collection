package global.goit.romashko.my_collection.queue;

import global.goit.romashko.my_collection.MyCollection;

public interface MyQueue<T> extends MyCollection<T>, Iterable<T> {
    void add(T value);
    void remove(int index);
    T peek();
    T poll();
}
