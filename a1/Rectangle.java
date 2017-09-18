package a1;

import java.util.StringTokenizer;

public class Rectangle extends Shape
{
    protected double height;
    protected double width;

    //default constructor
    public Rectangle(){height = width = 0;}

    //constructor with height and width
    public Rectangle(double h, double w)
    {
        super("rectangle", h*w, 2*h + 2*w);
        height = h;
        width = w;
        //area = h * w;
        //perimeter = (2 * h) + (2 * w);
    }

    public Rectangle(StringTokenizer st)
    {
        super("rectangle", 0, 0);
        height = Double.parseDouble(st.nextToken());
        width = Double.parseDouble(st.nextToken());
        area = height * width;
        perimeter = (2 * height)+(2 * width);
    }

    public double getHeight(){return height;}
    public double getWidth(){return width;}

    @Override
    public void report()
    {
        super.report();
        System.out.println("Height: "+height+"      Width: "+width);
    }
}
