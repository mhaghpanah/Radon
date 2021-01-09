package Radon;

public class GeometricPrimitive {

  public static boolean isLeftSide(Points points, Point p) {
    assert (points.dimension() == p.dimension());
    int d = p.dimension();

    long[][] matrix = new long[d][d];

    for (int i = 0; i < d; i++) {
      Point p0 = points.get(i);
      Point p1 = i == d - 1 ? p : points.get(i + 1);
      for (int j = 0; j < d; j++) {
        matrix[i][j] = p1.getIndex(j) - p0.getIndex(j);
      }
    }

    long val = Determinant.compute(matrix);
    return val >= 0;
  }

  /**
   * Check if points are affinely dependent.
   * The size of points is one more than its dimension.
   *
   * v_0, v_1, ..., v_k are affinely independent
   * if and only if
   * v_1 - v_0, ..., v_k - v_0 are linearly independent
   * @param points A set of points P
   * @return true if P is affinely dependent
   */
  public static boolean isAffinelyDependent(Points points) {
    assert (points.size() == points.dimension() + 1);
    int d = points.dimension();

    long[][] matrix = new long[d + 1][d + 1];

    for (int i = 0; i < d + 1; i++) {
      for (int j = 0; j < d + 1; j++) {
        matrix[i][j] = j < d ? points.get(i).getIndex(j) : 1;
      }
    }

    long val = Determinant.compute(matrix);
    return val == 0;
  }

  /***
   * Check if a given set is in general position.
   * Note: the methode assume size of points is larger then its dimension.
   *
   * Let P be a set of points \subset \R^d.
   * P is in general linear position if and only if
   * each k-tuple of points from P, k = 2, ..., d + 1, is affinely independent.
   * @param points A set of points P
   * @return true if P is in general position
   */
  public static boolean isInGeneralPosition(Points points) {
    int n = points.size();
    int d = points.dimension();
    CombinationIterator it = new CombinationIterator(n, d + 1);
    while (it.hasNext()) {
      int[] indexes = it.next();
      Points subset = new Points(d, d + 1);
      for (int i = 0; i < d + 1; i++) {
        subset.set(i, points.get(indexes[i]));
      }
      if (isAffinelyDependent(subset)) {
        return false;
      }
    }
    return true;
  }

}
