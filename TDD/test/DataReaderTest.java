import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DataReaderTest {
	DataReader reader;
	FileOpener mockFileOpener;
	
	@Before
	public void SetUp() {
		mockFileOpener = mock(FileOpener.class);
		reader = new DataReader(mockFileOpener);
		when(mockFileOpener.exist()).thenReturn(true);


	}
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void CheckFile_ShouldReturnTrueWhenFileExists() {
		assertTrue(reader.checkFile());
		verify(mockFileOpener, times(1)).exist();
	}
	
	@Test
	public void ReadLine_ShouldReturnAStringOfData() {
		when(mockFileOpener.readLine()).thenReturn("03ac0f 1 110101000000110111001101 001000011110011101001111");
		assertEquals("03ac0f 1 110101000000110111001101 001000011110011101001111", reader.readLine());
		verify(mockFileOpener, times(1)).readLine();
	}
	
	@Test
	public void HasMoreData_ShouldReturnTrueWhenFileHasMoreData() {
		when(mockFileOpener.hasNext()).thenReturn(true);
		assertTrue(reader.hasMoreDataToRead());
		verify(mockFileOpener, times(1)).hasNext();
	}
	
	@Test
	public void ExtractID_StringOfDataWhereIDIs03ac0f_ShouldReturn240655() {
		assertEquals(240655 ,reader.extractID("03ac0f 1 110101000000110111001101 001000011110011101001111"));
	}
	
	@Test
	public void readLine_StringWithWrongNumberOfArguments_ShouldThrowException() {
		when(mockFileOpener.readLine()).thenReturn("03ac0f 1 110101000000110111001101 001000011110011101001111 aa");
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Line: 03ac0f 1 110101000000110111001101 001000011110011101001111 aa has wrong number of arguments");
		reader.readLine();
		verify(mockFileOpener, times(1)).readLine();
		
	}
	
	
	
	
	

}
