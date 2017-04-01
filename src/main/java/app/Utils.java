package app;

import java.util.ArrayList;

/**
 *
 */
public class Utils {

	/**
	 * Check is only integer or float number contains in string.
	 * Double numbers may be in format: [integer part].(,)[float part](d,f).
	 * For examples: 7.5 , 7,5 , 7.5d , 7,5d , 7.d , 7,d , 7,f .
	 * Allow only positive numbers.
	 * Average performance run time = 0.1 ms.
	 *
	 * @param strNum number in string
	 * @return <tt>true<tt/> - is string contains only digits
	 */
	public static boolean isNumber(final String strNum) {
		if(strNum == null) return false;

		int dotCommaSym = 0, fdSym = 0;
		ArrayList<Boolean> boolNumbers = new ArrayList<>(strNum.length());
		char[] strChars = strNum.toCharArray();
		for (char c : strChars) {
			if (Character.isDigit(c)) {
				boolNumbers.add(true);
			}
			if (dotCommaSym == 0 && c == '.') {
				boolNumbers.add(true);
				dotCommaSym ++;
			}
			if (dotCommaSym == 0 && c == ',') {
				boolNumbers.add(true);
				dotCommaSym ++;
			}
			if (fdSym == 0 && c == 'd') {
				boolNumbers.add(true);
				fdSym ++;
			}
			if (fdSym == 0 && c == 'f') {
				boolNumbers.add(true);
				fdSym ++;
			}
		}
		return strChars.length == boolNumbers.size();
	}

	/**
	 * Check is string do not contained any symbols.
	 *
	 * @param text validated text
	 * @return <tt>true</tt> - if string do not contains everybody symbols
	 */
	public static boolean isEmpty(String text) {
		return (text.equals("") || text.equals(" ") || text.equals("  "));
	}

	/**
	 * Check is text contains only cyrillic and latin letters.
	 *
	 * @param text validate text
	 * @return <tt>true</tt> - text contained only letters
	 */
	public static boolean isLettersOnly(String text) {
		return text.matches("[a-zA-Zа-яА-Я]+");
	}
}
