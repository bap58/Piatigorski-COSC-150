package Paint;

import java.awt.*;
import java.util.StringTokenizer;

public class Triangle extends Shape
{
    int x1, x2, y1, y2;     //the x and y coordinates of the other two vertices

    //constructor with parameters
    public Triangle(Color c, int i, int j, int i1, int j1, int i2, int j2)
    {
        super("triangle", c, i, j);
        x1 = i1;
        x2 = i2;
        y1 = j1;
        y2 = j2;
    }

    //getter functions
    public int getX1(){return x1;}
    public int getX2(){return x2;}
    public int getY1(){return y1;}
    public int getY2(){return y2; }

    //setter functions
    public void setX1(int a){x1 = a;}
    public void setY1(int a){y1 = a;}
    public void setX2(int a){x2 = a;}
    public void setY2(int a){y2 = a;}

    //drawMe allows it to draw itself
    @Override public void drawMe(Graphics g)
    {
        g.setColor(color);

        int[] triX = new int[3];
        int[] triY = new int[3];

        triX[0] = x; triX[1] = x1; triX[2] = x2;
        triY[0] = y; triY[1] = y1; triY[2] = y2;

        g.fillPolygon(triX, triY, 3);
    }

    //clicked asks shape if it was clicked
    //triangle is clicked if any vertex is clicked
    @Override public boolean clicked(int xclick, int yclick)
    {
        boolean clicked = false;

        if((Math.abs(xclick - x) < 20 && Math.abs(yclick - y) < 20) ||
                ((Math.abs(xclick - x1) < 20 && Math.abs(yclick - y1) < 20))||
                ((Math.abs(xclick - x2) < 20 && Math.abs(yclick - y2) < 20)))
        {
            clicked = true;
        }

        return clicked;
    }

    //transforms the shape from one place to another
    @Override public void transform(int deltaX, int deltaY)
    {
        super.transform(deltaX, deltaY);
        setX1(x1 + deltaX);
        setY1(y1 + deltaY);
        setX2(x2 + deltaX);
        setY2(y2 + deltaY);
    }

    //gets the string which will be written in the save file
    @Override public String getSaveString()
    {
        String saveString = getType() + " "
                + colorToString(getColor()) + " "
                +getX() + " " + getY() + " "
                +x1 + " " + y1 + " " + x2 + " " + y2 + "\n";

        return saveString;
    }
}
