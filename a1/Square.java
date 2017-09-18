package a1;

import java.util.StringTokenizer;

public class Square extends Shape
{
    protected double side_size;

    //default constructor
    public Square(){side_size = 0;}

    //constructor with side
    public Square(double s)
    {
        super("square", s*s, 4*s);
        side_size = s;
        //area = s * s;
        //perimeter = 4 * s;
    }

    public Square(StringTokenizer st)
    {
        super("square", 0, 0);
        side_size = Double.parseDouble(st.nextToken());
        area = side_size * side_size;
        perimeter = 4 * side_size;
    }

    public double getSide_size() {return side_size;}

    @Override
    public void report()
    {
        super.report();
        System.out.println("Side length: "+side_size);
    }
}
