import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Stack;

class Point {
    int x, y, state;

    public Point(int x, int y, int state) {
        this.x = x;
        this.y = y;
        this.state = state;
    }
}


public class Painter {

    private final BufferedImage image;
    private Color painterColor;             // Farba, ktorou kreslime
    private Color initialColor;             // Farba, ktoru porovnavame

    final double deviation = 255 * 0.05;    // Maximalna dovolena odchylka

    private final Stack<Point> stack = new Stack<>();  // Simulacia realneho heapu, kedze ten nestaci

    public Painter(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage bucketFill(int x, int y, Color c) {
        painterColor = c;
        initialColor = new Color(image.getRGB(x, y));

        stack.push(new Point(x,y,0));
        while(!stack.empty()) {
            paintPixel();
        }

        return image;
    }


    private void paintPixel() {
        Point p = stack.pop();

        int x=-1,y=-1;

        switch (p.state) {
            case 0: //Doprava
                x = p.x + 1;
                y = p.y;
                break;
            case 1: //Dole
                x = p.x;
                y = p.y + 1;
                break;
            case 2: //Dolava
                x = p.x - 1;
                y = p.y;
                break;
            case 3: //Hore
                x = p.x;
                y = p.y - 1;
                break;
        }

        if(p.state <3) {    // Ak sme presli vsetky smery pri tomto bode tak uz ho nepridavame
            stack.push(new Point(p.x,p.y,p.state+1));
        }

        // Ak je novy bod mimo obrazku - koniec
        if (x < 0 || x >= image.getWidth(null) || y < 0 || y >= image.getHeight(null)) {
            return;
        }

        Color foundColor = new Color(image.getRGB(x, y));   // Farba aktualneho pixelu

        // Ak hranici s farbou, ktora nie je pribuzna - koniec
        if (Math.abs(foundColor.getBlue() - initialColor.getBlue()) > deviation ||
                Math.abs(foundColor.getRed() - initialColor.getRed()) > deviation ||
                Math.abs(foundColor.getGreen() - initialColor.getGreen()) > deviation ||
                Math.abs(foundColor.getAlpha() - initialColor.getAlpha()) > deviation) {
            return;
        }

        image.setRGB(x, y, painterColor.getRGB());
        stack.push(new Point(x,y,0));
    }

}
