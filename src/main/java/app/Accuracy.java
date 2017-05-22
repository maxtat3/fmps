package app;

/**
 * Определение точности вводимых данных пользователем.
 */
public class Accuracy {

	/**
	 * Точность вводимых данных пользователем в +-n%.
	 * Ответ пользователя на определенную формулу считается правильным если он
	 * входит в диапазон min, max. Чем больше значение этой переменной тем
	 * шире диапазон min, max.
	 */
	public static final int ACCURACY = 5;

	/**
	 * Проверка входит ли число в заданный диапазон [min, max].
	 * Например если значение этой переменной {@link #ACCURACY} = 10 (то есть +-10 %), а проверяемое число = 120,
	 * тогда 120 * 0.1 = 12 , min = 120 - 12, max = 120 + 12/2. То есть [114 ... 126].
	 *
	 * @param num1 original value, for example calculated from formula in this app
	 * @param num2 user calculated value
	 * @return <tt>true</tt> - both numbers are within required accuracy
	 */
	public static boolean checkValueInRange(double num1, double num2, double accuracy){
		double min = num1 -  (num1 * accuracy * 0.01);
		double max = num1 + (num1 * accuracy * 0.01);
		System.out.println("range = [" + min + ", " + max + "]");
		return num2 >= min && num2 <= max;
	}
}
