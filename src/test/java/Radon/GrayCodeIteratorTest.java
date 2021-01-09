package Radon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class GrayCodeIteratorTest {

  int n;

  public GrayCodeIteratorTest(int n) {
    this.n = n;
  }

  @Parameters
  public static Collection<Object> data() {
    return Arrays.asList(new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
  }

  @Test
  public void test0() {
    GrayCodeIterator it = new GrayCodeIterator(n);
    Set<Long> set = new HashSet<>();
    long prev = -1;
    while (it.hasNext()) {
      BitSet bitSet = it.next();
      long v = bitSet.getValue();
      assertFalse(set.contains(v));
      assertTrue(v < (1L << n));
      set.add(v);
      if (prev != -1) {
        assertEquals(1, Long.bitCount(v ^ prev));
      }

      prev = v;
    }

    for (long i = 0; i < (1L << n); i++) {
      assertTrue(set.contains(i));
    }
  }

  @Test
  public void test1() {
    List<Long> list = new ArrayList<>();
    for (long i = 0; i < (1L << n); i++) {
      list.add(GrayCodeIterator.NumberToGrayCode(i));
    }

    for (int i = 0; i < (1 << n); i++) {
      long gc = list.get(i);
      assertEquals(i, GrayCodeIterator.GrayCodeToNumber(gc));
    }
  }

}
