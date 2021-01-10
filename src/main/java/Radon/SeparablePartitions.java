package Radon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A class to compute all separation partition for a given set of points. The separable partitions
 * are encoded by BitSet.
 */
public class SeparablePartitions {

  private final Points points;
  private final int n;
  private final int d;

  private final List<BitSet> encodedSeparablePartitions;

  public SeparablePartitions(Points points) {
    this.points = points;
    n = points.size();
    d = points.dimension();

    assert (d > 0);
    assert (n > d);

    encodedSeparablePartitions = generate();
  }

  public List<BitSet> generate() {

    CombinationIterator it = new CombinationIterator(n, d + 1);
    Points separatorPoints = new Points(d, d + 1);

    Set<BitSet> ans = new HashSet<>();

    while (it.hasNext()) {
      int[] indexes = it.next();
      for (int i = 0; i < d + 1; i++) {
        separatorPoints.set(i, points.get(indexes[i]));
      }

      BitSet bitSet = new BitSet(n);

      for (int i = 0; i < n; i++) {
        boolean flag = GeometricPrimitive.isLeftSide(separatorPoints, points.get(i));
        if (flag) {
          bitSet.setIndex(i);
        }
      }

      for (int i = 0; i < (1 << d); i++) {
        BitSet b = BitSet.getClone(bitSet);

        for (int j = 0; j < d; j++) {
          if ((i & (1 << j)) != 0) {
            b.toggleIndex(indexes[j]);
          }
        }

        if (b.isIndexSet(n - 1)) {
          b.inverse();
        }
        ans.add(b);
      }

    }

    assert (d != 2 || ans.size() == (n * (n - 1)) / 2 + 1);
    return new ArrayList<>(ans);
  }

  public static List<BitSet> generateSeparablePartitions(Points points) {
    SeparablePartitions separablePartitions = new SeparablePartitions(points);
    return separablePartitions.encodedSeparablePartitions;
  }

  public static Set<BitSet> generateNonSeparablePartitions(int n,
      List<BitSet> separablePartitions) {
    Set<BitSet> partitions = new HashSet<>();
    for (long i = 0; i < (1L << (n - 1)); i++) {
      BitSet bitSet = BitSet.getInstance(n, i);
      if (separablePartitions.contains(bitSet)) {
        continue;
      }
      partitions.add(bitSet);
    }
    return partitions;
  }

}
