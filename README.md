# Radon

This is a program for testing Radon partitions.

### How to compile:
javac -d out src/main/java/Radon/*.java

### How to run:
java -cp out/ Radon.Main

### How to make jar file:
javac -d out src/main/java/Radon/*.java
jar cvf Radon.jar -C out Radon/

### How to run the jar file:
java -cp Radon.jar Radon.Main
