package Radon;

import java.util.Random;
import java.util.Scanner;

/**
 * The Points class representing an array of points.
 */
public class Points {

  private final Point[] points;
  private final int n;
  private final int d;

  public Points(int d, int n) {
    this.d = d;
    this.n = n;
    points = new Point[n];
  }

  public Points(Scanner in) {
    int d = in.nextInt();
    int n = in.nextInt();
    this.d = d;
    this.n = n;
    points = new Point[n];
    for (int i = 0; i < n; i++) {
      Point p = new Point(d, in);
      set(i, p);
    }
  }

  public static Points randomPointGenerator(int d, int n) {
    Random random = new Random();
    return randomPointGenerator(d, n, random);
  }

  private static boolean isConsistent(Points points, int size, Point p) {
    assert (points.dimension() == p.dimension());
    int d = points.dimension();
    CombinationIterator it = new CombinationIterator(size, d);
    while (it.hasNext()) {
      int[] indexes = it.next();
      Points subset = new Points(d, d + 1);
      for (int i = 0; i < d; i++)
        subset.set(i, points.get(indexes[i]));
      subset.set(d, p);
      if (GeometricPrimitive.isAffinelyDependent(subset))
        return false;
    }
    return true;
  }

  public static Points randomPointGenerator(int d, int n, Random random) {
    Points points = new Points(d, n);
    int bound = 1_000_000;
    int size = 0;
    while (size < n) {
      Point p = new Point(d, random, bound);
      if (isConsistent(points, size, p)) {
        points.set(size++, p);
      }
    }
    assert (GeometricPrimitive.isInGeneralPosition(points));
    return points;
  }

  public Point get(int i) {
    return points[i];
  }

  public void set(int i, Point p) {
    assert (dimension() == p.dimension());
    points[i] = p;
  }

  public int size() {
    return n;
  }

  public int dimension() {
    return d;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    for (Point p : points) {
      if (sb.length() > 1) {
        sb.append(", ");
      }
      sb.append(p);
    }
    sb.append("}");
    return sb.toString();
  }

}
