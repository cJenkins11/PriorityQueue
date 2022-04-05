package queuemanager;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Test class for HeapPriorityQueue
 * Tests public methods and checks that expected exceptions are thrown
 */
public class HeapPriorityQueueTest {

    private HeapPriorityQueue testQueue;
    private  Person person;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testHead() throws QueueUnderflowException, QueueOverflowException {
        testQueue = new HeapPriorityQueue(8);

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
    public void testAdd() throws QueueOverflowException {
        testQueue = new HeapPriorityQueue(8);

        assertTrue(testQueue.isEmpty());
        System.out.println("Before adding: " + testQueue);

        person = new Person("Riley");
        testQueue.add(person, 2);

        person = new Person("Callum");
        testQueue.add(person, 6);

        person = new Person("Lucas");
        testQueue.add(person, 9);

        person = new Person("Chloe");
        testQueue.add(person, 1);

        System.out.println("After adding: " + testQueue);
        assertFalse(testQueue.isEmpty());
    }

    @Test
    public void testRemove() throws QueueUnderflowException, QueueOverflowException {
        testQueue = new HeapPriorityQueue(3);

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
        testQueue = new HeapPriorityQueue(4);
        System.out.println(testQueue);
        System.out.println(testQueue.isEmpty());
        assertTrue(testQueue.isEmpty());
    }

    @Test
    public void testToString() throws QueueOverflowException {
        testQueue = new HeapPriorityQueue(5);

        assertEquals("Parent: null\nLeft child: null - Right child: null\n", testQueue.toString());

        person = new Person("Riley");
        testQueue.add(person, 2);

        System.out.println(testQueue);
        assertNotEquals("Parent: null\nLeft child: null - Right child: null", testQueue.toString());
        assertEquals("Parent: (Riley, 2)\nLeft child: null - Right child: null\n", testQueue.toString());

        person = new Person("Chloe");
        testQueue.add(person, 4);

        System.out.println(testQueue);
        assertNotEquals("Parent: null\nLeft child: null - Right child: null", testQueue.toString());
        assertEquals("Parent: (Riley, 2)\nLeft child: (Chloe, 4) - Right child: null\nParent: (Chloe, 4)\nLeft child: null - Right child: null\n", testQueue.toString());

        person = new Person("Callum");
        testQueue.add(person, 1);

        System.out.println(testQueue);
        assertNotEquals("Parent: null\nLeft child: null - Right child: null", testQueue.toString());
        assertEquals("Parent: (Callum, 1)\nLeft child: (Chloe, 4) - Right child: (Riley, 2)\nParent: (Chloe, 4)\nLeft child: null - Right child: null\n", testQueue.toString());
    }
}