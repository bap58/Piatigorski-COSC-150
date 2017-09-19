package layouts;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Flower extends JFrame
{
    public static void main(String[] args) {new Flower();}

    public Flower()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(1000,1000);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g)
    {
        Random rand;
        rand = new Random();

        super.paint(g);

//        int[] x = new int[3];
//        int[] y = new int[3];
//        x[0] = 450; x[1] = 550; x[2] = 500;
//        y[0] = 450; y[1] = 450; y[2] = 100;
//
//
//        g.setColor(new Color(165, 165, 0));
//        g.fillPolygon(x, y, 3);

        int r1 = 400;
        int r2 = 50;
        for(double theta = 0; theta < 2*3.14; theta += 3.14/8)
        {
           //need to handle rotation here...
            //right now it'll just make the same triangle over and over again...
            //trouble casting a double as an int so I can give sines and cosines as coordinates

            double x0, x1, x2, y0, y1, y2;
            x2 = 500 + r1 * Math.cos(theta);
            y2 = 500 + r1 * Math.sin(theta);

            x1 = 500 + r2 * Math.cos(theta - 3.14/4);
            y1 = 500 + r2 * Math.sin(theta - 3.14/4);

            x0 = 500 + r2 * Math.cos(theta + 3.14/4);
            y0 = 500 + r2 * Math.sin(theta + 3.14/4);

            int[] x = new int[3];
            int[] y = new int[3];
            x[0] = (int)Math.round(x0); x[1] = (int)Math.round(x1); x[2] = (int)Math.round(x2);
            y[0] = (int)Math.round(y0); y[1] = (int)Math.round(y1); y[2] = (int)Math.round(y2);

            g.setColor(new Color(135 + rand.nextInt(50), 135 + rand.nextInt(50), 0));
            g.fillPolygon(x, y, 3);

        }

        g.setColor(new Color(165, 83, 0));
        g.fillOval(450,450,100,100);


    }
}
