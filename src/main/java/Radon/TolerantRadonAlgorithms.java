package Radon;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TolerantRadonAlgorithms {

  public static boolean Algorithm1(Points points, int t) {
    assert (GeometricPrimitive.isInGeneralPosition(points));
    List<BitSet> separablePartitions = SeparablePartitions.generateSeparablePartitions(points);

    int n = points.size();
    Set<BitSet> partitions = SeparablePartitions
        .generateNonSeparablePartitions(n, separablePartitions);

    if (partitions.isEmpty()) {
      System.err.printf("Algorithm 1: Tolerance == %d\n", -1);
      return t == -1;
    }

    int tolerance = 0;
    while (tolerance < t) {
      CombinationEncodedIterator it = new CombinationEncodedIterator(n, tolerance + 1);
      while (it.hasNext()) {
        BitSet c = it.next();
        for (BitSet b : separablePartitions) {
          BitSet a = new BitSet(n);
          for (int j = 0; j < n; j++) {
            if (b.isIndexSet(j) ^ c.isIndexSet(j)) {
              a.setIndex(j);
              if (j == n - 1) {
                a.inverse();
              }
            }
          }

          partitions.remove(a);
        }
      }

      if (!partitions.isEmpty()) {
        tolerance++;
      } else {
        break;
      }

    }

    if (partitions.isEmpty()) {
      System.err.printf("Algorithm 1: Tolerance == %d\n", tolerance);
    } else {
      System.err.printf("Algorithm 1: Tolerance >= %d\n", tolerance);
    }

    return t == tolerance;
  }

  public static boolean Algorithm2(Points points, int t) {
    assert (GeometricPrimitive.isInGeneralPosition(points));
    List<BitSet> currS = SeparablePartitions.generateSeparablePartitions(points);

    int n = points.size();
    Set<BitSet> partitions = SeparablePartitions.generateNonSeparablePartitions(n, currS);

    if (partitions.isEmpty()) {
      System.err.printf("Algorithm 2: Tolerance == %d\n", -1);
      return t == -1;
    }

    int tolerance = 0;
    while (tolerance < t) {
      List<BitSet> nextS = new ArrayList<>();
      for (BitSet b : currS) {

        for (int j = 0; j < n; j++) {
          BitSet bPrime = BitSet.getInstance(n, b.getValue());
          bPrime.toggleIndex(j);

          if (j == n - 1) {
            bPrime.inverse();
          }

          if (partitions.contains(bPrime)) {
            partitions.remove(bPrime);
            nextS.add(bPrime);
          }
        }

      }

      currS = nextS;
      if (!partitions.isEmpty()) {
        tolerance++;
      } else {
        break;
      }

    }

    if (partitions.isEmpty()) {
      System.err.printf("Algorithm 2: Tolerance == %d\n", tolerance);
    } else {
      System.err.printf("Algorithm 2: Tolerance >= %d\n", tolerance);
    }

    return t == tolerance;
  }

  public static boolean Algorithm3(Points points, int t) {
    assert (GeometricPrimitive.isInGeneralPosition(points));
    List<BitSet> separablePartitions = SeparablePartitions.generateSeparablePartitions(points);

    int n = points.size();
    GrayCodeIterator it = new GrayCodeIterator(n);

    int m = separablePartitions.size();
    int[] v = new int[m];

    BitSet b = it.next();
    int index = 0;

    boolean ans = true;
    for (BitSet s : separablePartitions) {
      v[index] = BitSet.notHammingDistance(s, b);
      if (v[index] <= t || v[index] >= n - t) {
        ans = false;
      }
      index++;
    }

    while (!ans && it.hasNext()) {
      BitSet bPrime = it.next();
      int i = BitSet.getFirstDiffIndex(b, bPrime);
      ans = true;

      for (int j = 0; j < m; j++) {
        BitSet s = separablePartitions.get(j);

        if (s.isIndexSet(i) ^ bPrime.isIndexSet(i)) {
          v[j]++;
        } else {
          v[j]--;
        }

        if (v[j] <= t || v[j] >= n - t) {
          ans = false;
        }
      }

      b = bPrime;
    }

    return ans;
  }

  public static boolean solve(Points points, int t) {
//    System.err.printf("Algo 1: %b\n", Algorithm1(points, t) );
//    System.err.printf("Algo 2: %b\n", Algorithm2(points, t) );
//    System.err.printf("Algo 3: %b\n", Algorithm3(points, t) );

    assert (Algorithm1(points, t) == Algorithm2(points, t));
    assert (Algorithm2(points, t) == Algorithm3(points, t));

    return Algorithm2(points, t);
  }

}
