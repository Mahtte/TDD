public class Utility {

	public static int convertBitToInt(String string) {

		/**
		 * Check if string has more than 24 characters and if string contains
		 * other than 0 and 1
		 */
		if (string.length() > 24) {
			throw new IllegalArgumentException(
					"String length must be less than 24");
		}

		if (hasLetter(string) && !stringIsEmpty(string)) {
			throw new IllegalArgumentException(
					"String can only contain numbers 0 - 1");
		}

		if (hasDigitBiggerThanOne(string) && !stringIsEmpty(string)) {
			throw new IllegalArgumentException(
					"String can only contain numbers 0 - 1");
		}

		/** Convert from bit to int */
		int converted = 0;
		if (stringIsEmpty(string)) {
			return converted;
		} else {
			return charIsZeroOrOne(string.charAt(0));

		}
			
	}

	private static boolean hasDigitBiggerThanOne(String string) {
		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) > '1') {
				return true;
			}
		}
		return false;
	}

	private static boolean hasLetter(String string) {
		String regex = "\\d+";
		if (string.matches(regex)) {
			return false;
		} else
			return true;

	}

	private static boolean stringIsEmpty(String string) {
		if (string.length() < 1) {
			return true;
		} else
			return false;
	}
	
	private static int charIsZeroOrOne(char c) {
		if (c == '1') {
			return 1;
		}
		else 
			return 0;
	}
}
