# Radon

This is a program for testing Radon partitions with tolerance.
The program reads a set of point in the plane from '/src/main/resourse/RadonPoint.txt' and output the maximum tolernce of a Radon partiton of the given points. 

### How to compile:
javac -d out src/main/java/Radon/*.java

### How to run:
java -cp out/ Radon.Main

### How to make jar file:
javac -d out src/main/java/Radon/*.java

jar cvf Radon.jar -C out Radon/

### How to run the jar file:
java -cp Radon.jar Radon.Main

This program is written by Mohammadreza Haghpanah (Mohammadreza.Haghpanah@utdallas.edu) under supervision of Dr. Sergey Bereg (besp@utdallas.edu) as part of NSF award CCF-1718994 project.
