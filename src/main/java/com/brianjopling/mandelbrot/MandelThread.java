package com.brianjopling.mandelbrot;

import java.awt.*;

public class MandelThread implements Runnable {

    Mandelbrot mb;
    private final int threadId;
    private final int numThreads;
    private final double xlo;
    private final double xhi;
    private final double ylo;
    private final double yhi;
    private final int size;
    private final int thresh;

    public MandelThread(Mandelbrot mandelbrot, int threadId, int numThreads, double xlo, double xhi, double ylo, double yhi, int size, int thresh) {
        this.mb = mandelbrot;
        this.threadId = threadId;
        this.numThreads = numThreads;
        this.xlo = xlo;
        this.xhi = xhi;
        this.ylo = ylo;
        this.yhi = yhi;
        this.size = size;
        this.thresh = thresh;
    }

    @Override
    public void run() {
        mb.mandelBrot(threadId, numThreads, xlo, xhi, ylo, yhi, size, size, thresh);
    }

}
