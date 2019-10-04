#!/bin/bash

numIters=10
numThreads=2

echo "================="
echo "=   WHOLE 300   ="
echo "================="
java -classpath src/main/java/ com.brianjopling.mandelbrot.Main -SIZE 300 \
     -THRESHOLD 300 -XLO -1.5 -XHI 1.5 -YLO -1.5 -YHI 1.5 -ITERS $numIters \
     -NUMTHREADS $numThreads -SUFFIX whole300
echo "================="
echo "=   END WHOLE   ="
echo "================="


echo "================="
echo "= SEAHORSE 1600 ="
echo "================="
java -classpath src/main/java/ com.brianjopling.mandelbrot.Main -SIZE 1600 \
     -THRESHOLD 400 -XLO -0.735 -XHI -0.745 -YLO 0.125 -YHI 0.135 \
     -ITERS $numIters -NUMTHREADS $numThreads -SUFFIX seahorse1600
echo "================="
echo "=  END SEAHORSE ="
echo "================="


echo "================="
echo "=  TURTLE 400   ="
echo "================="
java -classpath src/main/java/ com.brianjopling.mandelbrot.Main -SIZE 400 \
     -THRESHOLD 1000 -XLO -0.138 -XHI -0.139 -YLO -0.649 -YHI -0.650 \
     -ITERS $numIters -NUMTHREADS $numThreads -SUFFIX turtle400
echo "================="
echo "=  END TURTLE   ="
echo "================="


echo "================="
echo "=  TURTLE 2000  ="
echo "================="
java -classpath src/main/java/ com.brianjopling.mandelbrot.Main -SIZE 2000 \
     -THRESHOLD 20000 -XLO -0.13883 -XHI -0.13887 -YLO -0.64933 -YHI -0.64937 \
     -ITERS $numIters -NUMTHREADS $numThreads -SUFFIX turtle2000
echo "================="
echo "=  END TURTLE   ="
echo "================="


echo "================="
echo "=  DRAGON 4000 ="
echo "================="
java -classpath src/main/java/ com.brianjopling.mandelbrot.Main -SIZE 4000 \
     -THRESHOLD 250 -XLO -0.840716 -XHI -0.840732 -YLO 0.22420 -YHI 0.224216 \
     -ITERS $numIters -NUMTHREADS $numThreads -SUFFIX dragon4000
echo "================="
echo "=  END DRAGON  ="
echo "================="


echo "================="
echo "=  FROSTY 3000  ="
echo "================="
java -classpath src/main/java/ com.brianjopling.mandelbrot.Main -SIZE 3000 \
     -THRESHOLD 10000 -XLO -1.236 -XHI -1.191 -YLO 0.14 -YHI 0.172 \
     -ITERS $numIters -NUMTHREADS $numThreads -SUFFIX frosty3000
echo "================="
echo "=  END FROSTY   ="
echo "================="


echo "================="
echo "=  OCTOPI 5000  ="
echo "================="
java -classpath src/main/java/ com.brianjopling.mandelbrot.Main -SIZE 5000 \
     -THRESHOLD 300 -XLO -0.79118456818291 -XHI -0.7911787441910218 \
     -YLO 0.14290239912741728 -YHI 0.142906755421732881 -ITERS $numIters \
     -NUMTHREADS $numThreads -SUFFIX octopi5000
echo "================="
echo "=  END OCTOPI   ="
echo "================="
