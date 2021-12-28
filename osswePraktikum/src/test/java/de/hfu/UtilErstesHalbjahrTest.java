package de.hfu;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.rules.ExpectedException;

import org.junit.Rule;
import org.junit.Test;

public class UtilErstesHalbjahrTest {
	
	// Initialise ExpectedException for use when testing for IllegalArgumentException later.
	@Rule
	public ExpectedException exception = ExpectedException.none();

	/* Testing the outer limit of the upper bound of the method.
	 * Seventh month is not in the first semester, so the expected return is false.
	 */
	@Test
	public void testUpperBoundFail() {
		assertFalse(Util.istErstesHalbjahr(7));
	}
	
	/* Testing the inner limit of the upper bound.
	 * Sixth month is in the first semester, so should return true.
	 */
	@Test
	public void testUpperBoundSuccess() {
		assertTrue(Util.istErstesHalbjahr(6));
	}
	
	/* Testing the inner limit of the lower bound of the method.
	 * First month is in the first semester, expecting true to be returned.
	 */
	@Test
	public void testLowerBoundSuccess() {
		assertTrue(Util.istErstesHalbjahr(1));
	}
	
	/* Testing invalid input on the lower bound.
	 * 0 should throw an exception.
	 */
	@Test
	public void testLowerInvalidInput() {
		exception.expect(IllegalArgumentException.class);
		Util.istErstesHalbjahr(0);
	}
	
	/* Testing invalid input on the upper bound.
	 * 13 should trigger the exception.
	 */
	@Test
	public void testUpperInvalidInput() {
		exception.expect(IllegalArgumentException.class);
		Util.istErstesHalbjahr(13);
	}

}
