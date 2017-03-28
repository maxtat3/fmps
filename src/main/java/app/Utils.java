package app;

import java.util.ArrayList;

/**
 *
 */
public class Utils {

	/**
	 * Check is only number contains in string.
	 * Average performance run time = 0.1 ms.
	 *
	 * @param strNum number in string
	 * @return <tt>true<tt/> - is string contains only digits
	 */
	public static boolean isNumber(final String strNum) {
		if(strNum == null || strNum.isEmpty()) return false;

		ArrayList<Boolean> boolNumbers = new ArrayList<>(strNum.length());
		char[] strChars = strNum.toCharArray();
		for (char c : strChars) {
			if (Character.isDigit(c)) {
				boolNumbers.add(true);
			}
		}
		return strChars.length == boolNumbers.size();
	}

	public static boolean isEmpty(String text) {
		return (text.equals("") || text.equals(" ") || text.equals("  "));
	}
}
