import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;


public class DataReaderTest {

	@Test
	public void CheckFile_ReturnsTrueWhenFileExists() {
		FileOpener mockFileOpener = mock(FileOpener.class);
		DataReader reader = new DataReader(mockFileOpener);
		
		when(mockFileOpener.exist("data.txt")).thenReturn(true);
		assertTrue(reader.checkFile("data.txt"));
		verify(mockFileOpener, times(1)).exist("data.txt");
	}

}
