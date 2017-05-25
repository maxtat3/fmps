package controller;

import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;


public class InputDataControllerTest {

	/**
	 * In JTextField set name with ":" symbol, set text double value.
	 */
	@Test
	public void testValidateInputData() {
		JTextField jtf = new JTextField();
		jtf.setName("Fe: ");
		jtf.setText("311.5");
		String resValidate = new InputDataController().validateInputData(jtf, InputDataController.ValidatorVariant.IS_NUMBER);
		Assert.assertEquals(resValidate, InputDataController.SUCCESS_VALIDATE);
	}

	/**
	 * In JTextField set name without ":" symbol, set text double value.
	 */
	@Test
	public void testValidateInputData1() {
		JTextField jtf = new JTextField();
		jtf.setName("Molar mass"); // without <:> symbol
		jtf.setText("3125");
		String resValidate = new InputDataController().validateInputData(jtf, InputDataController.ValidatorVariant.IS_NUMBER);
		Assert.assertEquals(resValidate, InputDataController.SUCCESS_VALIDATE);
	}

	/**
	 * In JTextField NOT set name, set text double value.
	 */
	@Test (expected = NullPointerException.class)
	public void testValidateInputData2() {
		JTextField jtf = new JTextField();
		jtf.setText("3.5");
		String resValidate = new InputDataController().validateInputData(jtf, InputDataController.ValidatorVariant.IS_NUMBER);
		Assert.assertEquals(resValidate, InputDataController.SUCCESS_VALIDATE);
	}

	/**
	 * In JTextField set name with ":" symbol, set text int value.
	 */
	@Test
	public void testValidateInputData3() {
		JTextField jtf = new JTextField();
		jtf.setName("Fe: ");
		jtf.setText("311");
		String resValidate = new InputDataController().validateInputData(jtf, InputDataController.ValidatorVariant.IS_NUMBER);
		Assert.assertEquals(resValidate, InputDataController.SUCCESS_VALIDATE);
	}

	// TODO: 25.05.17 дописать тесты: проверка на double 1.2d, 1.2f; not number, not text .
}