package Paint;

import java.awt.*;

public class Shape
{
    String type;      //the kind of shape it is
    Color color;      //color of the shape
    int x, y;                   //coordinates of the shape, from top left:

    //x and y coordinates vary meaning for each shape:
    //Lines: (x,y) is the first endpoint of the line
    //Ovals: (x,y) is the top left corner of a circumscribing rectangle of the oval
    //rectangles: (x,y) is the top left corner of the rectangle
    //triangles: (x,y) is the first vertex of the triangle defined
    //text box: (x,y) is the location of the left of the text box

    //default constructor
    public Shape()
    {
        type = " ";
        color = Color.black;
        x = y = 0;
    }

    // constructor with parameters
    public Shape(String str, Color c, int i, int j)
    {
        type = str;
        color = c;
        x = i;
        y = j;
    }

    //getter functions
    public int getX(){return x;}
    public int getY(){return y;}
    public Color getColor(){return color;}
    public String getType(){return type;}

    //setter functions
    public void setX(int a){x = a;}
    public void setY(int b){y = b;}

    //function drawMe for each shape to draw itself
    //drawing will practically be handled by the subclasses
    public void drawMe(Graphics g){}

    //function clicked to ask shape if it was clicked
    //will be overridden by each type of shape
    public boolean clicked(int x, int y){return false;}

    //transforms the shape from one place to another
    public void transform(int deltaX, int deltaY)
    {
        setX(x + deltaX);
        setY(y + deltaY);
    }

    //gets the string which will be written in the save file
    //overridden by subclasses
    public String getSaveString()
    {
        String saveString = getType() + " "
                + colorToString(getColor()) + " "
                +getX() + " " + getY() + " ";

        return saveString;
    }

    //conversion for color to string
    public String colorToString(Color c)
    {
        String returnString = "";

        if(c == Color.red)
            returnString = "red";
        else if(c == Color.orange)
            returnString = "orange";
        else if(c == Color.yellow)
            returnString = "yellow";
        else if(c == Color.green)
            returnString = "green";
        else if(c == Color.blue)
            returnString = "blue";
        else if(c == Color.magenta)
            returnString = "purple";
        else if(c == Color.black)
            returnString = "black";

        return returnString;
    }

}
