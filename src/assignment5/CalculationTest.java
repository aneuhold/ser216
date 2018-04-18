package assignment5;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculationTest {
  
  @Test
  public void testOddOrPos() {
    int[] testArray = {-3, -2, 0, 1, 4};
    assertEquals(3, Calculation.oddOrPos(testArray));
  }
}