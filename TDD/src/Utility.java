public class Utility {

	public static int convertBitToInt(String string) {

		checkArguments(string);

		/** Convert from bit to int */
		if (stringIsEmpty(string)) {
			return 0;
		}
		int converted = 0;
		for (int i = 0; i < string.length(); i++) {
			converted += charIsZeroOrOne(string.charAt(string.length() - 1 - i))
					* Math.pow(2, i);
		}
		return converted;

	}

	private static void checkArguments(String string) {
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
	
	/** converts character 1 or 0 to int value 1 or 0.
	 * 
	 * @param c character to be converted
	 * @return 1 or 0
	 */
	private static int charIsZeroOrOne(char c) {
		if (c == '1') {
			return 1;
		} else
			return 0;
	}
	
}
