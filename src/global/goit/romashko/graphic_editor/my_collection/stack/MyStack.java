package global.goit.romashko.graphic_editor.my_collection.stack;

import global.goit.romashko.graphic_editor.my_collection.MyCollection;

public interface MyStack<T> extends MyCollection<T> {
    void push(T value);
    void remove(int index);
    T peek();
    T pop();
}
