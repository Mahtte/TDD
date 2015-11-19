import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
 
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestUtilityIntToHexParam {
	@Parameters
	  public static Collection<Object[]> data() {
	      return Arrays.asList(new Object[][] {
	         {"12AB0", 76464},
	         {"5AFE78", 5963384},
	         {"2155", 8533},
	         {"F0F0", 61680},
	      });
	  }
	 
	  private final String actual;
	  private final int expected;
	 
	  public TestUtilityIntToHexParam(String actual, int expected){
	      this.actual = actual;
	      this.expected = expected;
	  }
	 
	  @Test
	  public void TestConvertBitToInt(){
	      assertEquals(expected, Utility.convertHexToInt(actual));
	  }
	
}