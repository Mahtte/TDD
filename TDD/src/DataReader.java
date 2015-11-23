public class DataReader {
	private FileOpener fileOpener;

	public DataReader(FileOpener fileOpener) {
		this.fileOpener = fileOpener;
	}

	public boolean checkFile() {
		return fileOpener.exist();
	}

	public String readLine() {
		return fileOpener.readLine();
	}

	public boolean hasMoreDataToRead() {
		return fileOpener.hasNext();
	}

	public int extractID(String string) {
		return Utility.convertHexToInt(string.substring(0, 6));
	}

}
