public class Utility {

	public static int convertBitToInt(String string) {

		checkBitString(string);

		/** Convert from bit to int */
		if (stringIsEmpty(string)) {
			return 0;
		}
		int converted = 0;
		for (int i = 0; i < string.length(); i++) {
			converted += getIntValue(string.charAt(string.length() - 1 - i))
					* Math.pow(2, i);
		}
		return converted;

	}

	public static String convertIntToBit(int number) {
		checkInt(number);
		StringBuilder bitString = new StringBuilder("000000000000000000000000");

		for (int i = 23; i >= 0; i--) {
			if (number == Math.pow(2, i)) {
				bitString.setCharAt(23 - i, '1');
				number = (int) (number - Math.pow(2, i));

			} else if (number > Math.pow(2, i) && number < Math.pow(2, i + 1)) {
				bitString.setCharAt(23 - i, '1');
				number = (int) (number - Math.pow(2, i));
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
			converted += getIntValue(string.charAt(string.length() - 1 - i))
					* Math.pow(16, i);
		}

		return converted;
	}

	private static int getIntValue(char c) {
		if (c >= '0' && c <= '9') {
			return c - '0';
		} else
			return (c - 'A') + 10;
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
		StringBuilder hex = new StringBuilder(6);
		int rest = -1;
		int position = getLargestPowerOfSixteenThatIsLessOrEqualToNumber(number);
		int difference;

		while (rest != 0) {
			int power = getLargestPowerOfSixteenThatIsLessOrEqualToNumber(number);
			rest = (int) (number % Math.pow(16, power));
			number = (int) (number / Math.pow(16, power));
			if (power < position) {
				difference = position - power;
				hex.append(createZeroes(difference));
				position = position - difference;
				
			}

			hex.append(getHexValue(number));
			position--;
			if (rest == 0 && position >= 0) {
				difference = power;
				hex.append(createZeroes(difference));
				position = position - difference;
			}
			number = rest;
		}

		return hex.toString();

	}

	private static int getLargestPowerOfSixteenThatIsLessOrEqualToNumber(
			int number) {
		int p = 0;
		int power = (int) Math.pow(16, p);
		while (power <= number) {
			p++;
			power = (int) Math.pow(16, p);
		}
		return p - 1;

	}

	private static char getHexValue(int number) {
		if (number >= 0 && number <= 9) {
			return (char) ('0' + number);
		} else
			return (char) ('A' + number - 10);
	}

	private static String createZeroes(int times) {
		String str = "";

		while (times > 0) {
			str = str + '0';
			times--;
		}
		return str;
	}
}
