package a1;

import java.util.StringTokenizer;

public class Circle extends Shape
{
    protected double radius;

    //default constructor
    public Circle(){radius = 0;}

    //constructor with radius
    public Circle(double r)
    {
        super("circle", pi*r*r, 2*pi*r);
        radius = r;
        //area = pi * r * r;
        //perimeter = 2* pi * r;
    }

    public Circle(StringTokenizer st)
    {
        super("circle", 0,0);
        radius = Double.parseDouble(st.nextToken());
        area = pi * radius * radius;
        perimeter = 2 * pi * radius;
    }

    public double getRadius(){return radius;}

    @Override
    public void report()
    {
        super.report();
        System.out.println("Radius: "+radius);
    }
}
