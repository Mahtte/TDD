
public class DataReader {
	private FileOpener fileOpener;
	
	public DataReader(FileOpener fileOpener) {
		this.fileOpener = fileOpener;
	}

	public boolean checkFile(String filePath) {
		return fileOpener.exist(filePath);
	}


}
