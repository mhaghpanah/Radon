package Radon;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CombinationEncodedIteratorTest {

  int n;
  int k;
  int sz;
  public CombinationEncodedIteratorTest(int n, int k, int sz) {
    this.n = n;
    this.k = k;
    this.sz = sz;
  }

  @Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][]{
        {1, 1, 1},
        {1, 0, 1},
        {5, 2, 10},
        {5, 0, 1},
        {5, 5, 1},
        {0, 1, 0},
        {7, 9, 0},
        {-2, 0, 0},
        {2, -1, 0}
    });
  }

  @Test
  public void test() {
    CombinationEncodedIterator it = new CombinationEncodedIterator(n, k);
    Set<Long> set = new HashSet<>();
    while (it.hasNext()) {
      BitSet bitSet = it.next();
      long v = bitSet.getValue();
      assertEquals(k, Long.bitCount(v));
      set.add(v);
    }
    assertEquals(sz, set.size());
  }

}
