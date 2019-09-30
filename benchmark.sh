#!/bin/bash

numIters=10

echo "================="
echo "=   WHOLE 300   ="
echo "================="
java -classpath src/main/java/ com.brianjopling.mandelbrot.Main -SIZE 300 -THRESHOLD 300 -XLO -1.5 -XHI 1.5 -YLO -1.5 -YHI 1.5 -ITERS $numIters
echo "================="
echo "=   END WHOLE   ="
echo "================="


echo "================="
echo "= SEAHORSE 300  ="
echo "================="
java -classpath src/main/java/ com.brianjopling.mandelbrot.Main -SIZE 400 -THRESHOLD 400 -XLO -0.735 -XHI -0.745 -YLO 0.125 -YHI 0.135 -ITERS $numIters
echo "================="
echo "=  END SEAHORSE ="
echo "================="


echo "================="
echo "=  TURTLE 300   ="
echo "================="
java -classpath src/main/java/ com.brianjopling.mandelbrot.Main -SIZE 300 -THRESHOLD 600 -XLO -0.138 -XHI -0.139 -YLO -0.649 -YHI -0.650 -ITERS $numIters
echo "================="
echo "=  END TURTLE   ="
echo "================="


echo "================="
echo "=  TURTLE 400   ="
echo "================="
java -classpath src/main/java/ com.brianjopling.mandelbrot.Main -SIZE 400 -THRESHOLD 1000 -XLO -0.138 -XHI -0.139 -YLO -0.649 -YHI -0.650 -ITERS $numIters
echo "================="
echo "=  END TURTLE   ="
echo "================="


echo "================="
echo "=  Reflect 4000 ="
echo "================="
java -classpath src/main/java/ com.brianjopling.mandelbrot.Main -SIZE 4000 -THRESHOLD 800 -XLO -0.750 -XHI -0.734 -YLO -0.143 -YHI 0.017 -ITERS $numIters
echo "================="
echo "=  END REFLECT  ="
echo "================="

