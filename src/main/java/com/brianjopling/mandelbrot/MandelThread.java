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
    private final int startRow;
    private final int endRow;

    public MandelThread(Mandelbrot mandelbrot, double xlo, double xhi, double ylo, double yhi, int size, int size1, int thresh, int startRow, int endRow) {
        mb = mandelbrot;
        this.xlo = xlo;
        this.xhi = xhi;
        this.ylo = ylo;
        this.yhi = yhi;
        this.size = size;
        this.size1 = size1;
        this.thresh = thresh;
        this.startRow = startRow;
        this.endRow = endRow;
    }

    @Override
    public void run() {
        mb.mandelBrot(xlo, xhi, ylo, yhi, size, size1, thresh, startRow, endRow);
    }

}
