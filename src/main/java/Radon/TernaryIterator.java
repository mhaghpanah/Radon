package Radon;

/**
 * A Ternary Iterator. It is implemented by iterating of each submask of a given mask.
 */
public class TernaryIterator {

  private final int n;
  private final BitSet m;
  private final BitSet s;
  private boolean hasNext;

  public TernaryIterator(int n) {
    this.n = n;
    m = new BitSet(n);
    s = new BitSet(n);
    hasNext = true;
  }

  public int[] next() {
    assert (hasNext());

    int[] ans = new int[n];
    for (int i = 0; i < n; i++) {
      if (!m.isIndexSet(i)) {
        ans[i] = 0;
      } else if (s.isIndexSet(i)) {
        ans[i] = 1;
      } else {
        ans[i] = 2;
      }
    }

    if (s.isEmpty() && m.isFull()) {
      hasNext = false;
    } else if (s.isEmpty()) {
      m.increase();
      s.setValue(m.getValue());
    } else {
      s.decrease();
      s.intersect(m);
    }

    return ans;
  }

  public boolean hasNext() {
    return hasNext;
  }
}
