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
		exception
				.expectMessage("The number to be converted cant be larger than 16777215");
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

	@Test
	public void ConvertHexToInt_StringIsLongerThanSix_ShouldThrowException() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("String length must be less than 7");
		Utility.convertHexToInt("AA33333");
	}

	@Test
	public void ConvertHexToInt_StringContainsIllegalCharacters_ShouldThrowException() {
		exception.expect(IllegalArgumentException.class);
		exception
				.expectMessage("String can only contain characters: 01234567890ABCDEF / abcdef");
		Utility.convertHexToInt("AA333k");
	}

	@Test
	public void ConvertHexToInt_EmptyString_ShouldReturn0() {
		assertEquals(0, Utility.convertHexToInt(""));
	}

	@Test
	public void ConvertHexToInt_0_ShouldReturn0() {
		assertEquals(0, Utility.convertHexToInt("0"));
	}

	@Test
	public void ConvertHexToInt_1_ShouldReturn1() {
		assertEquals(1, Utility.convertHexToInt("1"));
	}

	@Test
	public void ConvertHexToInt_A_ShouldReturn10() {
		assertEquals(10, Utility.convertHexToInt("A"));
	}

	@Test
	public void ConvertHexToInt_C_ShouldReturn12() {
		assertEquals(12, Utility.convertHexToInt("C"));
	}

	@Test
	public void ConvertHexToInt_F_ShouldReturn15() {
		assertEquals(15, Utility.convertHexToInt("F"));
	}
	
	@Test
	public void ConvertIntToHex_IntegerIsBiggerThan16777215_ShouldThrowException() {
		exception.expect(IllegalArgumentException.class);
		exception
				.expectMessage("The number to be converted cant be larger than 16777215");
		Utility.convertIntToHex(16777216);
	}
	
	@Test
	public void ConvertIntToHex_0_ShouldReturn0() {
		assertEquals("0", Utility.convertIntToHex(0));
	}
	
	
	@Test
	public void ConvertIntToHex_1_ShouldReturn1() {
		assertEquals("1", Utility.convertIntToHex(1));
	}
	
	@Test
	public void ConvertIntToHex_10_ShouldReturnA() {
		assertEquals("A", Utility.convertIntToHex(10));
	}
	
	@Test
	public void ConvertIntToHex_15_ShouldReturnF() {
		assertEquals("F", Utility.convertIntToHex(15));
	}
	
	@Test
	public void ConvertIntToHex_12_ShouldReturnC() {
		assertEquals("C", Utility.convertIntToHex(12));
	}
	
	@Test
	public void ConvertIntToHex_16_ShouldReturn10() {
		assertEquals("10", Utility.convertIntToHex(16));
	}
	
	
	@Test
	public void ConvertIntToHex_17_ShouldReturn11() {
		assertEquals("11", Utility.convertIntToHex(17));
	}
	
	@Test
	public void ConvertIntToHex_20_ShouldReturn14() {
		assertEquals("14", Utility.convertIntToHex(20));
	}
	
	@Test
	public void ConvertIntToHex_26_ShouldReturn1A() {
		assertEquals("1A", Utility.convertIntToHex(26));
	}
	
	@Test
	public void ConvertIntToHex_35_ShouldReturn23() {
		assertEquals("23", Utility.convertIntToHex(35));
	}
	
	@Test
	public void ConvertIntToHex_100_ShouldReturn64() {
		assertEquals("64", Utility.convertIntToHex(100));
	}
	
	@Test
	public void ConvertIntToHex_16777215_ShouldReturnFFFFFF() {
		assertEquals("FFFFFF", Utility.convertIntToHex(16777215));
	}
	
	@Test
	public void ConvertIntToHex_61459_ShouldReturnF013 () {
		assertEquals("F013", Utility.convertIntToHex(61459));
	}
	
	

}
