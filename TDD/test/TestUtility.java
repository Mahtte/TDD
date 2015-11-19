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
		exception.expectMessage("String length must be less than 25");
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
	
	@Test
	public void ConvertBitToInt_01_ShouldReturn1() {
		assertEquals(1, Utility.convertBitToInt("01"));
	}
	
	@Test
	public void ConvertIntToBit_IntegerIsBiggerThan16777215_ShouldThrowException() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("The number to be converted cant be larger than 16777215");
		Utility.convertIntToBit(16777216);
	}
	
	@Test
	public void ConvertIntToBit_0_ShouldReturn000000000000000000000000() {
		assertEquals("000000000000000000000000", Utility.convertIntToBit(0));
	}
	
	@Test
	public void ConvertIntToBit_1_ShouldReturn000000000000000000000001() {
		assertEquals("000000000000000000000001", Utility.convertIntToBit(1));
	}
	
	@Test
	public void ConvertIntToBit_3_ShouldReturn000000000000000000000011() {
		assertEquals("000000000000000000000011", Utility.convertIntToBit(3));
	}
	
	@Test
	public void ConvertIntToBit_ReturnedStringLengthShouldBe24() {
		assertEquals(24, Utility.convertIntToBit(0).length());
	}
	
	
	
	
	
	

}
