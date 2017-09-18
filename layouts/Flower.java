package layouts;

import javax.swing.*;
import java.awt.*;

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
        super.paint(g);

        g.setColor(new Color(165, 83, 0));
        g.fillOval(450,450,100,100);

//        int[] x = new int[3];
//        int[] y = new int[3];
//        x[0] = 450; x[1] = 550; x[2] = 500;
//        y[0] = 450; y[1] = 450; y[2] = 100;
//
//
//        g.setColor(new Color(165, 165, 0));
//        g.fillPolygon(x, y, 3);

        int r = 400;
        for(double theta = 0; theta < 2*3.14; theta += 3.14/4)
        {
           //need to handle rotation here...
            //right now it'll just make the same triangle over and over again...
            //trouble casting a double as an int so I can give sines and cosines as coordinates

            int[] x = new int[3];
            int[] y = new int[3];
            x[0] = 450; x[1] = 550; x[2] = 500;
            y[0] = 450; y[1] = 450; y[2] = 100;

            g.setColor(new Color(165, 165, 0));
            g.fillPolygon(x, y, 3);

        }


    }
}
