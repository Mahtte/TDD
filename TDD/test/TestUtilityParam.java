import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
 
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestUtilityParam {
	@Parameters
	  public static Collection<Object[]> data() {
	      return Arrays.asList(new Object[][] {
	         {"10", 2},
	         {"1001", 9},
	         {"111111111111111111111111", 16777215},
	         {"00110", 6},
	      });
	  }
	 
	  private final String actual;
	  private final int expected;
	 
	  public TestUtilityParam(String actual, int expected){
	      this.actual = actual;
	      this.expected = expected;
	  }
	 
	  @Test
	  public void TestConvertBitToInt(){
	      assertEquals(expected, Utility.convertBitToInt(actual));
	  }
	
}