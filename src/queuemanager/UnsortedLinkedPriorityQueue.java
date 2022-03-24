package queuemanager;

/**
 * Implementation of the PriorityQueue ADT using a sorted array for storage.
 *
 * Because Java does not allow generic arrays (!), this is implemented as an
 * array of Object rather than of PriorityItem&lt;T&gt;, which would be natural.
 * Array elements accessed then have to be cast to PriorityItem&lt;T&gt; before
 * using their getItem() or getPriority() methods.
 * 
 * This is an example of Java's poor implementation getting in the way. Java
 * fanboys will no doubt explain at length why it has to be this way, but note
 * that Eiffel allows it because Eiffel generics were done right from the start,
 * rather than being tacked on as an afterthought and limited by issues of
 * backward compatibility. Humph!
 * 
 * @param <T> The type of things being stored.
 */
public class UnsortedLinkedPriorityQueue<T> implements PriorityQueue<T> {

    private  Node<T> head;
    //private int size;

    public UnsortedLinkedPriorityQueue() {
        head = null;
        //size = 0;
    }


    @Override
    public void add(T item, int priority) throws QueueOverflowException, StringIndexOutOfBoundsException {

        PriorityItem<T> newItem = new PriorityItem<>(item, priority);
        head = new Node(newItem, head);
    }

    @Override
    public T head() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        }
        return head.getItem();
    }

    @Override
    public void remove() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        }

        /*Node temp = head;
        Node previous = null;
        int priority = (int) Double.POSITIVE_INFINITY;*/

        /*while (temp != null && ((PriorityItem)temp.getItem()).getPriority() <= priority) {

            previous = temp;
            temp = temp.getNext();
        }*/



        head = head.getNext();
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }


    @Override
    public String toString() {
        String result = "[";

        for (Node<T> node = head; node != null; node = node.getNext()) {
            if (node != head) {
                result += ", ";
                //System.out.println("Node was not the head node");
            }
            result += node.getItem();
            //System.out.println("Node item added to result string");
        }

        result = result + "]";

        return result;
    }

}
