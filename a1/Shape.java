package a1;

public class Shape
{
    protected String type;
    protected double area;
    protected double perimeter;
    static final protected double pi = 3.14;

    public Shape()
    {
        type = "?";
        area = 0;
        perimeter = 0;
    }

    public Shape(String str, double a, double p)
    {
        type = str;
        area = a;
        perimeter = p;
    }

    public double getArea() {return area;}
    public double getPerimeter() {return perimeter;}

    public void report()
    {
        System.out.println("This is a " + type + " of area " + area + " and of perimeter " + perimeter);
    }

}
