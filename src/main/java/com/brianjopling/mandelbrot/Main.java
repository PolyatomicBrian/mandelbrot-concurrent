package com.brianjopling.mandelbrot;

public class Main {

    /* Initialized via parseArgs */
    private static int SIZE;
    private static int THRESHOLD;
    private static double XLO;
    private static double XHI;
    private static double YLO;
    private static double YHI;
    private static int ITERS = 1;

    public static void main(String[] args) {

        parseArgs(args);

        for (int i = 0; i < ITERS; i++) {
            Mandelbrot mb = new Mandelbrot(SIZE);
            mb.mandelRun(SIZE, THRESHOLD, XLO, XHI, YLO, YHI);
        }
    }

    private static void parseArgs(String[] args) {
        /* Note: SIZE is the only required flag here.
         *       Any flag not passed will default to 0.
         */
        int i = 0;
        while (i < args.length && args[i].startsWith("-")) {
            String arg = args[i++];

            switch (arg) {
                case "-SIZE":
                    SIZE = Integer.parseInt(args[i++]);
                    break;
                case "-THRESHOLD":
                    THRESHOLD = Integer.parseInt(args[i++]);
                    break;
                case "-XLO":
                    XLO = Double.parseDouble(args[i++]);
                    break;
                case "-XHI":
                    XHI = Double.parseDouble(args[i++]);
                    break;
                case "-YLO":
                    YLO = Double.parseDouble(args[i++]);
                    break;
                case "-YHI":
                    YHI = Double.parseDouble(args[i++]);
                    break;
                case "-ITERS":
                    ITERS = Integer.parseInt(args[i++]);
                    break;
            }
        }

        if (SIZE <= 0){
            System.out.println("Size must be greater than zero!");
            System.exit(1);
        }

    }


}
