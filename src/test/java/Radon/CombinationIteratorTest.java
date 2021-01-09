package Radon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
public class CombinationIteratorTest {

  int n;
  int k;
  int sz;

  public CombinationIteratorTest(int n, int k, int sz) {
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

  public boolean isUnique(int[] nums) {
    Set<Integer> set = new HashSet<>();
    for (int a : nums) {
      if (set.contains(a)) {
        return false;
      }
      set.add(a);
    }
    return true;
  }

  public boolean isSorted(int[] nums) {
    int n = nums.length;
    for (int i = 0; i < n - 1; i++) {
      if (nums[i] >= nums[i + 1]) {
        return false;
      }
    }
    return true;
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
    CombinationIterator it = new CombinationIterator(n, k);
    Set<List<Integer>> set = new HashSet<>();
    while (it.hasNext()) {
      int[] nums = it.next();
      assertEquals(k, nums.length);
      assertTrue(isUnique(nums));
      assertTrue(isSorted(nums));
      List<Integer> list = toList(nums);
      assertFalse(set.contains(list));
      set.add(list);
    }
    assertEquals(sz, set.size());
  }

}
