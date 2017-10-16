//Brian Piatigorski
//Project 1 - Shapes
//Worked with Nico Cuevas

package Paint;

import a1.Circle;
import a1.Square;

import java.awt.*;
import java.io.*;
import java.util.*;

public class ShapeList
{
    LinkedList<Shape> listShapes;

    public ShapeList()
    {
        listShapes = new LinkedList<Shape>();
    }

    //adds a shape to the list
    public void add(Shape s)
    {
        listShapes.add(s);
    }

    //draws all shapes by iterating through the list
   public void drawList(Graphics g)
   {
       Iterator<Shape> it = listShapes.iterator();
       while(it.hasNext())
       {
           Shape s = it.next();
           s.drawMe(g);
       }
   }

   //returns true if user clicked on a shape, iterates through list to check
   public boolean clickedAShape(int x, int y)
   {
       boolean clicked = false;

       Iterator<Shape> it = listShapes.iterator();
       while(it.hasNext() && !clicked)
       {
           Shape s = it.next();
           clicked = s.clicked(x, y);
       }

       return clicked;
   }

   //moves the shape that was clicked
    public void moveShape(int downX, int downY, int upX, int upY)
    {
        boolean clicked = false;
        Shape s = null;

        Iterator<Shape> it = listShapes.iterator();
        while(it.hasNext() && !clicked)
        {
            s = it.next();
            clicked = s.clicked(downX, downY);
        }

        //transform the shape if not null
        if(s != null)
        {
            int deltaX = upX - downX;       //get the x displacement
            int deltaY = upY - downY;       //get the y displacement
            s.transform(deltaX, deltaY);
        }

    }

    //saves files by writing shape specs to a save file, specified by user
    public void save(String filename)
    {
        try
        {
            //make a file writer
            File file = new File(filename);
            FileWriter fileWriter = new FileWriter(file);

            //write each shape in the list
            Iterator<Shape> it = listShapes.iterator();
            while(it.hasNext())
            {
                Shape s = it.next();
                String saveString = s.getSaveString();
                fileWriter.write(saveString);
                System.out.println(saveString);

            }

            fileWriter.flush();
            fileWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    //loads from a save file. reads lines with shape specs,
    //determines what kind of shape, and adds a new shape with those
    //specs to the list
    public void load(String filename)
    {
        try
        {
            //get a file scanner
            File file = new File(filename);
            Scanner scanner = new Scanner(file);


            while(scanner.hasNext())            //while there are more shapes left
            {
                String nextType = scanner.next();   //what kind of shape is it

                //handle each kind of shape
                if(nextType.equals("line"))
                {
                    Color c = stringToColor(scanner.next());
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    int x1 = scanner.nextInt();
                    int y1 = scanner.nextInt();

                    add(new Line(c, x, y, x1, y1));

                }
                else if(nextType.equals("oval"))
                {
                    Color c = stringToColor(scanner.next());
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    int h = scanner.nextInt();
                    int w = scanner.nextInt();

                    add(new Oval(c, x, y, w, h));

                }
                else if(nextType.equals("rectangle"))
                {
                    Color c = stringToColor(scanner.next());
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    int h = scanner.nextInt();
                    int w = scanner.nextInt();

                    add(new Rectangle(c, x, y, w, h));
                }
                else if(nextType.equals("triangle"))
                {
                    Color c = stringToColor(scanner.next());
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    int x1 = scanner.nextInt();
                    int y1 = scanner.nextInt();
                    int x2 = scanner.nextInt();
                    int y2 = scanner.nextInt();

                    add(new Triangle(c, x, y, x1, y1, x2, y2));

                }
                else if(nextType.equals("text"))
                {
                    Color c = stringToColor(scanner.next());
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    String text = scanner.next();

                    add(new TextBox(text, c, x, y));
                }

                System.out.println("added a "+nextType+" to the list");

            }
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    //conversion for string to color
    //useful for reading a file and knowing what color the shape was
    public Color stringToColor(String s)
    {
        Color returnColor = Color.black;

        if(s.equals("red"))
            returnColor = Color.red;
        else if(s.equals("orange"))
            returnColor = Color.orange;
        else if(s.equals("yellow"))
            returnColor = Color.yellow;
        else if(s.equals("green"))
            returnColor = Color.green;
        else if(s.equals("blue"))
            returnColor = Color.blue;
        else if(s.equals("purple"))
            returnColor = Color.magenta;
        else if(s.equals("black"))
            returnColor = Color.black;

        return returnColor;
    }

    //clears the linked list
    public void clear()
    {
        listShapes.clear();
    }
}
