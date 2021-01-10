package Radon;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * The Point class representing a location in a d-dimensional coordinate Euclidean space.
 */
public class Point {
  private final int d;
  private final long[] v;

  public Point(int d) {
    this.d = d;
    this.v = new long[d];
  }

  public Point(long[] v) {
    this.d = v.length;
    this.v = v;
  }

  public Point(int d, Scanner in) {
    this(d);
    for (int i = 0; i < d; i++) { setIndex(i, in.nextLong()); }
  }

  public Point(int d, Random random, int bound) {
    this(d);
    for (int i = 0; i < d; i++) { setIndex(i, random.nextInt(bound)); }
  }

  public static Point getInstance(long[] v) { return new Point(v); }

  public int dimension() { return d; }

  public long getIndex(int index) { return v[index]; }

  public void setIndex(int index, long value) { v[index] = value; }

  @Override
  public String toString() {
    return "Point{" +
        "d=" + d +
        ", v=" + Arrays.toString(v) +
        '}';
  }

}
