import java.util.HashMap;

public class Utility {

	public static int convertBitToInt(String string) {

		checkBitString(string);

		/** Convert from bit to int */
		if (stringIsEmpty(string)) {
			return 0;
		}
		int converted = 0;
		for (int i = 0; i < string.length(); i++) {
			converted += getValue(string.charAt(string.length() - 1 - i))
					* Math.pow(2, i);
		}
		return converted;

	}

	public static String convertIntToBit(int number) {
		checkInt(number);
		StringBuilder bitString = new StringBuilder("000000000000000000000000");
		int[] powers = new int[25];
		for (int i = 0; i < powers.length; i++) {
			powers[i] = (int) Math.pow(2, i);
		}

		for (int i = powers.length - 2; i >= 0; i--) {

			if (number == powers[i]) {
				bitString.setCharAt(23 - i, '1');
				number = number - powers[i];
			} else if (number > powers[i]) {
				if (number < powers[i + 1]) {
					bitString.setCharAt(23 - i, '1');
					number = number - powers[i];
				}

			}

		}

		return bitString.toString();

	}

	private static void checkInt(int number) {
		if (number > 16777215) {
			throw new IllegalArgumentException(
					"The number to be converted cant be larger than 16777215");
		}
	}

	private static void checkBitString(String string) {
		/**
		 * Check if string has more than 24 characters and if string contains
		 * other than 0 and 1
		 */
		if (string.length() > 24) {
			throw new IllegalArgumentException(
					"String length must be less than 25");
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

	public static int convertHexToInt(String s) {
		if (stringIsEmpty(s)) {
			return 0;
		}
		checkHexString(s);
		String string = s.toUpperCase();

		int converted = 0;
		for (int i = 0; i < string.length(); i++) {
			converted += getValue(string.charAt(string.length() - 1 - i))
					* Math.pow(16, i);
		}

		return converted;
	}

	private static int getValue(char c) {

		HashMap<Character, Integer> values = new java.util.HashMap<Character, Integer>(
				16);
		for (int i = 0; i < 7; i++) {
			values.put((char) ('A' + i), 10 + i);
		}

		for (int i = 0; i < 10; i++) {
			values.put((char) ('0' + i), i);
		}
		return values.get(c);
	}

	private static void checkHexString(String string) {
		if (string.length() > 6) {
			throw new IllegalArgumentException(
					"String length must be less than 7");
		}

		if (!string.matches("[0-9A-Fa-f]+")) {
			throw new IllegalArgumentException(
					"String can only contain characters: 01234567890ABCDEF / abcdef");
		}
	}

	public static String convertIntToHex(int number) {
		checkInt(number);
		
		return ("");
		
	}


}
