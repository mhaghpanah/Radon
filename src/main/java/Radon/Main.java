package Radon;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws FileNotFoundException {

    String filename = "RadonPoints.txt";
    String pathname = String.join(File.separator,
        System.getProperty("user.dir"), "src", "main", "resources", filename);
    System.err.printf("Reading test file %s\n", pathname);

    File file = new File(pathname);
    Scanner in = new Scanner(file);

    int testNum = in.nextInt();
    System.err.printf("Number of tests: %d\n", testNum);
    while (testNum-- > 0) {
      System.err.println("----------------------------");
      Points points = new Points(in);
      System.err.println(points);
      System.err.printf("Points size: %d\n", points.size());
      if (GeometricPrimitive.isInGeneralPosition(points)) {
        TolerantRadonAlgorithms.solve(points, points.size());
      } else {
        System.err.printf("The given points are not in general position.\n");
      }
    }

  }

}
