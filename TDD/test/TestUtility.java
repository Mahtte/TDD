import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class TestUtility {
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void ConvertBitToInt_EmptyString_ShouldReturnZero() {
		assertEquals(0, Utility.convertBitToInt(""));
	}
	
	@Test
	public void ConvertBitToInt_StringLengtIsMoreThan24_ShouldThrowException() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("String length must be less than 24");
		Utility.convertBitToInt("00000000000000000000000000000");
	}
	
	@Test
	public void ConvertBitToInt_StringLengtIs24_ShouldNotThrowException() {
		Utility.convertBitToInt("000000000000000000000000");
	}
	
	@Test
	public void ConvertBitToInt_StringContainsALetter_ShouldThrowException() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("String can only contain numbers 0 - 1");
		Utility.convertBitToInt("01234567890k");
	}
	
	@Test
	public void ConvertBitToInt_StringContainsANumberBiggerThanOne_ShouldThrowException() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("String can only contain numbers 0 - 1");
		Utility.convertBitToInt("2");
	}
	
	@Test
	public void ConvertBitToInt_0_ShouldReturn0() {
		assertEquals(0, Utility.convertBitToInt("0"));
	}
	
	@Test
	public void ConvertBitToInt_1_ShouldReturn1() {
		assertEquals(1, Utility.convertBitToInt("1"));
	}
	
	
	
	
	
	
	

}
