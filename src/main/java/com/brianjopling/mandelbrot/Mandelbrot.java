package com.brianjopling.mandelbrot;

import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class Mandelbrot {

    /* = Vars = */

    private final String filePrefix = "brj33";
    private final String fileExt = "png";

    private int type = BufferedImage.TYPE_INT_ARGB;

    private int size;               // Initialized by Constructor
    private BufferedImage image;    // Initialized by Constructor
    private Color[][] pixels;       // Initialized by Constructor
    private String fileSuffix = ""; // Initialized by Constructor


    /* = Constructor = */

    public Mandelbrot(int size, String fileSuffix){
        this.size = size;
        this.image = new BufferedImage(size, size, type);
        this.pixels = new Color[size][size];
        this.fileSuffix = fileSuffix;
    }


    /* = Methods = */

    private int compute(double xc, double yc, int thresh){
        int i = 0;
        double x = 0.0;
        double y = 0.0;
        while ((x * x + y * y < 2) && i < thresh) {
            double xt = x * x - y * y + xc;
            double yt = 2 * x * y + yc;
            x = xt;
            y = yt;
            i += 1;
        }
        return i;
    }

    public void mandelBrot(int threadId, int numThreads, double xlo, double xhi, double ylo, double yhi, int wdt, int hgt, int thresh){
        for (int idx = threadId; idx < wdt*hgt; idx += numThreads){
            int x = idx % wdt;
            int y = idx / wdt;
            double xc = xlo + (xhi - xlo) * x / wdt;
            double yc = ylo + (yhi - ylo) * y / hgt;
            int iters = compute(xc, yc, thresh);
            Color c = determinePixelColor(iters, thresh);
            addToArray(x, y, c);
        }
    }


    private Color determinePixelColor(int iters, int thresh) {
        // Main colors
        Color foreground = Color.WHITE;
        Color background = Color.BLACK;

        Color[] arrColors = new Color[]{foreground, Color.GREEN, Color.BLUE, Color.RED};

        /* Colors correspond to differing levels of threshold.
         * Number of levels will correspond to size of array.
         * Threshold is divided equally by index of color to get Level.
         */

        for (int i = 0; i < arrColors.length; i++){
            if (iters >= thresh/(i+1)){
                return arrColors[i];
            }
        }
        return background;
    }

    protected void output() {
        String filename = getFileName();
        try {
            ImageIO.write(image, "png", new File(filename));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFileName() {
        String filename = filePrefix + "." + fileExt;
        if (!fileSuffix.isEmpty()){
            filename = filePrefix + "-" + fileSuffix + "." + fileExt;
        }
        return filename;
    }

    private void addToArray(int x, int y, Color c){
        try {
            pixels[x][y] = c;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    protected void draw() {
        for (int x = 0; x < size; x++){
            for (int y = 0; y < size; y++){
                image.setRGB(x, y, pixels[x][y].getRGB());
            }
        }
    }

}
