package Radon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;

public class CombinationIteratorTest {

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

  public void helper(int n, int k, int sz) {
    CombinationIterator it = new CombinationIterator(n, k);
    Set<List<Integer>> set = new HashSet<>();
    while (it.hasNext()) {
      int[] nums = it.next();
      assertEquals(k, nums.length);
      assertTrue(isUnique(nums));
      assertTrue(isSorted(nums));
      set.add(toList(nums));
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
