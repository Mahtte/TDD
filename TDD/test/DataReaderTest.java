import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;

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
		when(mockFileOpener.readLine()).thenReturn(
				"03ac0f 1 110101000000110111001101 001000011110011101001111");
		assertEquals(
				"03ac0f 1 110101000000110111001101 001000011110011101001111",
				reader.readLine());
		verify(mockFileOpener, times(1)).readLine();
	}

	@Test
	public void HasMoreData_ShouldReturnTrueWhenFileHasMoreData() {
		when(mockFileOpener.hasNext()).thenReturn(true);
		assertTrue(reader.hasMoreDataToRead());
		verify(mockFileOpener, times(1)).hasNext();
	}

	@Test
	public void ReadLine_StringWithWrongNumberOfArguments_ShouldThrowException() {
		when(mockFileOpener.readLine())
				.thenReturn(
						"03ac0f 1 110101000000110111001101 001000011110011101001111 aa");
		exception.expect(IllegalArgumentException.class);
		exception
				.expectMessage("Line: 03ac0f 1 110101000000110111001101 001000011110011101001111 aa has wrong number of arguments");
		reader.readLine();
		verify(mockFileOpener, times(1)).readLine();
	}

	@Test
	public void DoBitwiseOperation_1_ShouldReturnOperatedBitString() {
		assertEquals("000000000000010101001101", reader.doBitwiseOperation(
				"110101000000110111001101", "001000011110011101001111", 1));

	}

	@Test
	public void DoBitwiseOperation_2_ShouldReturnOperatedBitString() {
		assertEquals("111101011110111111001111", reader.doBitwiseOperation(
				"110101000000110111001101", "001000011110011101001111", 2));
	}

	@Test
	public void GetSetOfIDs_ShouldReturnASetOfDataIDs() {
		reader.data.put(240655, null);

		Set<Integer> IDs = new HashSet<Integer>();
		IDs.add(240655);

		assertEquals(IDs, reader.getSetOfIDs());
	}

	@Test
	public void GetSetOfIDs_MapHasManyKeys_ShouldReturnASetOfDataIDs() {
		reader.data.put(240615, null);
		reader.data.put(240635, null);
		reader.data.put(240665, null);

		Set<Integer> IDs = new HashSet<Integer>();
		IDs.add(240615);
		IDs.add(240635);
		IDs.add(240665);

		assertEquals(IDs, reader.getSetOfIDs());
	}

	@Test
	public void GetData_240655_ShouldReturnDataForThatID() {
		DataReader.Data expected = new DataReader.Data(240655, 2,
				"111101011110111111001111", "110101000000110111001101",
				"001000011110011101001111");
		reader.data.put(240655, expected);
		assertEquals(expected, reader.getData(240655));
	}

	@Test
	public void GetData_03ac0f_ShouldReturnDataForThatID() {
		DataReader.Data expected = new DataReader.Data(240655, 2,
				"111101011110111111001111", "110101000000110111001101",
				"001000011110011101001111");
		reader.data.put(240655, expected);
		assertEquals(expected, reader.getData("03ac0f"));
	}

	@Test
	public void GetSetOfIDs_ShouldReturnASetOfIDsAfterReadAndProcessDataMethodHasBeenCalled() {
		when(mockFileOpener.readLine()).thenReturn(
				"03ac0f 2 110101000000110111001101 001000011110011101001111",
				"03ac00 1 110101000000110111001100 001000011110011101001111",
				"00ac0f 2 110101000000110111001110 001000011110011101001111");

		when(mockFileOpener.hasNext()).thenReturn(true, true, true, false);

		reader.ReadAndProcessData();

		Set<Integer> IDs = new HashSet<Integer>();
		IDs.add(240655);
		IDs.add(240640);
		IDs.add(44047);

		assertEquals(IDs, reader.getSetOfIDs());

		verify(mockFileOpener, times(3)).readLine();
		verify(mockFileOpener, times(4)).hasNext();

	}

	@Test
	public void GetDataGetResult_03ac0f_ShouldReturnResultOfThatID() {
		when(mockFileOpener.readLine()).thenReturn(
				"03ac0f 2 110101000000110111001101 001000011110011101001111");
		when(mockFileOpener.hasNext()).thenReturn(true, false);

		reader.ReadAndProcessData();
		DataReader.Data expected = new DataReader.Data(240655, 2,
				"111101011110111111001111", "110101000000110111001101",
				"001000011110011101001111");

		assertEquals(expected.getResult(), reader.getData("03ac0f").getResult());

		verify(mockFileOpener, times(1)).readLine();
		verify(mockFileOpener, times(2)).hasNext();
	}

	@Test
	public void GetDataGetResultInt_03ac0f_ShouldReturnResultOfThatID() {
		when(mockFileOpener.readLine()).thenReturn(
				"03ac0f 2 110101000000110111001101 001000011110011101001111");
		when(mockFileOpener.hasNext()).thenReturn(true, false);

		reader.ReadAndProcessData();
		DataReader.Data expected = new DataReader.Data(240655, 2,
				"111101011110111111001111", "110101000000110111001101",
				"001000011110011101001111");

		assertEquals(expected.getResultInt(), reader.getData("03ac0f")
				.getResultInt());

		verify(mockFileOpener, times(1)).readLine();
		verify(mockFileOpener, times(2)).hasNext();
	}

	@Test
	public void GetDataGetBitString1_03ac0f_ShouldReturnBitString1OfThatID() {
		when(mockFileOpener.readLine()).thenReturn(
				"03ac0f 2 110101000000110111001101 001000011110011101001111");
		when(mockFileOpener.hasNext()).thenReturn(true, false);

		reader.ReadAndProcessData();
		DataReader.Data expected = new DataReader.Data(240655, 2,
				"111101011110111111001111", "110101000000110111001101",
				"001000011110011101001111");

		assertEquals(expected.getBitString1(), reader.getData("03ac0f")
				.getBitString1());

		verify(mockFileOpener, times(1)).readLine();
		verify(mockFileOpener, times(2)).hasNext();
	}

	@Test
	public void GetDataGetBitString2_240655_ShouldReturnBitString2fThatID() {
		when(mockFileOpener.readLine()).thenReturn(
				"03ac0f 2 110101000000110111001101 001000011110011101001111");
		when(mockFileOpener.hasNext()).thenReturn(true, false);

		reader.ReadAndProcessData();
		DataReader.Data expected = new DataReader.Data(240655, 2,
				"111101011110111111001111", "110101000000110111001101",
				"001000011110011101001111");

		assertEquals(expected.getBitString2(), reader.getData(240655)
				.getBitString2());

		verify(mockFileOpener, times(1)).readLine();
		verify(mockFileOpener, times(2)).hasNext();
	}

	@Test
	public void GetDataGetBitString1Int_240655_ShouldReturnBitString1fThatID() {
		when(mockFileOpener.readLine()).thenReturn(
				"03ac0f 2 110101000000110111001101 001000011110011101001111");
		when(mockFileOpener.hasNext()).thenReturn(true, false);

		reader.ReadAndProcessData();
		DataReader.Data expected = new DataReader.Data(240655, 2,
				"111101011110111111001111", "110101000000110111001101",
				"001000011110011101001111");

		assertEquals(expected.getBitString1Int(), reader.getData(240655)
				.getBitString1Int());

		verify(mockFileOpener, times(1)).readLine();
		verify(mockFileOpener, times(2)).hasNext();
	}

	@Test
	public void GetDataGetBitString2Int_240655_ShouldReturnBitString2fThatID() {
		when(mockFileOpener.readLine()).thenReturn(
				"03ac0f 2 110101000000110111001101 001000011110011101001111");
		when(mockFileOpener.hasNext()).thenReturn(true, false);

		reader.ReadAndProcessData();
		DataReader.Data expected = new DataReader.Data(240655, 2,
				"111101011110111111001111", "110101000000110111001101",
				"001000011110011101001111");

		assertEquals(expected.getBitString2Int(), reader.getData(240655)
				.getBitString2Int());

		verify(mockFileOpener, times(1)).readLine();
		verify(mockFileOpener, times(2)).hasNext();
	}

	@Test
	public void GetDataGetOrginal_240655_ShouldReturnOriginalDataString() {
		when(mockFileOpener.readLine()).thenReturn(
				"03ac0f 2 110101000000110111001101 001000011110011101001111");
		when(mockFileOpener.hasNext()).thenReturn(true, false);

		reader.ReadAndProcessData();

		assertEquals(
				"03ac0f 2 110101000000110111001101 001000011110011101001111",
				reader.getData(240655).getOriginal());

		verify(mockFileOpener, times(1)).readLine();
		verify(mockFileOpener, times(2)).hasNext();
	}

	@Test
	public void GetDataGetBitString1_240655_ShouldReturnOriginalBitString() {
		when(mockFileOpener.readLine()).thenReturn(
				"03ac0f 1 110101000000110111001 001000011110011101001");
		when(mockFileOpener.hasNext()).thenReturn(true, false);

		reader.ReadAndProcessData();

		assertEquals("110101000000110111001", reader.getData(240655)
				.getBitString1());

		verify(mockFileOpener, times(1)).readLine();
		verify(mockFileOpener, times(2)).hasNext();
	}

	@Test
	public void ReadAndProcessData_FileHasDuplicateIDs_DuplicatesShouldBeLogged() {
		when(mockFileOpener.readLine()).thenReturn(
				"03ac0f 1 110101000000110111001 001000011110011101001",
				"03a40f 1 110101000010110111001 001000011110011101001",
				"03ac0f 2 110101000000110111001 001000011110011101001");
		when(mockFileOpener.hasNext()).thenReturn(true, true, true, false);
		
		reader.ReadAndProcessData();
		String duplicates = "[03ac0f 2 110101000000110111001 001000011110011101001]";
		
		assertEquals(duplicates, reader.getErrorLogAsString());
		verify(mockFileOpener, times(3)).readLine();
		verify(mockFileOpener, times(4)).hasNext();
			
	}
	

	@Test
	public void ReadAndProcessData_FileHasWrongBitOperation_ItShouldBeLogged() {
		when(mockFileOpener.readLine()).thenReturn(
				"03ac6f 2k 110101000000110111001 001000011110011101001",
				"03a40f 1 110101000010110111001 001000011110011101001",
				"03ac0f 2 1101010000001101110 0010000111100111010");
		when(mockFileOpener.hasNext()).thenReturn(true, true, true, false);
		
		reader.ReadAndProcessData();
		String errorLog = "[03ac6f 2k 110101000000110111001 001000011110011101001]";
		
		assertEquals(errorLog, reader.getErrorLogAsString());
		verify(mockFileOpener, times(3)).readLine();
		verify(mockFileOpener, times(4)).hasNext();
			
	}
	
	

}
