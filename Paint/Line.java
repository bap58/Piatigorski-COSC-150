package Paint;

import java.awt.*;

public class Line extends Shape
{
    int x1, y1;     // the endpoints of the line

    //constructor with parameters
    public Line(Color c, int i, int j, int endx, int endy)
    {
        super("line", c, i, j);
        x1 = endx;
        y1 = endy;
    }

    //getter functions
    public int getX1(){return x1;}
    public int getY1(){return y1;}

    //setter functions
    public void setX1(int a){x1 = a;}
    public void setY1(int b){y1 = b;}

    //drawMe allows it to draw itself
    @Override public void drawMe(Graphics g)
    {
        g.setColor(color);
        g.drawLine(x, y, x1, y1);
    }

    //clicked asks shape if it was clicked
    //line is clicked if either end is clicked
    @Override public boolean clicked(int xclick, int yclick)
    {
        boolean clicked = false;

        if((Math.abs(xclick - x) < tolerance && Math.abs(yclick - y) < tolerance) ||
                ((Math.abs(xclick - x1) < tolerance && Math.abs(yclick - y1) < tolerance)))
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
    }

    //gets the string which will be written in the save file
    @Override public String getSaveString()
    {
        String saveString = getType() + " "
                + colorToString(getColor()) + " "
                +getX() + " " + getY() + " "
                +x1 + " " + y1 + "\n";

        return saveString;
    }
}
