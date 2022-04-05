package queuemanager;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Test class for UnsortedLinkedPriorityQueue
 * Tests public methods and checks that expected exceptions are thrown
 */
public class UnsortedLinkedPriorityQueueTest {
    private UnsortedLinkedPriorityQueue testQueue;
    private  Person person;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testHead() throws QueueUnderflowException, QueueOverflowException {
        testQueue = new UnsortedLinkedPriorityQueue();

        person = new Person("Riley");
        testQueue.add(person, 2);

        person = new Person("Riley");
        testQueue.add(person, 4);

        person = new Person("Callum");
        testQueue.add(person, 1);

        PriorityItem expectation = new PriorityItem<>(person, 1);

        System.out.println("Queue: " + testQueue);
        System.out.println("Head expected: (Callum, 1)");
        System.out.println("Head: " + testQueue.head());
        assertEquals(expectation, testQueue.head());
    }

    @Test
    public void underflowTest() throws QueueUnderflowException {
        testQueue = new UnsortedLinkedPriorityQueue();

        System.out.println(testQueue);
        exception.expect(QueueUnderflowException.class);

        testQueue.head();
    }

    @Test
    public void testAdd() {
        testQueue = new UnsortedLinkedPriorityQueue();

        assertTrue(testQueue.isEmpty());
        System.out.println("Before adding: " + testQueue);

        person = new Person("Riley");
        testQueue.add(person, 2);

        System.out.println("After adding: " + testQueue);
        assertFalse(testQueue.isEmpty());
    }

    @Test
    public void testRemove() throws QueueUnderflowException {
        testQueue = new UnsortedLinkedPriorityQueue();

        assertTrue(testQueue.isEmpty());

        person = new Person("Riley");
        testQueue.add(person, 2);

        System.out.println("Before removing: " + testQueue);
        assertFalse(testQueue.isEmpty());

        testQueue.remove();

        System.out.println("After removing: " + testQueue);
        assertTrue(testQueue.isEmpty());
    }

    @Test
    public void testIsEmpty() {
        testQueue = new UnsortedLinkedPriorityQueue();
        System.out.println(testQueue);
        System.out.println(testQueue.isEmpty());
        assertTrue(testQueue.isEmpty());
    }

    @Test
    public void testToString() throws QueueOverflowException {
        testQueue = new UnsortedLinkedPriorityQueue();

        assertEquals("[]", testQueue.toString());

        person = new Person("Riley");
        testQueue.add(person, 2);

        System.out.println(testQueue);
        assertNotEquals("[]", testQueue.toString());

        person = new Person("Riley");
        testQueue.add(person, 4);

        System.out.println(testQueue);
        assertNotEquals("[]", testQueue.toString());

        person = new Person("Callum");
        testQueue.add(person, 1);

        System.out.println(testQueue);
        assertNotEquals("[]", testQueue.toString());
    }
}