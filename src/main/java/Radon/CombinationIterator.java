package Radon;

import java.util.Arrays;

/**
 * An iterator for K-combinations of n index represented by int[].
 */
public class CombinationIterator {

  private final int n;
  private final int k;
  private final int[] nums;
  private boolean hasNext;

  public CombinationIterator(int n, int k) {
    this.n = n;
    this.k = k;

    if (0 <= k && k <= n) {
      nums = new int[k];
      hasNext = true;
      for (int i = 0; i < k; i++) {
        nums[i] = i;
      }
    } else {
      nums = null;
      hasNext = false;
    }
  }

  /**
   * Returns true if there are more combination.
   *
   * @return true if there are more combination
   */
  public int[] next() {
    assert (hasNext());
    assert (nums != null);

    int[] ans = Arrays.copyOf(nums, nums.length);
    int i = k - 1;
    while (i >= 0 && nums[i] == n - k + i) {
      i--;
    }

    if (i >= 0) {
      nums[i]++;
      for (int j = i + 1; j < k; j++) {
        nums[j] = nums[j - 1] + 1;
      }
    } else {
      hasNext = false;
    }
    return ans;
  }

  /**
   * Returns true if there are more combination.
   *
   * @return true if there are more combination
   */
  public boolean hasNext() {
    return hasNext;
  }

}
