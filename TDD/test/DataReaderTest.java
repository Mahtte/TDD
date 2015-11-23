import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class DataReaderTest {
	DataReader reader;
	FileOpener mockFileOpener;
	
	@Before
	public void SetUp() {
		mockFileOpener = mock(FileOpener.class);
		reader = new DataReader(mockFileOpener);
		when(mockFileOpener.exist()).thenReturn(true);


	}

	@Test
	public void CheckFile_ShouldReturnTrueWhenFileExists() {
		assertTrue(reader.checkFile());
		verify(mockFileOpener, times(1)).exist();
	}
	
	@Test
	public void ReadLine_ShouldReturnAStringOfData() {
		assertEquals("03ac0f 1 110101000000110111001101 001000011110011101001111", reader.readLine());
		when(mockFileOpener.readLine()).thenReturn("03ac0f 1 110101000000110111001101 001000011110011101001111");
		verify(mockFileOpener, times(1)).readLine();
		
	}

}
