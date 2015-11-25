import java.util.HashMap;
import java.util.Set;

public class DataReader {
	private FileOpener fileOpener;
	public java.util.HashMap<Integer, Data> data = new HashMap<Integer, Data>();

	public DataReader(FileOpener fileOpener) {
		this.fileOpener = fileOpener;
	}

	public boolean checkFile() {
		return fileOpener.exist();
	}

	public String readLine() {
		String line = fileOpener.readLine();
		checkLine(line);
		return line;
	}

	public boolean hasMoreDataToRead() {
		return fileOpener.hasNext();
	}

	public int extractIDasInt(String string) {
		return Utility.convertHexToInt(string.substring(0, 6));
	}

	public String extractIDasHex(String string) {
		return string.substring(0, 6);
	}

	private void checkLine(String line) {
		String trimmed = line.trim();
		int words = trimmed.isEmpty() ? 0 : trimmed.split("\\s+").length;
		if (words != 4) {
			throw new IllegalArgumentException("Line: " + line
					+ " has wrong number of arguments");
		}

	}

	public String doBitwiseOperation(String string) {
		char operation = string.charAt(7);
		String bitString1 = string.substring(9, 33);
		String bitString2 = string.substring(34, 58);
		String result = "";

		if (operation == '1') {
			result = Utility.bitwiseAND(bitString1, bitString2);
		} else if (operation == '2') {
			result = Utility.bitwiseOR(bitString1, bitString2);
		}

		return result;

	}

	public Set getSetOfIDs() {
		return data.keySet();

	}

	public Data getData(int id) {
		return data.get(id);
	}

	public Data getData(String hex) {
		return data.get(Utility.convertHexToInt(hex));
	}

	public void ReadAndProcessData() {
		while (hasMoreDataToRead()) {
			String line = readLine();
			String id = extractIDasHex(line);
			String bitString1 = line.substring(9, 33);
			String bitString2 = line.substring(34, 58);
			int operation = Character.getNumericValue(line.charAt(7));
			String result = doBitwiseOperation(line);
			Data dataFromFile = new Data(id, operation, result, bitString1,
					bitString2);

			data.put(Utility.convertHexToInt(id), dataFromFile);
		}
	}

	public static class Data {
		private String IDhex;
		private int IDint;
		private int operation;
		private String result;
		private int resultInt;
		private String bitString1;
		private String bitString2;
		private int bitString1Int;
		private int bitString2Int;

		public Data(String IDhex, int operation, String result,
				String bitString1, String bitString2) {
			this.IDhex = IDhex;
			this.IDint = Utility.convertHexToInt(IDhex);
			this.operation = operation;
			this.result = result;
			this.resultInt = Utility.convertBitToInt(result);
			this.bitString1 = bitString1;
			this.bitString2 = bitString2;
			this.bitString1Int = Utility.convertBitToInt(bitString1);
			this.bitString2Int = Utility.convertBitToInt(bitString2);
		}

		public Data(int IDint, int operation, String result, String bitString1,
				String bitString2) {
			this.IDint = IDint;
			this.IDhex = Utility.convertIntToHex(IDint);
			this.operation = operation;
			this.result = result;
			this.resultInt = Utility.convertBitToInt(result);
			this.bitString1 = bitString1;
			this.bitString2 = bitString2;
			this.bitString1Int = Utility.convertBitToInt(bitString1);
			this.bitString2Int = Utility.convertBitToInt(bitString2);
		}

		public String getIDhex() {
			return IDhex;
		}

		public int getIDint() {
			return IDint;
		}

		public String getResult() {
			return result;
		}

		public int getResultInt() {
			return resultInt;
		}

		public String getBitString1() {
			return bitString1;
		}

		public String getBitString2() {
			return bitString2;
		}

		public int getBitString1Int() {
			return bitString1Int;
		}

		public int getBitString2Int() {
			return bitString2Int;
		}

		public int getOperation() {
			return operation;
		}

		public String getOperationString() {
			if (operation == 1) {
				return "AND";
			} else
				return "OR";
		}

		public String getOriginal() {
			return (this.IDhex + " " + this.operation + " " + this.bitString1
					+ " " + this.bitString2);
		}

	}

}
