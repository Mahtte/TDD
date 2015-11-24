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
	public void ReadLine_StringWithWrongNumberOfArguments_ShouldThrowException() {
		when(mockFileOpener.readLine()).thenReturn("03ac0f 1 110101000000110111001101 001000011110011101001111 aa");
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Line: 03ac0f 1 110101000000110111001101 001000011110011101001111 aa has wrong number of arguments");
		reader.readLine();
		verify(mockFileOpener, times(1)).readLine();	
	}
	
	@Test
	public void DoBitwiseOperation_1_ShouldReturnOperatedBitString() {
		when(mockFileOpener.readLine()).thenReturn("03ac0f 1 110101000000110111001101 001000011110011101001111");
		assertEquals("000000000000010101001101", reader.doBitwiseOperation(reader.readLine()));
		verify(mockFileOpener, times(1)).readLine();
		
	}
	
	@Test
	public void DoBitwiseOperation_2_ShouldReturnOperatedBitString() {
		when(mockFileOpener.readLine()).thenReturn("03ac0f 2 110101000000110111001101 001000011110011101001111");
		assertEquals("111101011110111111001111", reader.doBitwiseOperation(reader.readLine()));
		verify(mockFileOpener, times(1)).readLine();
		
	}

	@Test
	public void GetListOfData_ShouldReturnAStringOfDataIDs() {
		reader.data.put(240655, "");
		assertEquals("[240655]", reader.getListOfData());
	}
	
	@Test
	public void GetListOfData_MapHasManyKeys_ShouldReturnAStringOfDataIDs() {
		reader.data.put(240615, "");
		reader.data.put(240635, "");
		reader.data.put(240665, "");
		
		assertEquals("[240615, 240635, 240665]", reader.getListOfData());
	}
	
	
	@Test
	public void GetData_240635_ShouldReturnDataForThatID() {
		reader.data.put(240615, "h");
		reader.data.put(240635, "a");
		reader.data.put(240665, "b");
		
		assertEquals("a", reader.getData(240635));
	}
	
	@Test
	public void GetData_03ac0f_ShouldReturnDataForThatID() {
		reader.data.put(240615, "h");
		reader.data.put(240635, "a");
		reader.data.put(240655, "b");
		
		assertEquals("b", reader.getData("03ac0f"));
	}
	
	@Test
	public void ProcessData
	
	
	
	
	
	
	
	
	
	
	

}
