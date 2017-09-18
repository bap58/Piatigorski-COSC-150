package a1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class ShapeList
{
    LinkedList<Shape> listShapes;


    public static void main(String[] args)
    {
        new ShapeList();
    }


    public ShapeList()
    {
        String filename;
        Scanner in = new Scanner(System.in);

        System.out.print("Enter file address: ");
        filename = in.nextLine();

        listShapes = new LinkedList<Shape>();

        try
        {
            File f = new File(filename);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while((line = br.readLine()) != null)
            {
                processLine(line);
            }
            br.close();
        }
        catch(Exception e)
        {
            System.out.println("Error: "+e);
        }

        in.close();

        System.out.println("");
        report();
    }

    public void processLine(String line)
    {
        try
        {
            System.out.println("Processing ... " + line);
            StringTokenizer st = new StringTokenizer(line);
            String cmd = st.nextToken();
            if(cmd.equals("square")) {listShapes.add(new Square(st));}
            else if(cmd.equals("rectangle")) {listShapes.add(new Rectangle(st));}
            else if(cmd.equals("circle")) {listShapes.add(new Circle(st));}
            else if(cmd.equals("triangle")) {listShapes.add(new Triangle(st));}
        }
        catch(NoSuchElementException n)
        {
            System.out.println("No such element exception in line: "+line);
        }
        catch(Exception e)
        {
            System.out.println("Error in this line: " + e);
        }
    }

    public void report()
    {
        double totalArea = 0.0;
        double totalPerimeter = 0.0;

        Iterator<Shape> it = listShapes.iterator();
        while(it.hasNext())
        {
            Shape s = it.next();
            totalArea += s.getArea();
            totalPerimeter += s.getPerimeter();
            s.report();
            System.out.println("");
        }

        System.out.println("totalArea="+totalArea);
        System.out.println("totalPerimeter="+totalPerimeter);
    }

}
