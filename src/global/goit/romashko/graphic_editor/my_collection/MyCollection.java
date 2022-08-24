package global.goit.romashko.graphic_editor.my_collection;

public interface MyCollection<T> extends Iterable<T> {
    void clear();
    int size();
}
