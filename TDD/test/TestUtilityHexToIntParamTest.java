import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
 
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestUtilityHexToIntParamTest {
	@Parameters
	  public static Collection<Object[]> data() {
	      return Arrays.asList(new Object[][] {
	         {"aa89", 43657},
	         {"1001AB", 1049003},
	         {"FFFFFF", 16777215},
	         {"000000", 0},
	      });
	  }
	 
	  private final String actual;
	  private final int expected;
	 
	  public TestUtilityHexToIntParamTest(String actual, int expected){
	      this.actual = actual;
	      this.expected = expected;
	  }
	 
	  @Test
	  public void TestConvertBitToInt(){
	      assertEquals(expected, Utility.convertHexToInt(actual));
	  }
	
}