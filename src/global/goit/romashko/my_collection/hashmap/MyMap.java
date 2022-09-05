package global.goit.romashko.my_collection.hashmap;

import global.goit.romashko.my_collection.MyCollection;

public interface MyMap<K, V> extends MyCollection<K> {
    void put(K key, V value);
    void remove(K key);
    V get(K key);
}
