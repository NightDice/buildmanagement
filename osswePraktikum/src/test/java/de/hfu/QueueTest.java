package de.hfu;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
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
		// The test should never get to this point.
		queue.enqueue(1);
	}
	
	/* Test that normal insertion works as intended.
	 * This should create an array of length one with a "1" in it.
	 */
	@Test
	public void testNormalInsertion() {
		Queue queue = new Queue(1);
		queue.enqueue(1);
		
		// Initialise a control array with the correct values.
		int[] correctArray = new int[1];
		correctArray[1] = 1;
		
		assertArrayEquals(queue.queue, correctArray);
	}

}
