package a1;

import java.util.StringTokenizer;

public class Triangle extends Shape
{
    protected double side1;
    protected double side2;
    protected double side3;

    protected double p; //a tool for calculating area, p = half the perimeter

    //default constructor
    public Triangle(){side1 = side2 = side3 = 0;}

    //constructor with sides
    public Triangle(double s1, double s2, double s3)
    {
        p = (s1 + s2 + s3)/2;

        side1 = s1;
        side2 = s2;
        side3 = s3;

        area = Math.sqrt(p * (p - s1) * (p - s2) * (p - s3)); //Heron's formula
        perimeter = 2 * p;
    }

    public Triangle(StringTokenizer st)
    {
        super("triangle",0,0);
        side1 = Double.parseDouble(st.nextToken());
        side2 = Double.parseDouble(st.nextToken());
        side3 = Double.parseDouble(st.nextToken());

        p = (side1 + side2 + side3)/2;

        area = Math.sqrt(p * (p - side1) * (p - side2) * (p - side3)); //Heron's formula
        perimeter = 2 * p;
    }

    @Override
    public void report()
    {
        super.report();
        System.out.println("Side 1: "+side1+"       Side 2: "+side2+"       Side 3: "+side3);
    }
}
