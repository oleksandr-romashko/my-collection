package global.goit.romashko.my_collection;

public final class Node<E> {

    private final E element;
    private Node<E> prev;
    private Node<E> next;

    public Node (E element) {
        this.element = element;
        this.next = this;
        this.prev = this;
    }
    public Node (E element, Node<E> next, Node<E> prev) {
        this.element = element;
        this.next = next;
        this.prev = prev;
    }

    public E getElement() {
        return element;
    }

    public Node<E> getNext() {
        return next;
    }

    public Node<E> getPrev() {
        return prev;
    }

    public void setPrev(Node<E> prev) {
        this.prev = prev;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }
}
