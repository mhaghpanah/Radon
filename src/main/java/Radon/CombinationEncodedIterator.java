package Radon;

/**
 * An iterator for K-combination of n index represented by BitSet.
 */
public class CombinationEncodedIterator {

  private final int n;
  private final int k;
  private final int[] nums;
  private final BitSet bitSet;
  private boolean hasNext;

  public CombinationEncodedIterator(int n, int k) {
    this.n = n;
    this.k = k;

    if (0 <= k && k <= n) {
      nums = new int[k];
      bitSet = new BitSet(n);
      hasNext = true;
      for (int i = 0; i < k; i++) {
        nums[i] = i;
        bitSet.setIndex(i);
      }
    } else {
      nums = null;
      bitSet = null;
      hasNext = false;
    }
  }

  /**
   * Returns the next element in the combination iterator.
   *
   * @return the next element in the combination iterator
   */
  public BitSet next() {
    BitSet ans = BitSet.getInstance(n, bitSet.getValue());

    int i = k - 1;
    while (i >= 0 && nums[i] == n - k + i) {
      i--;
    }

    if (i >= 0) {
      bitSet.unsetIndex(nums[i]);
      nums[i]++;
      bitSet.setIndex(nums[i]);

      for (int j = i + 1; j < k; j++) {
        bitSet.unsetIndex(nums[j]);
        nums[j] = nums[j - 1] + 1;
        bitSet.setIndex(nums[j]);
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
