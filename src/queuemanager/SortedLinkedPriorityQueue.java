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
public class SortedLinkedPriorityQueue<T> implements PriorityQueue<T> {

    private  Node<T> head;

    public SortedLinkedPriorityQueue() {
        head = null;
    }


    @Override
    public void add(T item, int priority) throws StringIndexOutOfBoundsException {

        PriorityItem<T> newItem = new PriorityItem<>(item, priority);

        Node newNode = new Node(newItem);

        if (head == null) {
            head = newNode;
            return;
        }

        Node temp = head;
        Node previous = null;


        while (temp != null && ((PriorityItem)temp.getItem()).getPriority() <= priority) {

            previous = temp;
            temp = temp.getNext();
        }

        //If no items had a priority greater (lower number), add new node to the end
        //Previous in this case is the last non-NULL element
        if (temp == null) {
            previous.setNext(newNode);

        } else {

            //If previous is NULL (ie first item had a lower priority (higher number))
            //Add new node as the head
            if (previous == null) {
                newNode.setNext(head);
                head = newNode;

            }
            //Else add new node between temp and previous nodes
            else {
                newNode.setNext(temp);
                previous.setNext(newNode);
            }
        }
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
