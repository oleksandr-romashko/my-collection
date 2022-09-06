package global.goit.romashko.my_collection.hashmap;

import java.util.*;

public class MyHashMap<K, V> implements MyMap<K, V> {
    private static final int DEFAULT_INI_BUCKET_CAPACITY = 8;
    private static final float LOAD_FACTOR = 0.75f;

    private Node<K, V>[] table;

    public MyHashMap() {
        table = createEmptyBucketsArray();
    }

    @Override
    public void clear() {
        Arrays.fill(table, null);
    }

    @Override
    public int size() {
        int size = 0;
        for (Node<K, V> node : table) {
            if (node != null) {
                Node<K, V> currentNode = node;
                while (currentNode != null) {
                    size++;
                    currentNode = currentNode.getNext();
                }
            }
        }
        return size;
    }

    @Override
    public void put(K key, V value) {
        Node<K,V> newNode = new Node<>(key, value);
        int bucketIndex = calculateTableIndex(Objects.hashCode(newNode));
        if (table[bucketIndex] == null) {
            table[bucketIndex] = newNode;
        } else {
            Node<K,V> current = table[bucketIndex];
            //check for duplicates
            while (current != null) {
                if (current.equals(newNode)) {
                    current.setValue(newNode.getValue());
                    return;
                }
                //if no duplicates, add new Node as a tail element
                if (current.getNext() == null) {
                    current.setNext(newNode);
                    current = current.getNext();
                }
                current = current.getNext();
            }
        }
        expandTableIfNecessary();
    }

    @Override
    public void remove(K key) {
        int keyHash = Objects.hashCode(key);
        int bucketIndex = calculateTableIndex(keyHash);
        if (table[bucketIndex] != null) {
            Node<K,V> current = table[bucketIndex];
            if (Objects.equals(current.getKey(), key)) {
                if(current.getNext() == null) {
                    table[bucketIndex] = null;
                } else {
                    table[bucketIndex] = current.getNext();
                }
                shrinkTableIfNecessary();
                return;
            }
            Node<K,V> prev = current;
            current = prev.getNext();
            while (current != null) {
                if (Objects.equals(current.getKey(), key)) {
                    if(current.getNext() == null) {
                        prev.setNext(null);
                    } else {
                        prev.setNext(current.getNext());
                    }
                    shrinkTableIfNecessary();
                    return;
                }
                prev = current;
                current = prev.getNext();
            }
        }
        shrinkTableIfNecessary();
    }

    @Override
    public V get(K key) {
        int keyHash = Objects.hashCode(key);
        int bucket = calculateTableIndex(keyHash);
        Node<K, V> currentNode = table[bucket];
        while (currentNode != null) {
            if (currentNode.getHash() == keyHash && currentNode.getKey().equals(key)) {
                return currentNode.getValue();
            }
            currentNode = currentNode.getNext();
        }
        return null;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        for (Node<K, V> bucket : table) {
            if (bucket != null) {
                Node<K, V> currentNode = bucket;
                while (currentNode != null) {
                    sj.add(currentNode.toString());
                    currentNode = currentNode.getNext();
                }
            }
        }
        return sj.toString();
    }

    @SuppressWarnings("unchecked")
    private Node<K, V>[] createEmptyBucketsArray() {
        return new MyHashMap.Node[DEFAULT_INI_BUCKET_CAPACITY];
    }

    int calculateTableIndex(int hash) {
        return hash & (table.length - 1);
    }

    @SuppressWarnings("unchecked")
    private void expandTableIfNecessary() {
        int counter = countFilledBuckets(table);
        if (counter > (int) (LOAD_FACTOR * table.length) && counter < (int) (LOAD_FACTOR * Integer.MAX_VALUE / 2)) { //
            Node<K, V>[] oldTable = table;
            table = new MyHashMap.Node[(int) (oldTable.length * 2)];
            rehashTables(oldTable);
        }
    }

    @SuppressWarnings("unchecked")
    private void shrinkTableIfNecessary() {
        int counter = countFilledBuckets(table);
        if (counter <= (int) (LOAD_FACTOR * table.length / 2) && counter >= (DEFAULT_INI_BUCKET_CAPACITY * LOAD_FACTOR)) {
            Node<K, V>[] oldTable = table;
            table = new MyHashMap.Node[(int) (oldTable.length / 2)];
            rehashTables(oldTable);
        }
    }

    private void rehashTables(Node<K, V>[] oldTable) {
        for (Node<K, V> bucket : oldTable) {
            if (bucket != null) {
                Node<K, V> currentNode = bucket;
                while (currentNode != null) {
                    put(currentNode.getKey(), currentNode.getValue());
                    currentNode = currentNode.getNext();
                }
            }
        }
    }

    private int countFilledBuckets(Node<K, V>[] table) {
        int counter = 0;
        for (Node<K,V> node : table) {
            if (node != null) {
                counter++;
            }
        }
        return counter;
    }


    static class Node<K, V> {
        int hash;
        private final K key;
        private V value;
        private Node<K,V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
            hash = hashCode();
        }

        public final Node<K, V> getNext() {
            return next;
        }
        @Override
        public final String toString() {
            return key + "=" + value;
        }

        public int getHash() {
            return hash;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V newValue) {
            value = newValue;
        }

        public final void setNext(Node<K, V> next) {
            this.next = next;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            Node<?, ?> node = (Node<?, ?>) obj;

            return Objects.equals(key, node.key);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(key);
        }
    }


}
