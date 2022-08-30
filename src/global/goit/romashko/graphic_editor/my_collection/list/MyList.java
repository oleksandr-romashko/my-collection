package global.goit.romashko.graphic_editor.my_collection.list;

import global.goit.romashko.graphic_editor.my_collection.MyCollection;

public interface MyList<T> extends MyCollection<T> {
    void add(T value);
    void remove(int index);
    T get(int index);
}
