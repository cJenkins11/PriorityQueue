package queuemanager;

/**
 * Implementation of the PriorityQueue ADT using an unsorted array for storage.
 *
 * Because Java does not allow generic arrays (!), this is implemented as an
 * array of Object rather than of PriorityItem&lt;T&gt;, which would be natural.
 * Array elements accessed then have to be cast to PriorityItem&lt;T&gt; before
 * using their getItem() or getPriority() methods.
 * 
 * @param <T> The type of things being stored.
 */
public class UnsortedArrayPriorityQueue<T> implements PriorityQueue<T> {

    /**
     * Where the data is actually stored.
     */
    private final Object[] storage;

    /**
     * The size of the storage array.
     */
    private final int capacity;

    /**
     * The index of the last item stored.
     *
     * This is equal to the item count minus one.
     */
    private int tailIndex;

    /**
     * Create a new empty queue of the given size.
     *
     * @param size
     */
    public UnsortedArrayPriorityQueue(int size) {
        storage = new Object[size];
        capacity = size;
        tailIndex = -1;
    }

    @Override
    public T head() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        } else {

            int headIndex = -1;
            double headPriority = Double.POSITIVE_INFINITY;

            for (int i = 0; i < storage.length; i++){
                if (((PriorityItem)storage[i]) != null) {
                    if (((PriorityItem) storage[i]).getPriority() <= headPriority) {
                        headPriority = ((PriorityItem) storage[i]).getPriority();
                        headIndex = i;
                    }
                }
            }

            return ((PriorityItem<T>) storage[headIndex]).getItem();

        }
    }

    @Override
    public void add(T item, int priority) throws QueueOverflowException {
        tailIndex = tailIndex + 1;
        if (tailIndex >= capacity) {
            /* No resizing implemented, but that would be a good enhancement. */
            tailIndex = tailIndex - 1;
            throw new QueueOverflowException();
        } else {
            /* Insert at end */
            int i = tailIndex;

            storage[i] = new PriorityItem<>(item, priority);
        }
    }

    @Override
    public void remove() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        } else {
            //Find index of the highest priority from the first index
            int headIndex = -1;
            double headPriority = Double.POSITIVE_INFINITY;

            for (int i = 0; i < storage.length; i++){
                if (((PriorityItem)storage[i]) != null) {
                    if (((PriorityItem) storage[i]).getPriority() <= headPriority) {
                        headPriority = ((PriorityItem) storage[i]).getPriority();
                        headIndex = i;
                    }
                }
             }

            for (int i = headIndex; i < storage.length - 1; i++) {
                storage[i] = storage[i + 1];
            }
            tailIndex = tailIndex - 1;

        }
    }

    @Override
    public boolean isEmpty() {
        return tailIndex < 0;
    }

    @Override
    public String toString() {
        String result = "[";
        for (int i = 0; i <= tailIndex; i++) {
            if (i > 0) {
                result = result + ", ";
            }
            result = result + storage[i];
        }
        result = result + "]";
        return result;
    }
}
