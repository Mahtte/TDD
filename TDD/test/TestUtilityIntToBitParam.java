import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
 
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestUtilityIntToBitParam {
	@Parameters
	  public static Collection<Object[]> data() {
	      return Arrays.asList(new Object[][] {
	         {"100000000000000000000000", 8388608},
	         {"000000000000000100000110", 262},
	         {"111111111111111111111111", 16777215},
	         {"111111111111111111111110", 16777214},
	         {"000110000000000000000000", 1572864},
	      });
	  }
	 
	  private final String expected;
	  private final int actual;
	 
	  public TestUtilityIntToBitParam(String expected, int actual){
	      this.actual = actual;
	      this.expected = expected;
	  }
	 
	  @Test
	  public void TestConvertBitToInt(){
	      assertEquals(expected, Utility.convertIntToBit(actual));
	  }
	
}