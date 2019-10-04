package com.brianjopling.mandelbrot;

import java.util.ArrayList;
import java.util.List;

public class Main {

    /* Initialized via parseArgs */
    private static int numThreads = 1;
    private static int size;
    private static int threshold;
    private static double xlo;
    private static double xhi;
    private static double ylo;
    private static double yhi;
    private static int iters = 1;
    private static String fileSuffix = "";


    public static void main(String[] args) {

        parseArgs(args);

        long[] runtimes = new long[iters];

        Mandelbrot mb = new Mandelbrot(size, fileSuffix);

        for (int i = 0; i < iters; i++) {

            long startTime = System.currentTimeMillis();

            performThreadedComputation(mb);

            long endTime = System.currentTimeMillis();
            long totalRuntime = endTime - startTime;
            log("Mandelbrot Computation Runtime: " + totalRuntime + "ms");
            runtimes[i] = totalRuntime;

        }
        if (iters > 1) {
            float avg = computeAverageRuntime(runtimes);
            log("Average runtime across " + iters + " iterations using " +
                    numThreads + " threads: " + avg + "ms");
        }
        mb.draw();
        mb.output();
    }

    private static float computeAverageRuntime(long[] runtimes) {
        float mean = 0;
        for (long l : runtimes){
            mean += l;
        }
        mean /= runtimes.length;
        return mean;
    }

    private static void performThreadedComputation(Mandelbrot mb) {
        /* Gives each thread a set of rows to perform the computations for. */
        int div = (size * size) / numThreads;
        int startPix = 0;
        int endPix = 0;
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < numThreads; i++){
            Thread t;
            endPix = div * (i+1);
            if (i == numThreads - 1 && div * numThreads < (size * size)){
                /* Check if we can't evenly divide the total number of pixels by
                   the number of threads. If this is the case, and the last
                   thread is to be created, then give the thread an additional
                   pixel (the last one).
                 */
                t = createMandelThread(mb, xlo, xhi, ylo, yhi, size, threshold, startPix, endPix+1);
            }else {
                t = createMandelThread(mb, xlo, xhi, ylo, yhi, size, threshold, startPix, endPix);
            }
            threads.add(t);
            startPix = endPix;
        }

        // Join all the threads.
        for (Thread t : threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static Thread createMandelThread(Mandelbrot mb, double xlo, double xhi, double ylo, double yhi, int size, int thresh, int startPix, int endPix) {
        Thread t = new Thread(new MandelThread(mb, xlo, xhi, ylo, yhi, size, size, thresh, startPix, endPix));
        t.start();
        return t;
    }

    private static void parseArgs(String[] args) {
        /* Note: size is the only required flag here.
         *       numthreads & iters will default to 1.
         *       Remaining flags not passed will default to 0.
         */
        int i = 0;
        while (i < args.length && args[i].startsWith("-")) {
            String arg = args[i++];

            switch (arg) {
                case "-NUMTHREADS":
                    numThreads = Integer.parseInt(args[i++]);
                    break;
                case "-SUFFIX":
                    fileSuffix = args[i++];
                    break;
                case "-SIZE":
                    size = Integer.parseInt(args[i++]);
                    break;
                case "-THRESHOLD":
                    threshold = Integer.parseInt(args[i++]);
                    break;
                case "-XLO":
                    xlo = Double.parseDouble(args[i++]);
                    break;
                case "-XHI":
                    xhi = Double.parseDouble(args[i++]);
                    break;
                case "-YLO":
                    ylo = Double.parseDouble(args[i++]);
                    break;
                case "-YHI":
                    yhi = Double.parseDouble(args[i++]);
                    break;
                case "-ITERS":
                    iters = Integer.parseInt(args[i++]);
                    break;
            }
        }

        if (size <= 0){
            log("size must be greater than zero!");
            System.exit(1);
        }

    }

    private static void log(String str){
        System.out.println(str);
    }


}
