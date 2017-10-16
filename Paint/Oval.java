package Paint;

import java.awt.*;

public class Oval extends Shape
{
    int height, width;      //the height and width of the oval

    //default constructor
    public Oval()
    {
        super("oval", Color.black, 0,0);
        height = width = 0;
    }

    //constructor with parameters
    public Oval(Color c, int i, int j, int w, int h)
    {
        super("oval", c, i, j);
        height = h;
        width = w;
    }

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
        g.fillOval(x, y, width, height);
    }

    //clicked asks shape if it was clicked
    //Oval is clicked if the very top or very bottom is clicked
    @Override public boolean clicked(int xclick, int yclick)
    {
        boolean clicked = false;

        if((Math.abs(xclick - (x+width/2)) < tolerance && Math.abs(yclick - y) < tolerance) ||
                ((Math.abs(xclick - (x+width/2)) < tolerance && Math.abs(yclick - (y+height)) < tolerance)))
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
