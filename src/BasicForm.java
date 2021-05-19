import javax.swing.*;

public class BasicForm {
    private JPanel rootPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("BasicForm");
        frame.setContentPane(new BasicForm().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
