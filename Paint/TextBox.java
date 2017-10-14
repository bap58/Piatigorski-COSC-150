package Paint;

import java.awt.*;

public class TextBox extends Shape
{
    String text;        //the text content of the box

    //default constructor
    public TextBox()
    {
        super("text", Color.black, 0,0);
        text = "";
    }

    //constructor with parameters
    public TextBox(String s, Color c, int i, int j)
    {
        super("text", c, i, j);
        text = s;
    }

    //getter functions
    String getText(){return text;}

    //drawMe allows it to draw itself
    @Override public void drawMe(Graphics g)
    {
        g.setColor(color);
        g.drawString(text, x, y);
    }

    //clicked asks shape if it was clicked
    //text box is clicked if the left end is clicked
    @Override public boolean clicked(int xclick, int yclick)
    {
        boolean clicked = false;

        if((Math.abs(xclick - x) < 20 && Math.abs(yclick - y) < 20))
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
                + getX() + " " + getY() + " "
                + text + "\n";

        return saveString;
    }
}
