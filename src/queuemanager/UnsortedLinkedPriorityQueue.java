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

    private  Node<T> head, tail;

    public UnsortedLinkedPriorityQueue() {
        head = null;
        tail = null;
    }


    @Override
    public void add(T item, int priority) throws StringIndexOutOfBoundsException {

        PriorityItem<T> newItem = new PriorityItem<>(item, priority);
        Node node = new Node(newItem, head);

        if (head == null) {
            head = tail = node;
            head.setPrevious(null);
            tail.setNext(null);
        } else {

            tail.setNext(node);
            node.setPrevious(tail);
            tail = node;
            tail.setNext(null);

        }
    }

    @Override
    public T head() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        }
        Node temp = head;
        Node previous = null;
        Node highPriority = null;
        int priority = (int) Double.POSITIVE_INFINITY;

        while (temp != null) {

            if (((PriorityItem)temp.getItem()).getPriority() < priority) {

                //System.out.println("found item with higher priority");
                highPriority = temp;
                previous = temp;
                temp = temp.getNext();

            } else {

                previous = temp;
                temp = temp.getNext();
            }

            if (highPriority != null) {
                priority = ((PriorityItem) highPriority.getItem()).getPriority();
            }
        }

        return (T) highPriority.getItem();
    }

    @Override
    public void remove() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        }

        Node temp = head;
        Node previous = null;
        Node<T> highPriority = null;
        int priority = (int) Double.POSITIVE_INFINITY;

        //Search for highest priority item within each node
        while (temp != null) {
            if (((PriorityItem)temp.getItem()).getPriority() < priority) {
                highPriority = temp;
                temp = temp.getNext();
            } else {
                temp = temp.getNext();
            }

            if (highPriority != null) {
                priority = ((PriorityItem) highPriority.getItem()).getPriority();
            }
        }

        //Check position of target node
        Node before = highPriority.getPrevious();
        Node after = highPriority.getNext();

        //Condition: last node
        if (before == null && after == null) {

            head = null;

        }
        //Condition: node at start or end
        else if (after == null || before == null) {
            //Target at tail
            if (after == null && before != null){
                before.setNext(null);

            }
            //Target at head
            else if (before == null && after != null) {
                head = head.getNext();
                head.setPrevious(null);
            }


        }
        //Condition: Target node between two other nodes
        else {
            before.setNext(after);
            after.setPrevious(before);
        }
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
            }
            result += node.getItem();
        }

        result = result + "]";

        return result;
    }

}
