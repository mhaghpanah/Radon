package Radon;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PointsTest {

  @Test
  public void test() {
    Points points = Points.randomPointGenerator(2, 3);

    assertEquals(2, points.dimension());
    assertEquals(3, points.size());

    Point point0 = points.get(0);
    Point point1 = points.get(1);
    Point point2 = points.get(2);

    points.set(2, point0);
    points.set(1, point1);
    points.set(0, point2);

    assertEquals(point0, points.get(2));
    assertEquals(point1, points.get(1));
    assertEquals(point2, points.get(0));

    assertEquals(2, points.dimension());
    assertEquals(3, points.size());
  }
}
