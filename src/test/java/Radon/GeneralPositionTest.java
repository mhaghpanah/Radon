package Radon;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class GeneralPositionTest {

  int d;
  int n;
  Points points;
  boolean expected;

  public GeneralPositionTest(int d, int n, List<long[]> list, boolean expected) {
    this.d = d;
    this.n = n;
    points = new Points(d, n);
    for (int i = 0; i < n; i++) {
      long[] v = list.get(i);
      points.set(i, Point.getInstance(v));
    }
    this.expected = expected;

  }

  @Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][]{
        {2, 3, Arrays.asList(new long[][]{{1, 1}, {-3, 12}, {5, 7}}), true},
        {2, 3, Arrays.asList(new long[][]{{1, 1}, {-3, -3}, {5, 5}}), false},
        {2, 3, Arrays.asList(new long[][]{{1, 1}, {-3, 12}, {1, 1}}), false},
        {2, 4, Arrays.asList(new long[][]{{0, 0}, {1, 1}, {2, 4}, {3, 9}}), true},
    });
  }

  @Test
  public void test() {
    assertEquals(expected, GeometricPrimitive.isInGeneralPosition(points));
  }

}
