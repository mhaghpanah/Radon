package Radon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BitSetTest {

  @Test
  public void getClone() {
    BitSet bitSet = new BitSet(3, 7);
    BitSet cloneBitSet = BitSet.getClone(bitSet);
    bitSet.unsetIndex(0);
    assertEquals(6, bitSet.getValue());
    assertEquals(7, cloneBitSet.getValue());
  }

  @Test
  public void getInstance() {
    BitSet bitSet = BitSet.getInstance(3, 7);
    assertEquals(7, bitSet.getValue());
  }

  @Test
  public void notHammingDistance() {
    BitSet bitSet0 = new BitSet(3, 7);
    BitSet bitSet1 = new BitSet(3, 5);
    BitSet bitSet2 = new BitSet(3, 4);
    assertEquals(1, BitSet.notHammingDistance(bitSet0, bitSet1));
    assertEquals(1, BitSet.notHammingDistance(bitSet1, bitSet2));
    assertEquals(2, BitSet.notHammingDistance(bitSet0, bitSet2));
  }

  @Test
  public void getFirstDiffIndex() {
    BitSet bitSet0 = new BitSet(3, 7);
    BitSet bitSet1 = new BitSet(3, 5);
    BitSet bitSet2 = new BitSet(3, 4);
    assertEquals(1, BitSet.getFirstDiffIndex(bitSet0, bitSet1));
    assertEquals(0, BitSet.getFirstDiffIndex(bitSet1, bitSet2));
    assertEquals(0, BitSet.getFirstDiffIndex(bitSet0, bitSet2));
  }

  @Test
  public void size() {
    BitSet bitSet = new BitSet(3, 7);
    assertEquals(3, bitSet.size());
  }

  @Test
  public void setValue() {
    BitSet bitSet = new BitSet(3, 4);
    bitSet.setValue(6);
    assertEquals(6, bitSet.getValue());
  }

  @Test
  public void getValue() {
    BitSet bitSet = new BitSet(3, 4);
    assertEquals(4, bitSet.getValue());
  }

  @Test
  public void setIndex() {
    BitSet bitSet = new BitSet(3, 4);
    bitSet.setIndex(2);
    assertEquals(4, bitSet.getValue());
    bitSet.setIndex(1);
    assertEquals(6, bitSet.getValue());
    bitSet.setIndex(1);
    assertEquals(6, bitSet.getValue());
    bitSet.setIndex(0);
    assertEquals(7, bitSet.getValue());
  }

  @Test
  public void unsetIndex() {
    BitSet bitSet = new BitSet(3, 6);
    bitSet.unsetIndex(2);
    assertEquals(2, bitSet.getValue());
    bitSet.unsetIndex(0);
    assertEquals(2, bitSet.getValue());
    bitSet.unsetIndex(2);
    assertEquals(2, bitSet.getValue());
    bitSet.unsetIndex(1);
    assertEquals(0, bitSet.getValue());
  }

  @Test
  public void intersect() {
    BitSet bitSet0 = new BitSet(3, 6);
    BitSet bitSet1 = new BitSet(3, 5);
    bitSet0.intersect(bitSet0);
    assertEquals(6, bitSet0.getValue());
    bitSet0.intersect(bitSet1);
    assertEquals(4, bitSet0.getValue());
  }

  @Test
  public void union() {
    BitSet bitSet0 = new BitSet(3, 6);
    BitSet bitSet1 = new BitSet(3, 5);
    bitSet0.union(bitSet0);
    assertEquals(6, bitSet0.getValue());
    bitSet0.union(bitSet1);
    assertEquals(7, bitSet0.getValue());
  }

  @Test
  public void toggleIndex() {
    BitSet bitSet = new BitSet(3, 6);
    bitSet.toggleIndex(0);
    assertEquals(7, bitSet.getValue());
    bitSet.toggleIndex(0);
    assertEquals(6, bitSet.getValue());
    bitSet.toggleIndex(2);
    assertEquals(2, bitSet.getValue());
  }

  @Test
  public void isEmpty() {
    BitSet bitSet0 = new BitSet(3, 4);
    assertFalse(bitSet0.isEmpty());
    BitSet bitSet1 = new BitSet(3, 0);
    assertTrue(bitSet1.isEmpty());
    BitSet bitSet2 = new BitSet(3);
    assertTrue(bitSet2.isEmpty());
  }

  @Test
  public void isFull() {
    BitSet bitSet0 = new BitSet(3, 4);
    assertFalse(bitSet0.isFull());
    BitSet bitSet1 = new BitSet(3, 0);
    assertFalse(bitSet1.isFull());
    BitSet bitSet2 = new BitSet(3);
    assertFalse(bitSet2.isFull());
    BitSet bitSet3 = new BitSet(3, 7);
    assertTrue(bitSet3.isFull());
  }

  @Test
  public void isIndexSet() {
    BitSet bitSet = new BitSet(3, 5);
    assertTrue(bitSet.isIndexSet(0));
    assertFalse(bitSet.isIndexSet(1));
    assertTrue(bitSet.isIndexSet(2));
  }

  @Test
  public void decrease() {
    BitSet bitSet = new BitSet(3, 5);
    for (int i = 5; i > 0; i--) {
      assertEquals(i, bitSet.getValue());
      bitSet.decrease();
    }
    assertEquals(0, bitSet.getValue());

  }

  @Test
  public void increase() {
    BitSet bitSet = new BitSet(3, 3);
    for (int i = 3; i < 7; i++) {
      assertEquals(i, bitSet.getValue());
      bitSet.increase();
    }
    assertEquals(7, bitSet.getValue());
  }

  @Test
  public void inverse() {
    BitSet bitSet = new BitSet(3, 3);
    bitSet.inverse();
    assertEquals(4, bitSet.getValue());
  }
}
