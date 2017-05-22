package app;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 */
public class Accuracy5PercentTest {

	/**
	 * This value accuracy measured in +-n%
	 */
	private static final int ACCURACY = 5;

	@Test
	public void testCheckValueInRange0() {
		boolean res = Accuracy.checkValueInRange(120, 121, ACCURACY);
		Assert.assertEquals(true, res);
	}

	@Test
	public void testCheckValueInRange1() {
		boolean res = Accuracy.checkValueInRange(120, 122.5, ACCURACY);
		Assert.assertEquals(true, res);
	}

	@Test
	public void testCheckValueInRange2() {
		boolean res = Accuracy.checkValueInRange(120, 126.1, ACCURACY);
		Assert.assertEquals(false, res);
	}

	@Test
	public void testCheckValueInRange3() {
		boolean res = Accuracy.checkValueInRange(120, 118, ACCURACY);
		Assert.assertEquals(true, res);
	}

	@Test
	public void testCheckValueInRange4() {
		boolean res = Accuracy.checkValueInRange(120, 111.5, ACCURACY);
		Assert.assertEquals(false, res);
	}

	@Test
	public void testCheckValueInRange5() {
		boolean res = Accuracy.checkValueInRange(1.105, 1.15, ACCURACY);
		Assert.assertEquals(true, res);
	}

	@Test
	public void testCheckValueInRange6() {
		boolean res = Accuracy.checkValueInRange(5.5E-5, 5.23E-5, ACCURACY);
		Assert.assertEquals(true, res);
	}

	@Test
	public void testCheckValueInRange7() {
		boolean res = Accuracy.checkValueInRange(5.5E-5, 5.9E-5, ACCURACY);
		Assert.assertEquals(false, res);
	}
}