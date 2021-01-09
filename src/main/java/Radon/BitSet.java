package Radon;

/**
 * This class implements BitSet. Some primitive methods for manipulation of them is implemented.
 */

public class BitSet {

  int n;
  private long bitSet;

  public BitSet(int n) {
    assert (n <= 63);
    this.n = n;
    bitSet = 0;
  }

  public BitSet(int n, long bitSet) {
    assert (n <= 63);
    this.n = n;
    this.bitSet = bitSet;
  }

  public static BitSet getClone(BitSet b) {
    return new BitSet(b.size(), b.getValue());
  }

  public static BitSet getInstance(int n, long bitSet) {
    return new BitSet(n, bitSet);
  }

  public static int notHammingDistance(BitSet a, BitSet b) {
    long xor = a.getValue() ^ b.getValue();
    return Long.bitCount(xor);
  }

  public static int firstDiffIndex(BitSet a, BitSet b) {
    assert (a.size() == b.size());
    long xor = a.getValue() ^ b.getValue();
    if (xor == 0) {
      return -1;
    }
    int index = 0;
    while ((xor & (1L << index)) == 0) {
      index++;
    }
    return index;
  }

  public int size() {
    return n;
  }

  public long getValue() {
    return bitSet;
  }

  public void setValue(long bitSet) {
    this.bitSet = bitSet;
  }

  public void setIndex(int i) {
    assert (i < size());
    bitSet |= (1L << i);
  }

  public void unsetIndex(int i) {
    assert (i < size());
    bitSet &= ~(1L << i);
  }

  public void intersect(BitSet b) {
    bitSet &= b.getValue();
  }

  public void union(BitSet b) {
    bitSet |= b.getValue();
  }

  public void toggleIndex(int i) {
    assert (i < size());
    bitSet ^= (1L << i);
  }

  public boolean isEmpty() {
    return bitSet == 0;
  }

  public boolean isFull() {
    return bitSet == (1L << n) - 1;
  }

  public boolean isIndexSet(int i) {
    assert (i < size());
    return (bitSet & (1L << i)) != 0;
  }

  public void decrease() {
    assert (!isEmpty());
    bitSet--;
  }

  public void increase() {
    assert (!isFull());
    bitSet++;
  }

  public void inverse() {
    bitSet = ~bitSet & ((1L << n) - 1);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BitSet)) {
      return false;
    }

    BitSet bitSet1 = (BitSet) o;

    return bitSet == bitSet1.bitSet;
  }

  @Override
  public int hashCode() {
    return (int) (bitSet ^ (bitSet >>> 32));
  }

  public String toString() {
    return String.format("%s", Long.toBinaryString((1L << n) | bitSet)).substring(1);
  }

}
