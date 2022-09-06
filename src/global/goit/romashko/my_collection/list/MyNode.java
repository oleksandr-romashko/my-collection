package global.goit.romashko.my_collection.list;

public final class MyNode<E> {

    private final E element;
    private MyNode<E> prev;
    private MyNode<E> next;

    public MyNode(E element) {
        this.element = element;
        this.next = this;
        this.prev = this;
    }
    public MyNode(E element, MyNode<E> next, MyNode<E> prev) {
        this.element = element;
        this.next = next;
        this.prev = prev;
    }

    public E getElement() {
        return element;
    }

    public MyNode<E> getNext() {
        return next;
    }

    public MyNode<E> getPrev() {
        return prev;
    }

    public void setPrev(MyNode<E> prev) {
        this.prev = prev;
    }

    public void setNext(MyNode<E> next) {
        this.next = next;
    }
}
