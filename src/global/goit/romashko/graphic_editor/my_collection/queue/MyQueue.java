package global.goit.romashko.graphic_editor.my_collection.queue;

import global.goit.romashko.graphic_editor.my_collection.MyCollection;

public interface MyQueue<T> extends MyCollection<T> {
    void add(T value);
    void remove(int index);
    T peek();
    T poll();
}
