package global.goit.romashko.my_collection.stack;

import global.goit.romashko.my_collection.MyCollection;

public interface MyStack<T> extends MyCollection<T>, Iterable<T> {
    void push(T value);
    void remove(int index);
    T peek();
    T pop();
}
