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

		if (hasLetter(string) && string.length() > 0) {
			throw new IllegalArgumentException(
					"String can only contain numbers 0 - 1");
		}

		/** Convert from bit to int */
		int converted = 0;
		if (string.length() < 1) {
			return converted;
		} else
			return converted;

	}

	private static boolean hasLetter(String string) {
		String regex = "\\d+";
		if (string.matches(regex)) {
			return false;
		} else
			return true;

	}
}
