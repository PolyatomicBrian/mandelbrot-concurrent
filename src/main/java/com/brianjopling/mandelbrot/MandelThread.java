package com.brianjopling.mandelbrot;

import java.awt.*;

public class MandelThread implements Runnable {

    Mandelbrot mb;
    private final double xlo;
    private final double xhi;
    private final double ylo;
    private final double yhi;
    private final int size;
    private final int size1;
    private final int thresh;
    private final int startPix;
    private final int endPix;

    public MandelThread(Mandelbrot mandelbrot, double xlo, double xhi, double ylo, double yhi, int size, int size1, int thresh, int startPix, int endPix) {
        this.mb = mandelbrot;
        this.xlo = xlo;
        this.xhi = xhi;
        this.ylo = ylo;
        this.yhi = yhi;
        this.size = size;
        this.size1 = size1;
        this.thresh = thresh;
        this.startPix = startPix;
        this.endPix = endPix;
    }

    @Override
    public void run() {
        mb.mandelBrot(xlo, xhi, ylo, yhi, size, size1, thresh, startPix, endPix);
    }

}
