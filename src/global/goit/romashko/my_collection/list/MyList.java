package global.goit.romashko.my_collection.list;

import global.goit.romashko.my_collection.MyCollection;

public interface MyList<T> extends MyCollection<T>, Iterable<T> {
    void add(T value);
    void remove(int index);
    T get(int index);
}
