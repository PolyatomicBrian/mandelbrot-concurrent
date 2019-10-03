package com.brianjopling.mandelbrot;

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

    public static void main(String[] args) {

        parseArgs(args);

        for (int i = 0; i < iters; i++) {

            Mandelbrot mb = new Mandelbrot(size);

            long startTime = System.currentTimeMillis();

            performThreadedComputation(mb);

            long endTime = System.currentTimeMillis();
            long totalRuntime = endTime - startTime;
            log("Mandelbrot Computation Runtime: " + totalRuntime + "ms");

            mb.draw();
            mb.output();

        }
    }

    private static void performThreadedComputation(Mandelbrot mb) {
        /* Gives each thread a set of rows to perform the computations for. */
        int div = size / numThreads;
        int startRow = 0;
        int endRow = 0;
        for (int i = 0; i < numThreads; i++){
            endRow = div * (i+1);
            if (i == numThreads - 1 && div * numThreads < size){
                /* Check if we can't evenly divide the total number of rows by
                   the number of threads. If this is the case, and the last
                   thread is to be created, then give the thread an additional
                   row (the last one).
                 */
                createMandelThread(mb, xlo, xhi, ylo, yhi, size, threshold, startRow, endRow+1);
            }else {
                createMandelThread(mb, xlo, xhi, ylo, yhi, size, threshold, startRow, endRow);
            }
            startRow = endRow+1;
        }
    }

    private static void createMandelThread(Mandelbrot mb, double xlo, double xhi, double ylo, double yhi, int size, int thresh, int startRow, int endRow) {
        Thread t = new Thread(new MandelThread(mb, xlo, xhi, ylo, yhi, size, size, thresh, startRow, endRow));
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void parseArgs(String[] args) {
        /* Note: size is the only required flag here.
         *       Any flag not passed will default to 0.
         */
        int i = 0;
        while (i < args.length && args[i].startsWith("-")) {
            String arg = args[i++];

            switch (arg) {
                case "-numThreads":
                    numThreads = Integer.parseInt(args[i++]);
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
