package db;

import app.Utils;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 */
public class UtilsTest {

	@Test
	public void testIsTextContainedLettersOnly(){
		boolean actual = Utils.isLettersOnly("green");
		Assert.assertEquals(true, actual);
	}

	@Test
	public void testIsTextContainedLettersOnlyRv1(){
		boolean actual = Utils.isLettersOnly("GreEN");
		Assert.assertEquals(true, actual);
	}

	@Test
	public void testIsTextContainedLettersOnlyRv2(){
		boolean actual = Utils.isLettersOnly("Dmitry");
		Assert.assertEquals(true, actual);
	}

	@Test
	public void testIsTextContainedLettersOnlyRv3(){
		boolean actual = Utils.isLettersOnly("green yellow");
		Assert.assertEquals(false, actual); // whitespaces are not allowed
	}

	@Test
	public void testIsTextContainedLettersOnlyRv4(){
		boolean actual = Utils.isLettersOnly("Green5AndYellowColor"); // numbers is not allowed
		Assert.assertEquals(false, actual);
	}

	@Test
	public void testIsTextContainedLettersOnlyRv5(){
		boolean actual = Utils.isLettersOnly("GreenColor!"); // special symbols is not allowed
		Assert.assertEquals(false, actual);
	}

	@Test
	public void testIsTextContainedLettersOnlyRv6(){
		boolean actual = Utils.isLettersOnly("<script>");
		Assert.assertEquals(false, actual);
	}

	@Test
	public void testIsTextContainedLettersOnlyRv7(){
		boolean actual = Utils.isLettersOnly("+-#Symbol");
		Assert.assertEquals(false, actual);
	}

	@Test
	public void testIsTextContainedLettersOnlyRv8(){
		boolean actual = Utils.isLettersOnly("Александр");
		Assert.assertEquals(true, actual);
	}

	@Test
	public void testIsTextContainedLettersOnlyRv9(){
		boolean actual = Utils.isLettersOnly("александр");
		Assert.assertEquals(true, actual);
	}

	@Test
	public void testIsTextContainedLettersOnlyRv10(){
		boolean actual = Utils.isLettersOnly("АлександрAlexandr");
		Assert.assertEquals(true, actual);
	}

	@Test
	public void testIsTextContainedLettersOnlyRv11(){
		boolean actual = Utils.isLettersOnly("Александр Alexandr");
		Assert.assertEquals(false, actual);
	}

	@Test
	public void testIsTextContainedLettersOnlyRv12(){
		boolean actual = Utils.isLettersOnly("Александр5");
		Assert.assertEquals(false, actual);
	}

	@Test
	public void testIsTextContainedLettersOnlyRv13(){
		boolean actual = Utils.isLettersOnly("АлександрAlexandr");
		Assert.assertEquals(true, actual);
	}

	@Test
	public void testIsTextContainedLettersOnlyRv14(){
		boolean actual = Utils.isLettersOnly("Александр ");
		Assert.assertEquals(false, actual);
	}

	@Test
	public void testIsTextContainedLettersOnlyRv15(){
		boolean actual = Utils.isLettersOnly("Дмитрий!");
		Assert.assertEquals(false, actual);
	}
}
