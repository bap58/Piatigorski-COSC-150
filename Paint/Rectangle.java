package Paint;

import java.awt.*;
import java.util.StringTokenizer;

public class Rectangle extends Shape
{
    int height;
    int width;

    //default constructor
    public Rectangle()
    {
        super("rectangle", Color.black,0,0);
        height = width = 0;
    }

    //constructor with parameters
    public Rectangle(Color c, int i, int j, int w, int h)
    {
        super("rectangle", c, i, j);
        height = h;
        width = w;
    }

    /*public Rectangle(StringTokenizer st)
    {
        super("rectangle", 0, 0);
        height = Double.parseDouble(st.nextToken());
        width = Double.parseDouble(st.nextToken());

    }*/

    //getter functions
    public int getHeight(){return height;}
    public int getWidth(){return width;}

    //setter functions
    public void setHeight(int h){height = h;}
    public void setWidth(int w){width = w;}


    //drawMe allows it to draw itself
    @Override public void drawMe(Graphics g)
    {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    //clicked asks shape if it was clicked
    //rectangle is clicked if any corner is clicked
    @Override public boolean clicked(int xclick, int yclick)
    {
        boolean clicked = false;

        if((Math.abs(xclick - x) < tolerance && Math.abs(yclick - y) < tolerance) ||
                ((Math.abs(xclick - (x+width)) < tolerance && Math.abs(yclick - y) < tolerance)) ||
                ((Math.abs(xclick - x) < tolerance && Math.abs(yclick - (y+height)) < tolerance)) ||
                ((Math.abs(xclick - (x+width)) < tolerance && Math.abs(yclick - (y+height)) < tolerance)))
        {
            clicked = true;
        }

        return clicked;
    }

    //gets the string which will be written in the save file
    @Override public String getSaveString()
    {
        String saveString = getType() + " "
                + colorToString(getColor()) + " "
                +getX() + " " + getY() + " "
                +height + " " + width + "\n";

        return saveString;
    }
}
