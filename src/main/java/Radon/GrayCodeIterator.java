package Radon;

/**
 * An iterator for Gray code.
 */
public class GrayCodeIterator {

  private final int n;
  private long index;

  public GrayCodeIterator(int n) {
    assert (n > 0);
    this.n = n;
    index = 0;
  }

  public static long NumberToGrayCode(long num) {
    return (num ^ (num >> 1));
  }

  public static long GrayCodeToNumber(long grayCode) {
    long ans = 0;
    while (grayCode > 0) {
      ans ^= grayCode;
      grayCode >>= 1;
    }
    return ans;
  }

  public BitSet next() {
    assert (hasNext());

    long ans = NumberToGrayCode(index);
    index++;
    return BitSet.getInstance(n, ans);
  }

  public boolean hasNext() {
    return index < (1L << n);
  }

}
