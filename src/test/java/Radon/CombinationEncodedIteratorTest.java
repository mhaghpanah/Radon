package Radon;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

public class CombinationEncodedIteratorTest {

  public void helper(int n, int k, int sz) {
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

  @Test
  public void test() {
    helper(1, 1, 1);
    helper(1, 0, 1);
    helper(5, 2, 10);
    helper(5, 0, 1);
    helper(5, 5, 1);
    helper(0, 1, 0);
    helper(7, 9, 0);
    helper(-2, 0, 0);
    helper(-2, -1, 0);
  }

}
