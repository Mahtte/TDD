
public class DataReader {
	private FileOpener fileOpener;
	
	public DataReader(FileOpener fileOpener) {
		this.fileOpener = fileOpener;
	}

	public boolean checkFile() {
		return fileOpener.exist();
	}

	public String readLine() {
		return null;
	}


}
