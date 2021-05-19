import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BasicForm extends JPanel{

    final String imgPath = "obr.bmp";
    BufferedImage img;

    public BasicForm() {
        JLabel xPos = new JLabel("X position");
        add(xPos);

        JTextField xPosField = new JTextField();
        xPosField.setPreferredSize(new Dimension(70,30));
        add(xPosField);

        JLabel yPos = new JLabel("Y position");
        add(yPos);

        JTextField yPosField = new JTextField();
        yPosField.setPreferredSize(new Dimension(70,30));
        add(yPosField);

        JLabel lbl = new JLabel();
        //Load image
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(imgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JButton btn = new JButton("Click");

        BufferedImage finalImage1 = image;
        btn.addActionListener(e-> {
            Painter painter = new Painter(finalImage1);

            try {
                int x = Integer.parseInt(xPosField.getText());
                int y = Integer.parseInt(yPosField.getText());

                img = painter.bucketFill(x, y, new Color(0, 255, 0, 255));
                lbl.setIcon(new ImageIcon(img));
            }catch (Exception e2) {
                e2.printStackTrace();
            }
        });

        JButton save = new JButton("Save");
        save.addActionListener(e-> {
            try {
                File outputfile = new File("saved.png");
                ImageIO.write(img, "png", outputfile);
            } catch (IOException e3) {
                // handle exception
            }
        });

        add(btn);
        add(lbl);

        add(save);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bucket fill");
        frame.setContentPane(new BasicForm());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(new Dimension(800,800));
    }
}
