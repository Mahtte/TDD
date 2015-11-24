import java.util.HashMap;
import java.util.Set;

public class DataReader {
	private FileOpener fileOpener;
	public java.util.HashMap<Integer, String> data = new HashMap<Integer, String>();

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

	public int extractID(String string) {
		return Utility.convertHexToInt(string.substring(0, 6));
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

	public String getData(int id) {
		return data.get(id);
	}

	public String getData(String hex) {
		return data.get(Utility.convertHexToInt(hex));
	}

	public void ReadAndProcessData() {
		while(hasMoreDataToRead()) {
			String line = readLine();
			int id = extractID(line);
			data.put(id, line);
		}
	}

}
