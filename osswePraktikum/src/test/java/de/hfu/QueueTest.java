package de.hfu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import org.junit.rules.ExpectedException;

import org.junit.Test;
import org.junit.Rule;

public class QueueTest {

	// Initialise ExpectedException Rule so it can later be used to test correct exceptions.
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	/* Test for the parameters of creating the queue.
	 * 0 should be an invalid array length and should therefore throw the exception.
	 */
	@Test
	public void testInvalidArraySize() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Queue Arraylength 0");
		
		Queue queue = new Queue(0);
		// The test should never get to this point as the above line should throw an exception.
		queue.enqueue(1);
	}
	
	/* Test that normal insertion works as intended.
	 * This should create an array of length one with a "1" integer in it.
	 */
	@Test
	public void testNormalEnqueue() {
		Queue queue = new Queue(1);
		queue.enqueue(1);
		
		// Initialise a control array with the correct values.
		int[] expectedArray = {1};
		
		assertArrayEquals(expectedArray, queue.queue);
	}
	
	/* Test that enqueue adds to the correct position in the array.
	 * This requires two cases, one in case nothing has been dequeued,
	 * in which case the last position in the array is to be overwritten.
	 * The other scenario is to be handled by the next test.
	 * Expectation is that the last position in the array equals 4 at the end.
	 */
	@Test
	public void testEnqueuePositionEnd() {
		Queue queue = new Queue(3);
		queue.enqueue(0);
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(4);
		
		int[] expectedArray = {0,1,4};
		
		assertArrayEquals(expectedArray, queue.queue);
	}
	
	/* Test that enqueue adds to the correct position in the array.
	 * This test is for the second of two scenarios, where something has been dequeued
	 * and enqueue() should therefore add the new item to the position of the current head.
	 * To keep things simple, we will only dequeue one item so the new item is added to the
	 * front of the array.
	 * Expectation is for the first position in the array to equal 4.
	 */
	@Test
	public void testEnqueuePositionBeginning() {
		Queue queue = new Queue(3);
		queue.enqueue(0);
		queue.enqueue(1);
		queue.enqueue(2);
		queue.dequeue();
		queue.enqueue(4);
		
		int[] expectedArray = {4,1,2};
		
		assertArrayEquals(expectedArray, queue.queue);
	}
	
	/* Test that dequeue only works with a queue that contains data.
	 * Expectation is that the dequeue call will fail with the exception being thrown,
	 * since we call it with no items in queue.
	 */
	@Test
	public void testDequeueFail() {
		exception.expect(IllegalStateException.class);
		exception.expectMessage("dequeue on empty queue");
		
		Queue queue = new Queue(2);
		
		//if this returns true, multiple things have gone wrong.
		assertEquals(0, queue.dequeue());
	}
	
	/* Test that dequeue works as intended in an array that contains data.
	 * This requires a queue of length 2 with 2 items, so we can ensure that
	 * the correct item is being dequeued.
	 * Expectation is that the item that has spent the longest time in the queue is
	 * dequeued, so in this case, 1.
	 */
	@Test
	public void testNormalDequeue() {
		Queue queue = new Queue(2);
		queue.enqueue(1);
		queue.enqueue(2);
		
		assertEquals(1, queue.dequeue());
	}
	
}
