package Radon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
public class TernaryIteratorTest {

  int n;

  public TernaryIteratorTest(int n) {
    this.n = n;
  }

  @Parameters
  public static Collection<Object> data() {
    return Arrays.asList(new Object[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
  }

  public List<Integer> toList(int[] nums) {
    List<Integer> ans = new ArrayList<>();
    for (int a : nums) {
      ans.add(a);
    }
    return ans;
  }

  @Test
  public void test() {
    int sz = 1;
    for (int i = 0; i < n; i++) {
      sz *= 3;
    }

    TernaryIterator it = new TernaryIterator(n);
    Set<List<Integer>> set = new HashSet<>();
    while (it.hasNext()) {
      int[] nums = it.next();
      assertEquals(n, nums.length);
      List<Integer> list = toList(nums);
      assertFalse(set.contains(list));
      set.add(list);
    }
    assertEquals(sz, set.size());

  }
}
