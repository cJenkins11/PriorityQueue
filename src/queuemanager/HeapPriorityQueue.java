package queuemanager;

/**
 * Implementation of the PriorityQueue ADT using an array for storage that adheres to min-heap rules.
 * The root node is the highest priority (the lowest number) and each child has a lower priority (higher number)
 *
 * Because Java does not allow generic arrays (!), this is implemented as an
 * array of Object rather than of PriorityItem&lt;T&gt;, which would be natural.
 * Array elements accessed then have to be cast to PriorityItem&lt;T&gt; before
 * using their getItem() or getPriority() methods.
 * 
 * @param <T> The type of things being stored.
 */
public class HeapPriorityQueue<T> implements PriorityQueue<T> {

    /**
     * Where the data is actually stored.
     */
    private final Object[] storage;

    /**
     * The max size of the storage array.
     */
    private final int capacity;

    /**
     * Current count of the array size
     */
    private int size;

    /**
     * Starting point of the array
     */
    private final int START = 1;


    /**
     * Create a new empty queue of the given size.
     * Given size must be >= 4 to store the initial root and it's children
     * @param size - Size of the array
     */
    public HeapPriorityQueue(int size) {
        capacity = size;
        this.size = 0;
        storage = new Object[capacity + 1];
    }

    /**
     * Gets the parents index of a given position in the array
     *
     * @param position - Position to get the parent of
     * @return - Index of the parent of the given position
     */
    private int getParent(int position) {
        return position/2;
    }

    /**
     * Gets the left child's index of a given position in the array
     *
     * @param position - Position to get the left child of
     * @return - Index of the left child of the given position
     */
    private int leftChild(int position) {
        return position*2;
    }

    /**
     * Gets the right child's index of a given position in the array
     *
     * @param position - Position to get the right child of
     * @return - Index of the right child of the given position
     */
    private int rightChild(int position) {
        return (position*2) + 1;
    }

    /**
     * Checks if a given position in the array is a leaf
     *
     * @param position - Position to check if it is a leaf or not
     * @return - True if position is a leaf, otherwise false
     */
    private boolean isLeaf(int position) {
        if (position > (size/2) && position <= size) {
            return true;
        }
        return false;
    }

    /**
     * Takes 2 positions in an array and swaps them
     *
     * @param positionA - Position in array to swap
     * @param positionB - Position in array to swap to
     */
    private void swap(int positionA, int positionB) {
        Object temp;
        temp = storage[positionA];

        storage[positionA] = storage[positionB];
        storage[positionB] = temp;
    }

    /**
     * Recursive function to build the array to adhere to min-heap rules
     *
     * @param position - Position the function checks first, typically the root of the heap
     */
    private void minHeapify(int position) {

        if (isLeaf(position)) {return;}

        int left = leftChild(position);
        int right = rightChild(position);

        int smallest = left;

        if (right <= size && ((PriorityItem)storage[right]).getPriority() < ((PriorityItem)storage[left]).getPriority()) {
            smallest = right;
        }

        if (storage[smallest] == null) {

            storage[START] = null;
            return;
        }

        if (((PriorityItem)storage[position]).getPriority() > ((PriorityItem)storage[smallest]).getPriority()) {
            swap(position, smallest);
            minHeapify(smallest);
        }
    }

    /**
     * Gets the item with the highest priority, the root.
     *
     * @return - Root of the tree
     * @throws QueueUnderflowException - If tree is empty, it has no root
     */
    @Override
    public T head() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        } else {
            return ((PriorityItem<T>) storage[START]).getItem();
        }
    }

    /**
     * Adds an item with a priority to the array
     *
     * @param item - new item to be added to the array
     * @param priority - priority of that item
     */
    @Override
    public void add(T item, int priority) {

        storage[++size] = new PriorityItem(item, priority);
        int current = size;

        while (current > 1 && ((PriorityItem)storage[current]).getPriority() < ((PriorityItem)storage[getParent(current)]).getPriority()) {

            swap(current, getParent(current));
            current = getParent(current);

        }
    }

    /**
     * Removes the root of the tree and ensures it still follows the heap rules
     * @throws QueueUnderflowException
     */
    @Override
    public void remove() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        } else {

            storage[START] = storage[size--];
            minHeapify(START);

        }
    }

    /**
     * @return True if the size is less than the starting index
     */
    @Override
    public boolean isEmpty() {
        return size < START;
    }


    /**
     * @return a result string detailing the parents and each of their children.
     */
    @Override
    public String toString() {
        String result = "";

        /*if (storage[START] == null) {
            result += "Queue is empty";
        }*/

        for (int i = START; i <= ((size/2) + 1); i++) {
            result += "Parent: " + storage[i] + "\nLeft child: " + storage[i*2] + " - Right child: " + storage[(i*2)+1] + "\n";
        }

        return result;
    }
}
