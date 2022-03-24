package queuemanager;

public class Node<T> {

    private T item;
    private Node<T> next;

    public Node(T item) {
        this.item = item;
    }

    public Node(T item, Node<T> next) {
        this.item = item;
        this.next = next;
    }

    public T getItem() {
        return item;
    }

    public void setNext(Node<T> next) {this.next = next;}

    public Node<T> getNext() {
        return next;
    }

}

