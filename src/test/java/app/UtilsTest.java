package app;

import org.junit.Assert;
import org.junit.Test;
import util.Utils;

/**
 * Tests for {@link Utils} methods class.
 */
public class UtilsTest {

	// Tests for Utils.isNumber method

	@Test
	public void testIsNumberNumbersOnly(){
		boolean actual = Utils.isNumber("127");
		Assert.assertEquals(true, actual);
	}

	@Test
	public void testIsNumberNumbersOnly1(){
		boolean actual = Utils.isNumber("764928370");
		Assert.assertEquals(true, actual);
	}

	@Test
	public void testIsNumberNumbersOnlyZeroVal(){
		boolean actual = Utils.isNumber("0");
		Assert.assertEquals(true, actual);
	}

	@Test
	public void testIsNumberNumbersOnlyNegativeVal(){
		boolean actual = Utils.isNumber("-5510"); // allow only positive numbers
		Assert.assertEquals(false, actual);
	}

	@Test
	public void testIsNumberTextOnly(){
		boolean actual = Utils.isNumber("abcdk");
		Assert.assertEquals(false, actual);
	}

	@Test
	public void testIsNumberNumbersAndText(){
		boolean actual = Utils.isNumber("125abjk");
		Assert.assertEquals(false, actual);
	}

	@Test
	public void testIsNumberEmptyStr(){
		boolean actual = Utils.isNumber("");
		Assert.assertEquals(false, actual);
	}

	@Test
	public void testIsNumberFloatFormat(){
		boolean actual = Utils.isNumber("7.1");
		Assert.assertEquals(true, actual);
	}

	@Test
	public void testIsNumberFloatFormatRv1(){
		boolean actual = Utils.isNumber("7.1f");
		Assert.assertEquals(true, actual);
	}

	@Test
	public void testIsNumberFloatFormatRv2(){
		boolean actual = Utils.isNumber("7.1d");
		Assert.assertEquals(true, actual);
	}

	@Test
	public void testIsNumberFloatFormatRv3(){
		boolean actual = Utils.isNumber("7,1");
		Assert.assertEquals(true, actual);
	}

	@Test
	public void testIsNumberFloatFormatRv4(){
		boolean actual = Utils.isNumber("7,1f");
		Assert.assertEquals(true, actual);
	}

	@Test
	public void testIsNumberFloatFormatRv5(){
		boolean actual = Utils.isNumber("8,1d");
		Assert.assertEquals(true, actual);
	}

	@Test
	public void testIsNumberFloatFormatRv6(){
		boolean actual = Utils.isNumber("8.d");
		Assert.assertEquals(true, actual);
	}

	@Test
	public void testIsNumberFloatFormatRv7(){
		boolean actual = Utils.isNumber("8,d");
		Assert.assertEquals(true, actual);
	}

	@Test
	public void testIsNumberFloatFormatRv8(){
		boolean actual = Utils.isNumber("15.n"); //any symbol
		Assert.assertEquals(false, actual);
	}

	@Test
	public void testIsNumberFloatWrongFormat(){
		boolean actual = Utils.isNumber("1..d");
		Assert.assertEquals(false, actual);
	}

	@Test
	public void testIsNumberFloatWrongFormatRv1(){
		boolean actual = Utils.isNumber("1,,.d");
		Assert.assertEquals(false, actual);
	}

	@Test
	public void testIsNumberFloatWrongFormatRv2(){
		boolean actual = Utils.isNumber("1,.d");
		Assert.assertEquals(false, actual);
	}

	@Test
	public void testIsNumberFloatWrongFormatRv3(){
		boolean actual = Utils.isNumber("1,,,,5");
		Assert.assertEquals(false, actual);
	}

	// Tests for Utils.isEmpty method

	@Test
	public void testIsEmptyOnlyText(){
		boolean actual = Utils.isEmpty("Place");
		Assert.assertEquals(false, actual);
	}

	@Test
	public void testIsEmptyTextWithWhitespace(){
		boolean actual = Utils.isEmpty("Component A");
		Assert.assertEquals(false, actual);
	}

	@Test
	public void testIsEmptyOnlySpecSymbols(){
		boolean actual = Utils.isEmpty("!@#%&+_=/");
		Assert.assertEquals(false, actual);
	}

	@Test
	public void testIsEmptyEmptyStr(){
		boolean actual = Utils.isEmpty("");
		Assert.assertEquals(true, actual);
	}

	@Test
	public void testIsEmptyWhitespace(){
		boolean actual = Utils.isEmpty(" ");
		Assert.assertEquals(true, actual);
	}

	// Tests for Utils.isLettersOnly method

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
