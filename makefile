default: all

all:
	javac src/main/java/com/brianjopling/mandelbrot/Main.java src/main/java/com/brianjopling/mandelbrot/Mandelbrot.java src/main/java/com/brianjopling/mandelbrot/MandelThread.java

zip:
	zip -r cs361-brj33-assignment1-part2.zip src/ benchmark.sh brj33-*.png makefile README

clean:
	rm src/main/java/com/brianjopling/mandelbrot/*.class
