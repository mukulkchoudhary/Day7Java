import javax.swing.*;
import java.awt.*;

public class SmileyFaceSwing extends JPanel {

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Face
        g.setColor(Color.YELLOW);
        g.fillOval(100, 100, 200, 200);

        // Eyes
        g.setColor(Color.BLACK);
        g.fillOval(150, 160, 20, 20);
        g.fillOval(230, 160, 20, 20);

        // Smile
        g.drawArc(150, 180, 100, 60, 180, 180);
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Smiley Face");
        SmileyFaceSwing panel = new SmileyFaceSwing();

        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}



import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class ConcentricCirclesSwing extends JPanel {

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Random r = new Random();

        int radius = 20;

        for (int i = 0; i < 10; i++) {

            Color c = new Color(
                    r.nextInt(256),
                    r.nextInt(256),
                    r.nextInt(256));

            g.setColor(c);

            g.drawOval(
                    250 - radius,
                    250 - radius,
                    radius * 2,
                    radius * 2);

            radius += 10;
        }
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Concentric Circles");
        ConcentricCirclesSwing panel = new ConcentricCirclesSwing();

        frame.add(panel);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}





import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorSwing extends JFrame
        implements ActionListener {

    JTextField tf;

    double num1 = 0, num2 = 0, result = 0;
    String operator = "";

    String buttons[] = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C"
    };

    public CalculatorSwing() {

        setTitle("Calculator");
        setSize(350, 400);
        setLayout(new BorderLayout());

        tf = new JTextField();
        tf.setEditable(false);
        add(tf, BorderLayout.NORTH);

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(5, 4));

        for (String b : buttons) {

            JButton btn = new JButton(b);
            btn.addActionListener(this);
            p.add(btn);
        }

        add(p, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        String s = e.getActionCommand();

        // Numbers and decimal
        if ((s.charAt(0) >= '0' && s.charAt(0) <= '9')
                || s.equals(".")) {

            tf.setText(tf.getText() + s);
        }

        // Clear
        else if (s.equals("C")) {

            tf.setText("");
            num1 = num2 = result = 0;
            operator = "";
        }

        // Equals
        else if (s.equals("=")) {

            num2 = Double.parseDouble(tf.getText());

            switch (operator) {

                case "+":
                    result = num1 + num2;
                    break;

                case "-":
                    result = num1 - num2;
                    break;

                case "*":
                    result = num1 * num2;
                    break;

                case "/":
                    result = num1 / num2;
                    break;
            }

            tf.setText(String.valueOf(result));

            // Continuous calculation
            num1 = result;
        }

        // Operators
        else {

            num1 = Double.parseDouble(tf.getText());
            operator = s;
            tf.setText("");
        }
    }

    public static void main(String[] args) {

        new CalculatorSwing();
    }
}