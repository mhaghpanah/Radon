package Radon;

import static org.junit.Assert.assertEquals;

public class DeterminantTest {

  @org.junit.Test
  public void compute() {
    assertEquals(-5L, Determinant.compute(new long[][]{{3, 4}, {2, 1}}));
    assertEquals(15947576L, Determinant.compute(new long[][]{{545, 2454}, {-5489, 4546}}));
    assertEquals(291133677L, Determinant.compute(new long[][]{{1235, 25697}, {-11111, 4546}}));
    assertEquals(0L, Determinant.compute(new long[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
    assertEquals(-12L, Determinant.compute(new long[][]{{1, 2, 3}, {3, 2, 1}, {2, 1, 3}}));
    assertEquals(22451L, Determinant.compute(
        new long[][]{{3, 4, 9, -9, 0}, {-8, -7, 0, 1, 2}, {2, -6, 5, -2, 1}, {1, 1, 2, 7, 0},
            {7, 2, -8, 3, 2}}));
  }

}
