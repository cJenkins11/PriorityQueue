package queuemanager;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Test class for UnsortedArrayPriorityQueue
 * Tests public methods and checks that expected exceptions are thrown
 */
public class UnsortedArrayPriorityQueueTest {

    private UnsortedArrayPriorityQueue testQueue;
    private  Person person;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testHead() throws QueueUnderflowException, QueueOverflowException {
        testQueue = new UnsortedArrayPriorityQueue(3);

        person = new Person("Riley");
        testQueue.add(person, 2);

        person = new Person("Riley");
        testQueue.add(person, 4);

        person = new Person("Callum");
        testQueue.add(person, 1);

        System.out.println("Queue: " + testQueue);
        System.out.println("Head expected: Callum");
        System.out.println("Head: " + testQueue.head());
        assertEquals(person, testQueue.head());
    }

    @Test
    public void overflowTest() throws QueueOverflowException {
        testQueue = new UnsortedArrayPriorityQueue(1);

        exception.expect(QueueOverflowException.class);

        person = new Person("Riley");
        testQueue.add(person, 4);

        person = new Person("Callum");
        testQueue.add(person, 1);
    }

    @Test
    public void underflowTest() throws QueueUnderflowException {
        testQueue = new UnsortedArrayPriorityQueue(1);

        System.out.println(testQueue);
        exception.expect(QueueUnderflowException.class);

        testQueue.head();
    }

    @Test
    public void testAdd() throws QueueOverflowException {
        testQueue = new UnsortedArrayPriorityQueue(1);

        assertTrue(testQueue.isEmpty());
        System.out.println("Before adding: " + testQueue);

        person = new Person("Riley");
        testQueue.add(person, 2);

        System.out.println("After adding: " + testQueue);
        assertFalse(testQueue.isEmpty());
    }

    @Test
    public void testRemove() throws QueueUnderflowException, QueueOverflowException {
        testQueue = new UnsortedArrayPriorityQueue(1);

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
        testQueue = new UnsortedArrayPriorityQueue(1);
        System.out.println(testQueue);
        System.out.println(testQueue.isEmpty());
        assertTrue(testQueue.isEmpty());
    }

    @Test
    public void testToString() throws QueueOverflowException {
        testQueue = new UnsortedArrayPriorityQueue(3);

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