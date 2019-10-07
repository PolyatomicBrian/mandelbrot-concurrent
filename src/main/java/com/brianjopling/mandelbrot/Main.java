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
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < numThreads; i++){
            Thread t = createMandelThread(mb, i, xlo, xhi, ylo, yhi, size, threshold);
            threads.add(t);
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

    private static Thread createMandelThread(Mandelbrot mb, int threadId, double xlo, double xhi, double ylo, double yhi, int size, int thresh) {
        Thread t = new Thread(new MandelThread(mb, threadId, numThreads, xlo, xhi, ylo, yhi, size, thresh));
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
            log("SIZE must be greater than zero!");
            System.exit(1);
        }
        if (numThreads <= 0){
            log("NUMTHREADS must be greater than zero!");
            System.exit(2);
        }
        if (threshold <= 0){
            log("THRESHOLD must be greater than zero!");
            System.exit(3);
        }
        if (iters <= 0){
            log("ITERS must be greater than zero!");
            System.exit(4);
        }


    }

    private static void log(String str){
        System.out.println(str);
    }


}
