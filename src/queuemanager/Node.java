package queuemanager;

/**
 * Node class to store an item and a link to the next node
 *
 * @param <T> The type of object stored
 */
public class Node<T> {

    private T item;
    private Node<T> next;
    private Node<T> previous;

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

    public void setPrevious(Node<T> previous) {this.previous = previous;}

    public Node<T> getPrevious() {
        return previous;
    }

}

