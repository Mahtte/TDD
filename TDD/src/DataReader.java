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


	private void checkLine(String line) {
		String trimmed = line.trim();
		int words = trimmed.isEmpty() ? 0 : trimmed.split("\\s+").length;
		if (words != 4) {
			throw new IllegalArgumentException("Line: " + line
					+ " has wrong number of arguments");
		}

	}

	public String doBitwiseOperation(String bitString1, String bitString2, int operation) {
		String result = "";
		if (operation == 1) {
			result = Utility.bitwiseAND(bitString1, bitString2);
		} else if (operation == 2) {
			result = Utility.bitwiseOR(bitString1, bitString2);
		}

		return result;

	}

	public Set<Integer> getSetOfIDs() {
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
			String[] arguments = new String[4];
			String line = readLine();
			String trimmed = line.trim();
			arguments = trimmed.split("\\s+");
			String id = arguments[0];
			String bitString1 = arguments[2];
			String bitString2 = arguments[3];
			int operation = Integer.parseInt(arguments[1]);
			String result = doBitwiseOperation(bitString1, bitString2, operation);
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
