package Radon;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PointTest {

  @Test
  public void getInstance() {
    Point point = Point.getInstance(new long[]{1, 2, 3});
    assertEquals(3, point.dimension());
    assertEquals(1, point.getIndex(0));
    assertEquals(2, point.getIndex(1));
    assertEquals(3, point.getIndex(2));
  }

  @Test
  public void dimension() {
    Point point = new Point(new long[]{0, 0, 0});
    assertEquals(3, point.dimension());

    point = new Point(3);
    assertEquals(3, point.dimension());
  }

  @Test
  public void getIndex() {
    Point point = new Point(new long[]{1, 2, 3});
    assertEquals(1, point.getIndex(0));
    assertEquals(2, point.getIndex(1));
    assertEquals(3, point.getIndex(2));
  }

  @Test
  public void setIndex() {
    Point point = new Point(new long[]{1, 2, 3});
    assertEquals(1, point.getIndex(0));
    point.setIndex(0, 0);
    assertEquals(0, point.getIndex(0));
  }
}
