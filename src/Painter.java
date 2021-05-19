import java.awt.*;
import java.awt.image.BufferedImage;

public class Painter {

    private final BufferedImage image;
    private Color painterColor;             // Farba, ktorou kreslime
    private Color initialColor;             // Farba, ktoru porovnavame

    final double deviation = 255 * 0.05;    //Maximalna dovolena odchylka
    final int MAX_DEPTH = 2000;             //Po 2000 vnoreniach vypraznime stack a ideme znova

    int lastX = -1; //Sledovanie, kde sme skoncili s X poziciou
    int lastY = -1; //Sledovanie, kde sme skoncili s Y poziciou

    public Painter(BufferedImage image) {
        this.image = image;
    }

    // Attempt to optimize recursion - wrong
    /*public BufferedImage bucketFill(int x, int y, Color c) {
        painterColor = c;
        initialColor = new Color(image.getRGB(x, y));

        boolean doneStart = false;

        int depth;
        while (!doneStart) {

            depth = paint(x, y, 0);     // Skusime od zaciatocneho pola vyfarbovat
            if (depth >= MAX_DEPTH) {          // Ak uz sme vyfarbili vsetky smery tak koncime
                doneStart = true;
                return image;
            }

            do {
                depth = paint(lastX, lastY, 0);   // Vymalovanie od posledneho bodu, kde sme skoncili
            } while (depth >= MAX_DEPTH);               // Ak sme uz domalovali vsetky chybajuce body tak koncime
        }

        return image;
    }

    //Rekurzivna funkcia vyfarbovania
    private int paint(int x, int y, int depth) {



        if (x < 0 || x >= image.getWidth(null) || y < 0 || y >= image.getHeight(null)) {
            return depth - 1;
        }

        Color foundColor = new Color(image.getRGB(x, y));   // Farba aktualneho pixelu

        if (Math.abs(foundColor.getBlue() - initialColor.getBlue()) > deviation ||
                Math.abs(foundColor.getRed() - initialColor.getRed()) > deviation ||
                Math.abs(foundColor.getGreen() - initialColor.getGreen()) > deviation ||
                Math.abs(foundColor.getAlpha() - initialColor.getAlpha()) > deviation) {

            System.out.println();
            return depth - 1;
        }

        if (depth == MAX_DEPTH) {
            lastX = x;
            lastY = y;
            return MAX_DEPTH;
        }

        image.setRGB(x, y, painterColor.getRGB());

        int d = 0;
        d = paint(x - 1, y, depth + 1);
        if (d >= MAX_DEPTH)
            return MAX_DEPTH;

        d = paint(x, y - 1, depth + 1);
        if (d >= MAX_DEPTH)
            return MAX_DEPTH;


        d = paint(x + 1, y, depth + 1);
        if (d >= MAX_DEPTH)
            return MAX_DEPTH;

        d = paint(x, y + 1, depth + 1);
        if (d >= MAX_DEPTH)
            return MAX_DEPTH;

        return depth;
    }*/

    public BufferedImage bucketFill(int x, int y, Color c) {
        painterColor = c;
        initialColor = new Color(image.getRGB(x, y));

        paint(x, y);



        return image;
    }

    //Rekurzivna funkcia vyfarbovania
    private void paint(int x, int y) {

        if (x < 0 || x >= image.getWidth(null) || y < 0 || y >= image.getHeight(null)) {
            return;
        }

        Color foundColor = new Color(image.getRGB(x, y));   // Farba aktualneho pixelu

        if (Math.abs(foundColor.getBlue() - initialColor.getBlue()) > deviation ||
                Math.abs(foundColor.getRed() - initialColor.getRed()) > deviation ||
                Math.abs(foundColor.getGreen() - initialColor.getGreen()) > deviation ||
                Math.abs(foundColor.getAlpha() - initialColor.getAlpha()) > deviation) {

            return;
        }

        image.setRGB(x, y, painterColor.getRGB());

        paint(x - 1, y);
        paint(x, y - 1);
        paint(x + 1, y);
        paint(x, y + 1);
    }
}
